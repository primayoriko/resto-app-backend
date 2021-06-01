package com.future.restoapp.controller;

import com.future.restoapp.controller.path.BoardControllerPath;
import com.future.restoapp.model.dto.BoardCreateRequest;
import com.future.restoapp.model.dto.BoardResponse;
import com.future.restoapp.model.dto.BoardUpdateRequest;
import com.future.restoapp.model.dto.SuccessResponse;
import com.future.restoapp.model.entity.Board;
import com.future.restoapp.service.BoardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Tag(name = "Board")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardController extends BaseController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = BoardControllerPath.CREATE, method = RequestMethod.POST)
    public ResponseEntity create(@Valid @RequestBody BoardCreateRequest boardReq) throws Exception {
        boardService.create(boardReq.toBoard());
        return ResponseEntity.created(new URI(BoardControllerPath.FETCH_ALL)).build();
    }

    @RequestMapping(value = BoardControllerPath.FETCH_ALL, method = RequestMethod.GET)
    public ResponseEntity fetchAll() throws Exception {
        Collection<Board> boards = boardService.findAll();
        Collection<BoardResponse> responseBody = boards
                .stream()
                .map(BoardResponse::build)
                .collect(Collectors.toSet());
        return ResponseEntity.ok(new SuccessResponse(responseBody));
    }

    @RequestMapping(value = BoardControllerPath.UPDATE, method = RequestMethod.PATCH)
    public ResponseEntity update(@Valid @RequestBody BoardUpdateRequest boardReq) throws Exception {
        boardService.update(boardReq.toBoard());
        return ResponseEntity.created(new URI(BoardControllerPath.FETCH_ALL)).build();
    }

    @RequestMapping(value = BoardControllerPath.CHECK, method = RequestMethod.GET)
    public ResponseEntity check(@PathVariable Long id,
                                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startTime,
                                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endTime
    ) throws Exception {
        boolean result = boardService.checkIfAvailable(id, startTime, endTime);
        return ResponseEntity.ok(new SuccessResponse(result));
    }

}
