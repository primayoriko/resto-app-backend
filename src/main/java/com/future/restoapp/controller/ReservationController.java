package com.future.restoapp.controller;

import com.future.restoapp.controller.path.ReservationControllerPath;
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
    public ResponseEntity create(@RequestBody Reservation reservation, Principal principal) throws Exception {

//        System.out.println(reservation);
//        UserPrincipal userData = (UserPrincipal)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
//        System.out.println(userData);
        User user = getUser(principal);
        System.out.println(user.getId());
        Reservation newReservation = reservationService.create(reservation, user);

        return ResponseEntity.created(new URI("/reservation/1")).build();
    }

}
