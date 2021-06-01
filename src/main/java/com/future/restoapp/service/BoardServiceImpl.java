package com.future.restoapp.service;

import com.future.restoapp.model.entity.Board;
import com.future.restoapp.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Board create(Board board) throws Exception {
        return boardRepository.save(board);
    }

    @Override
    public Board update(Board board) throws Exception {
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
    public Collection<Board> findAllAvailable(LocalDateTime startTime, LocalDateTime endTime) throws Exception {
        return null;
    }

    @Override
    public boolean checkAvailable(long id, LocalDateTime startTime, LocalDateTime endTime) throws Exception {
        return true;
    }

}
