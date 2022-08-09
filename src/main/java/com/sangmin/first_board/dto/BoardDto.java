package com.sangmin.first_board.dto;

import com.sangmin.first_board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class BoardDto {
    private Long id;
    private String title;
    private String explan;

    public Board toEntity(){
        return new Board(id, title, explan);
    }

}
