package com.future.restoapp.repository;

import com.future.restoapp.model.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r where " +
            "r.startTime <= cast(:startTime as timestamp) and " +
            "r.endTime >= cast(:endTime as timestamp) and " +
            "(r.isAccepted = :isAccepted or :isAccepted is null) and " +
            "(r.user.id = :userId or :userId is null)")
    Page<Reservation> findByQuery(
            Long userId, Boolean isAccepted,
            Date startTime, Date endTime, Pageable pageable
        );
//    Page<Reservation> findByStartTimeBeforeAndEndTimeAfterOrderByIsAcceptedAscStartTimeAsc(Date startTime, Date endTime, Pageable pageable);

}
