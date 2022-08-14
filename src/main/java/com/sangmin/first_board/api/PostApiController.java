package com.sangmin.first_board.api;

import com.sangmin.first_board.dto.PostDto;
import com.sangmin.first_board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostApiController {
    @Autowired
    private PostService postService;
    //목록조회
    @GetMapping("/api/boards/{boardId}/comments")
    public ResponseEntity<List<PostDto>>posts(@PathVariable Long boardId){
        List<PostDto> dtos = postService.posts(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    //게시글 작성
    @PostMapping("/api/boards/{boardId}/posts")
    public ResponseEntity<PostDto> create (@PathVariable Long boardId,
                                           @RequestBody PostDto dto){
        PostDto createDto = postService.create(boardId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }
    //게시글 수정

    //게시글 삭제

}
