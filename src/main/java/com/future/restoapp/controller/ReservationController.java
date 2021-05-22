package com.future.restoapp.controller;

import com.future.restoapp.controller.path.ReservationControllerPath;
import com.future.restoapp.model.entity.Reservation;
import com.future.restoapp.service.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "Reservation")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservationController extends BaseController {

    @Autowired
    ReservationService reservationService;

    @RequestMapping(value = ReservationControllerPath.CREATE, method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Reservation reservation) throws Exception {

//        System.out.println(reservation);
        Reservation newReservation = reservationService.create(reservation);

        return ResponseEntity.created(new URI("/reservation/1")).build();
    }

}
