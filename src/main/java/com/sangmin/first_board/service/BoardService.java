package com.sangmin.first_board.service;

import com.sangmin.first_board.dto.BoardForm;
import com.sangmin.first_board.entity.Board;
import com.sangmin.first_board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


    public Board create(BoardForm boardForm) {
        Board board = boardForm.toEntity();
        if(board.getId()!=null){
            return null;
        }
        return boardRepository.save(board);
    }

    public Board update(Long id, BoardForm boardForm) {
        //수정용 엔티티 생성
        Board board = boardForm.toEntity();
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
        return boardRepository.save(target);


    }

    public Board delete(Long id) {
        Board target = boardRepository.findById(id).orElse(null);
        if (target == null) {
            return null;
        }
        boardRepository.delete(target);
        return target;
    }

    public List<Board> createBoard(List<BoardForm> dtos) {
        //dto 묶음을 Entity 묶음으로 변환
        List<Board> boardList= dtos.stream()
                .map(dto ->dto.toEntity())
                .collect(Collectors.toList());
        //Entity 묶음을 DB로 저장
        boardRepository.saveAll(boardList);
        //강제 예외 발생
        boardRepository.findById(-1L).orElseThrow(
                ()->new IllegalArgumentException("결재실패")
        );
        //결과값 발생
        return boardList;
    }
}
