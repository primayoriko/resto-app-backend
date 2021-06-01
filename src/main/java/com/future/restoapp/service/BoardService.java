package com.future.restoapp.service;

import com.future.restoapp.model.entity.Board;

import java.time.LocalDateTime;
import java.util.Collection;

public interface BoardService {

    Board create(Board board) throws Exception;

    Board update(Board board) throws Exception;

    Collection<Board> findAll() throws Exception;

    Collection<Board> findAllAvailable(LocalDateTime startTime, LocalDateTime endTime) throws Exception;

    boolean checkAvailable(long id, LocalDateTime startTime, LocalDateTime endTime) throws Exception;

}
