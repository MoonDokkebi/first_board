package com.sangmin.first_board.service;

import com.sangmin.first_board.dto.BoardDto;
import com.sangmin.first_board.entity.Board;
import com.sangmin.first_board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        Board board = boardDto.toEntity();
        if(board.getId()!=null){
            return null;
        }
        return boardRepository.save(board);
    }

    public Board update(Long id, BoardDto boardDto) {
        Board board = boardDto.toEntity();
        log.info("id : {}, board : {}", id,board.toString());
        //대상 엔티티를 조회
        Board target = boardRepository.findById(id).orElse(null);
        //잘못된 요청을 처리
        if(target==null || id != board.getId()){
            log.info("잘못된 응답요청 id : {}, board : {}", id, board.toString());
            return null;
        }
        //정상 요청 처리
        target.patch(board);
        Board updated = boardRepository.save(target);
        return updated;


    }

    public Board delete(Long id) {
        Board target = boardRepository.findById(id).orElse(null);
        if (target == null) {
            return null;
        }
        boardRepository.delete(target);
        return target;
    }
}
