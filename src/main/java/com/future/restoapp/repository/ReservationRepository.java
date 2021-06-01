package com.future.restoapp.repository;

import com.future.restoapp.model.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r where " +
            "(r.user.id = :userId or :userId is null) and " +
            "(r.board.id = :boardId or :boardId is null) and " +
            "(r.isAccepted = :isAccepted or :isAccepted is null) and " +
            "r.startTime >= cast(:lowerStartTime as timestamp) and " +
            "r.startTime <= cast(:upperStartTime as timestamp) and " +
            "r.endTime >= cast(:lowerEndTime as timestamp) and " +
            "r.endTime <= cast(:upperEndTime as timestamp)")
    Page<Reservation> findByQuery(
            Long userId, Long boardId, Boolean isAccepted,
            @NotNull Date lowerStartTime, @NotNull Date upperStartTime,
            @NotNull Date lowerEndTime, @NotNull Date upperEndTime,
            Pageable pageable
        );

    @Query("SELECT r FROM Reservation r where " +
            "r.board.id = :boardId and " +
            "((:startTime <= r.startTime and :endTime >= r.startTime) or" +
            "(:startTime >= r.startTime and :startTime <= r.endTime) or" +
            "(:endTime >= r.startTime and :endTime <= r.endTime) or" +
            "(:startTime <= r.endTime and :endTime >= r.endTime))")
    Collection<Reservation> findBoardConflictedOnTime(
            @NotNull Long boardId,
            @NotNull Date startTime, @NotNull Date endTime
    );
}
