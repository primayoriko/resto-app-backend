package com.future.restoapp.service;

import com.future.restoapp.model.entity.Board;

import java.time.LocalDateTime;
import java.util.Collection;

public interface BoardService {

    Board create(Board board);

    Collection<Board> findAll();

    Collection<Board> findAllAvailable(LocalDateTime startTime, LocalDateTime endTime);

    boolean checkAvailable(long id, LocalDateTime startTime, LocalDateTime endTime);

}
