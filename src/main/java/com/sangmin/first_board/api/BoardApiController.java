package com.sangmin.first_board.api;

import com.sangmin.first_board.dto.BoardDto;
import com.sangmin.first_board.entity.Board;
import com.sangmin.first_board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardApiController {
    @Autowired
    private BoardService boardService;

    //GET
    @GetMapping("/api/boards")
    public List<Board> index(){
        return boardService.index();
    }
    @GetMapping("/api/boards/{id}")
    public Board show(@PathVariable Long id){
        return boardService.show(id);
    }

    //POST
    @PostMapping("/api/boards")
    public ResponseEntity<Board> create(@RequestBody BoardDto boardDto){
        Board create = boardService.create(boardDto);
        return (create != null) ?
                ResponseEntity.status(HttpStatus.OK).body(create) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
