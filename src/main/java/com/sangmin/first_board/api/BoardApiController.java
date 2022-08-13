package com.sangmin.first_board.api;

import com.sangmin.first_board.dto.BoardForm;
import com.sangmin.first_board.entity.Board;
import com.sangmin.first_board.service.BoardService;
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
    @PostMapping("api/boards")
    public ResponseEntity<Board> create(@RequestBody BoardForm dto){
        Board create = boardService.create(dto);
        return (create != null) ?
                ResponseEntity.status(HttpStatus.OK).body(create) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //patch
    @PatchMapping("/boards/{id}")
    public ResponseEntity<Board> update(@PathVariable Long id,
                                        @RequestBody BoardForm dto){
        Board updated = boardService.update(id, dto);
        return (updated!=null)?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Board> delete(@PathVariable Long id){
        Board deleted = boardService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    //트랜잭션 -> 실패 -> 롤백
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Board>> transectionTest(@RequestBody List<BoardForm> dtos){
        List<Board> createdList = boardService.createBoard(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
