package com.sangmin.first_board.api;

import com.sangmin.first_board.dto.PostDto;
import com.sangmin.first_board.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
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
        log.info("id : {}, post : {}", boardId, dto.toString());
        PostDto createDto = postService.create(boardId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }
    //게시글 수정
    @PatchMapping("/api/posts/{id}")
    public ResponseEntity<PostDto>update(@PathVariable Long id,
                                         @RequestBody PostDto dto){
        log.info("id : {}, post : {}",id,dto.toString());
        // 서비스에게 위임
        PostDto updatedDto = postService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);

    }


    //게시글 삭제
    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<PostDto>delete(@PathVariable Long id){
        PostDto deletDto = postService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletDto);
    }

}
