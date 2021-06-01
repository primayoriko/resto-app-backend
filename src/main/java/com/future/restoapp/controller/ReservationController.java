package com.future.restoapp.controller;

import com.future.restoapp.controller.path.ReservationControllerPath;
import com.future.restoapp.model.dto.ReservationCreateRequest;
import com.future.restoapp.model.entity.Reservation;
import com.future.restoapp.model.entity.User;
import com.future.restoapp.service.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;

@Tag(name = "Reservation")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservationController extends BaseController {

    @Autowired
    ReservationService reservationService;

    @RequestMapping(value = ReservationControllerPath.CREATE, method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody ReservationCreateRequest reservationReq, Principal principal) throws Exception {
        User user = getUser(principal);
        Reservation reservation = reservationService.create(reservationReq.toReservation(), user);

        String uri = String.format("%s/%d", ReservationControllerPath.BASE_CLIENT, reservation.getId());
        return ResponseEntity.created(new URI(uri)).build();
    }

    @RequestMapping(value = {
            ReservationControllerPath.FETCH_ONE
    }, method = RequestMethod.GET)
    public ResponseEntity fetchOne(@RequestBody ReservationCreateRequest reservationReq, Principal principal) throws Exception {
        User user = getUser(principal);
        Reservation reservation = reservationService.create(reservationReq.toReservation(), user);

        String uri = String.format("%s/%d", ReservationControllerPath.BASE_CLIENT, reservation.getId());
        return ResponseEntity.created(new URI(uri)).build();
    }

    @RequestMapping(value = ReservationControllerPath.FETCH_ALL, method = RequestMethod.GET)
    public ResponseEntity fetchByQuery(@RequestBody ReservationCreateRequest reservationReq, Principal principal) throws Exception {
        User user = getUser(principal);
        Reservation reservation = reservationService.create(reservationReq.toReservation(), user);

        String uri = String.format("%s/%d", ReservationControllerPath.BASE_CLIENT, reservation.getId());
        return ResponseEntity.created(new URI(uri)).build();
    }

    @RequestMapping(value = ReservationControllerPath.UPDATE_ADMIN, method = RequestMethod.PATCH)
    public ResponseEntity updateAdmin(@RequestBody ReservationCreateRequest reservationReq, Principal principal) throws Exception {
        User user = getUser(principal);
        Reservation reservation = reservationService.create(reservationReq.toReservation(), user);

        String uri = String.format("%s/%d", ReservationControllerPath.BASE_CLIENT, reservation.getId());
        return ResponseEntity.created(new URI(uri)).build();
    }

    @RequestMapping(value = ReservationControllerPath.UPDATE_CLIENT, method = RequestMethod.PATCH)
    public ResponseEntity updateClient(@RequestBody ReservationCreateRequest reservationReq, Principal principal) throws Exception {
        User user = getUser(principal);
        Reservation reservation = reservationService.create(reservationReq.toReservation(), user);

        String uri = String.format("%s/%d", ReservationControllerPath.BASE_CLIENT, reservation.getId());
        return ResponseEntity.created(new URI(uri)).build();
    }

}
