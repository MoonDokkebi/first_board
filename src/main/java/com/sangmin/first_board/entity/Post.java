package com.sangmin.first_board.entity;


import com.sangmin.first_board.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Board board;

    @Column
    private String postTitle;

    @Column
    private String nickname;

    @Column
    private String body;


    public static Post createPost(PostDto dto, Board board) {
        if(dto.getId() != null){
            throw new IllegalArgumentException("게시글 생성 실패 댓글의 ID 가있어야 합니다.");
        }
        if(dto.getBoardId() != board.getId()){
            throw new IllegalArgumentException("댓글 생성 실패 ! 게시판의 id가 잘못되었습니다.");
        }

        //엔팉티 생성및 반환
        return new Post(
                dto.getId(),
                board,
                dto.getPostTitle(),
                dto.getNickname(),
                dto.getBody()
        );
    }
}
