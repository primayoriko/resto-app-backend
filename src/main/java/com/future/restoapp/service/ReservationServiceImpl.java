package com.future.restoapp.service;

import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.model.entity.Reservation;
import com.future.restoapp.model.entity.User;
import com.future.restoapp.repository.MenuRepository;
import com.future.restoapp.repository.ReservationRepository;
import com.future.restoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuRepository menuRepository;

    @Override
    @Transactional
    public Reservation create(Reservation reservation) throws Exception {
        System.out.println(reservation);

        Long userId = reservation.getUser().getId();
        User user = userRepository.findById(userId).orElse(null);

        if(user == null) throw new NoSuchElementException("");
        reservation.setUser(user);

        System.out.println(reservation);

        reservation.setOrders(
                reservation.getOrders()
                        .stream()
                        .peek(order -> {
                            Long id = order.getId().getMenuId();
                            Menu menu = menuRepository.findById(id)
                                    .orElse(null);

                            if(menu == null) throw new NoSuchElementException("");

                            order.setMenu(menu);
                        }).collect(Collectors.toList())
        );

        System.out.println(reservation);

        reservationRepository.save(reservation);

        System.out.println(reservation);

        return reservation;
    }

    @Override
    public Reservation findById(Long id){
        return reservationRepository.findById(id).orElse(null);
    }

}
