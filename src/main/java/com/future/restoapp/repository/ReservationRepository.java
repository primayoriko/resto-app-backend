package com.future.restoapp.repository;

import com.future.restoapp.domain.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE " +
            "(r.user.id = :userId OR :userId  IS NULL) AND " +
            "(r.board.id = :boardId OR :boardId  IS NULL) AND " +
            "(r.isAccepted = :isAccepted OR :isAccepted  IS NULL) AND " +
            "r.startTime >= :lowerStartTime AND " +
            "r.startTime <= :upperStartTime AND " +
            "r.endTime >= :lowerEndTime AND " +
            "r.endTime <= :upperEndTime")
    Page<Reservation> findByQuery(
            Long userId, Long boardId, Boolean isAccepted,
            @NotNull LocalDateTime lowerStartTime, @NotNull LocalDateTime upperStartTime,
            @NotNull LocalDateTime lowerEndTime, @NotNull LocalDateTime upperEndTime,
            Pageable pageable
        );

    @Query("SELECT r FROM Reservation r WHERE " +
            "r.board.id = :boardId AND " +
            "((:startTime <= r.startTime AND :endTime >= r.startTime) OR " +
            "(:startTime >= r.startTime AND :startTime <= r.endTime) OR " +
            "(:endTime >= r.startTime AND :endTime <= r.endTime) OR " +
            "(:startTime <= r.endTime AND :endTime >= r.endTime))")
    Collection<Reservation> findBoardConflictedOnTime(
            @NotNull Long boardId,
            @NotNull LocalDateTime startTime, @NotNull LocalDateTime endTime
    );

}
