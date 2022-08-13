package com.sangmin.first_board.dto;

import com.sangmin.first_board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class BoardForm {
    private Long id;
    private String title;
    private String explain;

    public Board toEntity(){
        return new Board(id, title, explain);
    }

}
