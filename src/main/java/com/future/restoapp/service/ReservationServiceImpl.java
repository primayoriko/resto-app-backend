package com.future.restoapp.service;

import com.future.restoapp.model.entity.*;
import com.future.restoapp.repository.MenuRepository;
import com.future.restoapp.repository.ReservationRepository;
import com.future.restoapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
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
    @Transactional
    public Reservation create(Reservation reservation, User client) throws Exception {
        Collection<OrderItem> orders  = reservation.getOrders();

        reservation.setOrders(null);
        reservation.setUser(client);
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
    public Reservation findById(Long id) throws Exception {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Reservation> findByQuery(
            Long userId,
            Boolean isAccepted,
            LocalDateTime startTime,
            LocalDateTime endTime,
            Pageable pageable
    ) throws Exception {
        if(startTime == null) startTime = LocalDateTime.now().plusYears(9999);
        if(endTime == null) endTime = LocalDateTime.now().minusYears(2000);

        Date start = Timestamp.valueOf(startTime);
        Date end = Timestamp.valueOf(endTime);

        return reservationRepository.findByQuery(userId, isAccepted, start, end, pageable);
    }

}
