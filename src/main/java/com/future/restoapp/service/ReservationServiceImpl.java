package com.future.restoapp.service;

import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.model.entity.OrderItem;
import com.future.restoapp.model.entity.OrderItemKey;
import com.future.restoapp.model.entity.Reservation;
import com.future.restoapp.repository.MenuRepository;
import com.future.restoapp.repository.ReservationRepository;
import com.future.restoapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Reservation create(@NotNull Reservation reservation) throws Exception {
        Collection<OrderItem> orders  = reservation.getOrders();

        reservation.setOrders(null);

        reservationRepository.save(reservation);

        reservation.setOrders(
                orders
                    .stream()
                    .peek(order -> {
                        Long id = order.getMenu().getId();
                        Menu menu = menuRepository.findById(id)
                                .orElse(null);

                        if(menu == null) throw new NoSuchElementException("Menu with specified ID not found");

                        order.setMenu(menu);
                        order.setReservation(reservation);
                        order.setId(OrderItemKey
                                .builder()
                                .menuId(menu.getId())
                                .reservationId(reservation.getId())
                                .build());
                    }).collect(Collectors.toList())
        );

        reservation.setTotalPrice(
                reservation.getOrders()
                    .stream()
                    .map(el -> el.getMenu().getPrice() * el.getQuantity())
                    .reduce(0F, Float::sum)
        );

        reservationRepository.save(reservation);

        return reservation;
    }

    @Override
    public Reservation findById(long id) throws Exception {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Reservation> findByQuery(
            Long userId,
            Long boardId,
            Boolean isAccepted,
            LocalDateTime lowerStartTime,
            LocalDateTime upperStartTime,
            LocalDateTime lowerEndTime,
            LocalDateTime upperEndTime,
            Pageable pageable
    ) throws Exception {
        if(lowerStartTime == null) lowerStartTime = LocalDateTime.now().minusYears(2000);
        if(lowerEndTime == null) lowerEndTime = LocalDateTime.now().minusYears(2000);
        if(upperStartTime == null) upperStartTime = LocalDateTime.now().plusYears(9999);
        if(upperEndTime == null) upperEndTime = LocalDateTime.now().plusYears(9999);

        return reservationRepository.findByQuery(userId, boardId, isAccepted,
                lowerStartTime, upperStartTime, lowerEndTime, upperEndTime, pageable);
    }

    @Override
    public Reservation update(@NotNull Reservation reservation) throws Exception {
        Reservation reservationDb = reservationRepository
                .findById(reservation.getId())
                .orElseThrow(() -> new NoSuchElementException("Reservation with specified ID not found"));
        reservationDb.update(reservation);
        return reservationRepository.save(reservationDb);
    }

    @Override
    public void deleteById(long id) throws Exception {
        reservationRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reservation with specified ID not found"));
        reservationRepository.deleteById(id);
    }

}
