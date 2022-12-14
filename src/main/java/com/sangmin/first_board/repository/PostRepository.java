package com.sangmin.first_board.repository;

import com.sangmin.first_board.entity.Post;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value =
            "SELECT * " +
            "FROM Post" +
            " WHERE board_id ",
            nativeQuery = true)
    List<Post> findByBoardId(Long boardId);

}
