package com.sangmin.first_board.Controller;

import com.sangmin.first_board.dto.BoardDto;
import com.sangmin.first_board.entity.Board;
import com.sangmin.first_board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j // 로깅을 쓰기위한 어노테이션
public class BoardController {
    @Autowired //스프링 부트가 미리 생성해놓는 객페를 가져다 자동으로 연결
    private BoardRepository boardRepository;
    //@Autowired
    //private CommentService commentService;

    @GetMapping("/boards/new")
    public String newBoardDto(){
        return "boards/new";
    }
    @PostMapping("/boards/create")
    public String createBoard(BoardDto boardDto){
        log.info(boardDto.toString());
        // System.out.println(boardDto.toString()); -> sout를 로깅기능으로 대체
        //1.Dto 를 Entity로 변환
        Board board = boardDto.toEntity();
        log.info(board.toString());

        //2. 리포지토리에게 엔티티를  DB 안에 저장하게함
        Board saved = boardRepository.save(board);
        log.info(saved.toString());
        return "redirect:/boards/"+saved.getId();
    }
    @GetMapping("/boards/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = "+id);
        // 1 : id로 데이터를 가져옴!
        Board boardEntity =boardRepository.findById(id).orElse(null);//Id 값으로 찾는데 없으면 null 반환
        //Optional<Board> boardEntity =boardRepository.findById(id); 이런 방식도 가능 (JAVA 8의 optional)

        //List<CommentDto> commentDtos = commentService.comments(id);

        // 2 : 가져온 데이터를 모델에 등록!
        model.addAttribute("board", boardEntity);
       // model.addAttribute("commentDtos", commentDtos);

        // 3 : 보여줄 페이지를 설정!
        return "boards/show";
    }

    @GetMapping("/boards")
    public String index(Model model){

        //1. 모든 Board을  가져온다ㅣ
        List<Board> boardEntityList =  boardRepository.findAll();
        //2: 가져온 Board 묶음을 뷰로 전당ㄹ
        model.addAttribute("boardList",boardEntityList);

        //3. 뷰 페이지를 설정

        return "boards/index";
    }

    @GetMapping("/boards/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터를 가져오기
        Board boardEntity = boardRepository.findById(id).orElse(null);

        //모델에 데이터 등록
        model.addAttribute("board", boardEntity);

        //뷰 페이지 설정
        return "boards/edit";
    }

    @PostMapping("/boards/update")
    public String update(BoardDto boardDto){
        log.info(boardDto.toString());

        //1.DTO 를 엔티티로 변환
        Board boardEntity = boardDto.toEntity();
        log.info(boardEntity.toString());

        //2. 엔티티를 DB로 저장 한다.
        //2-1 DB에서 기존 데이터를 가져온다
        Board target = boardRepository.findById(boardEntity.getId()).orElse(null);

        //2-1 DB에서 기존데이터가 있다면 값을 갱신한다.
        if(target != null){
            boardRepository.save(boardEntity);
        }
        //3. 수정 셜과 페이지로 리다이렉트 한다.

        return "redirect:/boards/"+ boardEntity.getId();

    }

    @GetMapping("/boards/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redi){
        log.info("삭제 요청");

        //1. 삭제 대상은 가져온다
        Board target = boardRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 대상을 삭제한다.
        if (target != null){
            boardRepository.delete(target);
            redi.addFlashAttribute("msg", "삭제가 완료되었습니다.");
        }

        //결과 페이지를 요청한다.
        return "redirect:/boards";
    }
}

