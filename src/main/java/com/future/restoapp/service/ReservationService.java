package com.future.restoapp.service;

import com.future.restoapp.model.entity.Reservation;

public interface ReservationService {

    Reservation create(Reservation reservation) throws Exception;

    Reservation findById(String id);

}
