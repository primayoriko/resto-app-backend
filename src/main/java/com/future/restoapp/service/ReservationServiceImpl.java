package com.future.restoapp.service;

import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.model.entity.Reservation;
import com.future.restoapp.repository.MenuRepository;
import com.future.restoapp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Reservation create(Reservation reservation) throws Exception {
        System.out.println(reservation);

        reservation.setOrders(
                reservation.getOrders()
                        .stream()
                        .peek(order -> {
                            String id = order.getId().getMenuId();
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
    public Reservation findById(String id){
        return reservationRepository.findById(id).orElse(null);
    }

}
