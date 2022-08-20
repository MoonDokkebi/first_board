package com.sangmin.first_board.service;

import com.sangmin.first_board.dto.PostDto;
import com.sangmin.first_board.entity.Board;
import com.sangmin.first_board.entity.Post;
import com.sangmin.first_board.repository.BoardRepository;
import com.sangmin.first_board.repository.PostRepository;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private BoardRepository boardRepository;

    public List<PostDto> posts(Long boardId) {
        //조회 : 게시판 목록
        List<Post> posts = postRepository.findByBoardId(boardId);

        //변환
        List<PostDto> dtos = new ArrayList<PostDto>();
        for (Post i : posts){
            PostDto dto = PostDto.createPostDto(i);
            dtos.add(dto);
        }
        return dtos;
    }
    @Transactional
    public PostDto create(Long boardId, PostDto dto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 생성 실패! 대상 게시판이 없습니다."));
        //게시글 엔티티 생성
        Post post = Post.createPost(dto, board);
        //게시글 엔티티르 DB로 저장
        Post create = postRepository.save(post);
        //DTO를 변경하여 반환
        return PostDto.createPostDto(create);
    }
    @Transactional
    public PostDto update(Long id, PostDto dto) {
        Post target = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글 수정 실패! 대상 게시판이 없습니다."));
        target.patch(dto);
        Post update = postRepository.save(target);
        return PostDto.createPostDto(target);
    }
    @Transactional
    public PostDto delete(Long id) {
        Post target = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글 삭제 실패! 대상 게시판이 없습니다."));
        postRepository.delete(target);
        return PostDto.createPostDto(target);
    }

}
