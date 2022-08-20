package com.sangmin.first_board.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sangmin.first_board.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class PostDto {
    private Long postId;
    @JsonProperty("board_id")
    private Long boardId;
    private String postTitle;
    private String nickname;
    private String body;

    public static PostDto createPostDto(Post post) {
        return new PostDto(
                post.getPostId(),
                post.getBoard().getId(),
                post.getPostTitle(),
                post.getNickname(),
                post.getBody()
        );
    }
}
