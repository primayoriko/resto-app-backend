package com.future.restoapp.service;

import com.future.restoapp.model.entity.Board;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;

public interface BoardService {

    Board create(@NotNull Board board) throws Exception;

    Collection<Board> findAll() throws Exception;

    Collection<Board> findAllAvailable(@NotNull LocalDateTime startTime,
                                       @NotNull LocalDateTime endTime) throws Exception;

    boolean checkIfAvailable(long id, @NotNull LocalDateTime startTime,
                             @NotNull LocalDateTime endTime) throws Exception;

    Board update(@NotNull Board board) throws Exception;

}
