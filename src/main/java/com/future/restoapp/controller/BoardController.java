package com.future.restoapp.controller;

import com.future.restoapp.controller.path.BoardControllerPath;
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

@Tag(name = "Board")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardController extends BaseController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = BoardControllerPath.CREATE, method = RequestMethod.POST)
    public ResponseEntity create(@Valid @RequestBody Board boardReq) throws Exception {
        Board board = boardService.create(boardReq);

        return ResponseEntity.created(new URI("")).build();
    }

    @RequestMapping(value = BoardControllerPath.CHECK, method = RequestMethod.GET)
    public ResponseEntity check(@PathVariable String id,
                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startTime,
                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endTime
                      ) throws Exception {

        System.out.println(startTime);
        System.out.println(endTime);
        return ResponseEntity.ok().build();
//        boolean isAvailable = boardService.checkAvailable(id);
    }

    @RequestMapping(value = BoardControllerPath.FETCH_ALL, method = RequestMethod.GET)
    public ResponseEntity fetchAll() throws Exception {
        Collection<Board> boards = boardService.findAll();
        return ResponseEntity.ok(new SuccessResponse(boards));
    }

}
