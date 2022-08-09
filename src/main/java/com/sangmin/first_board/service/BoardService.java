package com.sangmin.first_board.service;

import com.sangmin.first_board.dto.BoardDto;
import com.sangmin.first_board.entity.Board;
import com.sangmin.first_board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public List<Board> index() {
        return boardRepository.findAll();
    }
    public Board show(Long id){
        return boardRepository.findById(id).orElse(null);
    }


    public Board create(BoardDto boardDto) {
    }
}
