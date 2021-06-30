package com.future.restoapp.controller;

import com.future.restoapp.controller.path.ReservationControllerPath;
import com.future.restoapp.dto.reservation.ReservationAdminUpdateRequest;
import com.future.restoapp.dto.reservation.ReservationCreateRequest;
import com.future.restoapp.dto.reservation.ReservationResponse;
import com.future.restoapp.dto.core.SuccessResponse;
import com.future.restoapp.domain.Reservation;
import com.future.restoapp.domain.User;
import com.future.restoapp.exception.AccessPrivilegeNotEnoughException;
import com.future.restoapp.service.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Tag(name = "Reservation")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservationController extends BaseController {

    @Autowired
    ReservationService reservationService;

    @RequestMapping(value = ReservationControllerPath.CREATE, method = RequestMethod.POST)
    public ResponseEntity create(@Valid @RequestBody ReservationCreateRequest reservationReq, Principal principal) throws Exception {
        User user = getUser(principal);
        Reservation reservation = reservationReq.toReservation();

        reservation.setUser(user);

        reservation = reservationService.create(reservation);
        String uri = String.format("%s/%d", ReservationControllerPath.BASE_CLIENT, reservation.getId());

        return ResponseEntity.created(new URI(uri)).build();
    }

    @RequestMapping(value = {
            ReservationControllerPath.FETCH_ONE
    }, method = RequestMethod.GET)
    @Transactional
    public ResponseEntity fetchOne(@PathVariable Long id, Principal principal) throws Exception {
        User user = getUser(principal);
        Reservation reservation = reservationService.findById(id);

        if(reservation == null) throw new NoSuchElementException("Reservation with specified ID not found");
        if(!user.getIsAdmin() && !user.getId().equals(reservation.getUser().getId()))
            throw new AccessPrivilegeNotEnoughException("Your account is either not admin or not have access to look this resource(s)");

        SuccessResponse responseBody = new SuccessResponse(ReservationResponse.build(reservation));

        return ResponseEntity.ok(responseBody);
    }

    @RequestMapping(value = ReservationControllerPath.FETCH_ALL, method = RequestMethod.GET)
    @Transactional
    public ResponseEntity fetchByQuery(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long boardId,
            @RequestParam(required = false) Boolean isAccepted,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime lowerStartTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime lowerEndTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime upperStartTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime upperEndTime,
            Principal principal
    ) throws Exception {
        User user = getUser(principal);
        Pageable pageable = PageRequest.of(
                page - 1, pageSize,
                Sort.by("isAccepted").ascending()
                        .and(Sort.by("startTime").ascending())
                        .and(Sort.by("endTime").ascending())
        );

        if(!user.getIsAdmin()) isAccepted = false;

        Page<Reservation> reservations = reservationService.findByQuery(userId, boardId, isAccepted,
                lowerStartTime, upperStartTime, lowerEndTime, upperEndTime, pageable);
        Page<ReservationResponse> responseBody = reservations.map(ReservationResponse::build);

        return ResponseEntity.ok(responseBody);
    }

    @RequestMapping(value = ReservationControllerPath.DELETE, method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id, Principal principal) throws Exception {
        User user = getUser(principal);
        Reservation reservation = reservationService.findById(id);

        if(reservation == null) throw new NoSuchElementException("Reservation with specified ID not found");
        if(!user.getId().equals(reservation.getUser().getId()))
            throw new AccessPrivilegeNotEnoughException("Your account don't have access to modify this resource(s)");

        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = ReservationControllerPath.UPDATE_ADMIN, method = RequestMethod.PATCH)
    public ResponseEntity updateAdmin(@Valid @RequestBody ReservationAdminUpdateRequest reservationReq) throws Exception {
        Reservation reservation = reservationService.update(reservationReq.toReservation());
        SuccessResponse responseBody = new SuccessResponse(ReservationResponse.build(reservation));
        return ResponseEntity.ok(responseBody);
    }

//    @RequestMapping(value = ReservationControllerPath.UPDATE_CLIENT, method = RequestMethod.PATCH)
//    public ResponseEntity updateClient(@Valid @RequestBody ReservationCreateRequest reservationReq, Principal principal) throws Exception {
//        Reservation reservation = reservationService.update(reservationReq.toReservation());
//        return ResponseEntity.ok(new SuccessResponse(reservation));
//    }

}
