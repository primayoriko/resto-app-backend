package com.future.restoapp.service;

import com.future.restoapp.model.entity.Board;
import com.future.restoapp.model.entity.Reservation;
import com.future.restoapp.repository.BoardRepository;
import com.future.restoapp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Board create(@NotNull Board board) throws Exception {
        return boardRepository.save(board);
    }

    @Override
    public Board update(@NotNull Board board) throws Exception {
        Board boardDb = boardRepository
                .findById(board.getId())
                .orElseThrow(() -> new NoSuchElementException("Board with specified ID not found"));
        boardDb.update(board);
        return boardRepository.save(boardDb);
    }

    @Override
    public Collection<Board> findAll() throws Exception {
        return boardRepository.findAll();
    }

    @Override
    public Collection<Board> findAllAvailable(@NotNull LocalDateTime startTime,
                                              @NotNull LocalDateTime endTime) throws Exception {
        if(startTime == null) startTime = LocalDateTime.now().minusYears(2000);
        if(endTime == null) endTime = LocalDateTime.now().plusYears(9999);

        return boardRepository.findAllAvailableBoard(startTime, endTime);
    }

    @Override
    public boolean checkIfAvailable(long id, @NotNull LocalDateTime startTime,
                                    @NotNull LocalDateTime endTime) throws Exception {
        if(startTime == null) startTime = LocalDateTime.now().minusYears(2000);
        if(endTime == null) endTime = LocalDateTime.now().plusYears(9999);

        Collection<Reservation> reservations = reservationRepository.findBoardConflictedOnTime(id, startTime, endTime);

        return reservations.isEmpty();
    }

}
