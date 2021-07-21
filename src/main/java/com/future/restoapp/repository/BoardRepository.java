package com.future.restoapp.repository;

import com.future.restoapp.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "SELECT b FROM Board b WHERE " +
            "b.id NOT IN (SELECT DISTINCT r.id FROM Reservation r WHERE " +
            "(:startTime <= r.startTime AND :endTime >= r.startTime) OR " +
            "(:startTime >= r.startTime AND :startTime <= r.endTime) OR " +
            "(:endTime >= r.startTime AND :endTime <= r.endTime) OR " +
            "(:startTime <= r.endTime AND :endTime >= r.endTime))")
    Collection<Board> findAllAvailableBoard(
            @NotNull LocalDateTime startTime, @NotNull LocalDateTime endTime
    );

}
