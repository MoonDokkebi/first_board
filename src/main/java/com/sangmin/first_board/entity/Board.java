package com.sangmin.first_board.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String explain;


    public boolean patch(Board board) {
        if(board.title != null){
            this.title=board.title;
            return true;
        }
        if (board.explain != null){
            this.explain = board.explain;
            return true;
        }
        return false;
    }
}
