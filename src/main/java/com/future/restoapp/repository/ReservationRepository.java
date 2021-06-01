package com.future.restoapp.repository;

import com.future.restoapp.model.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

//    @Query("SELECT r FROM reservation r where r.creationDateTime <= :creationDateTime")
//    Page<Reservation> findByStartTimeBeforeAndEndTimeAfterOrderByIsAcceptedAscStartTimeAsc(LocalDateTime start, LocalDateTime end, Pageable pageable);
    Page<Reservation> findByStartTimeBeforeAndEndTimeAfterOrderByIsAcceptedAscStartTimeAsc(Date startTime, Date endTime, Pageable pageable);

}
