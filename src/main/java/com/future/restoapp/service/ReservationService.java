package com.future.restoapp.service;

import com.future.restoapp.model.entity.Reservation;
import com.future.restoapp.model.entity.User;

public interface ReservationService {

    Reservation create(Reservation reservation, User client) throws Exception;

    Reservation findById(Long id);

}
