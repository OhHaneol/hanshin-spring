package com.example.demo.board.controller;

import com.example.demo.board.controller.model.BoardVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
@RequestMapping("/board")
public class BoardController {

    @RequestMapping("/list")
    public String boardList(
            Model model
    ) {

        ArrayList<BoardVO> list = new ArrayList<>();

        for(int i=0;i<=10;i++) {
            //  객체 생성
            BoardVO boardVO = generate(i,i+"번 게시물", i + "번 게시물 내용", "관리자", 100);
            //  생성한 객체 ArrayList에 넣기(10번~)
            list.add(boardVO);
        }

        model.addAttribute("result", list);

        return "board/list";
    }

    @RequestMapping("/mainView")
    public String boardView(
            @RequestParam(value = "id", defaultValue = "1") int id,
            Model model
    ) {

        ArrayList<BoardVO> list = new ArrayList<>();

        for(int i=0;i<=10;i++) {
            //  객체 생성
            BoardVO boardVO = generate(i,i+"번 게시물", i + "번 게시물 내용", "관리자", 100);
            //  생성한 객체 ArrayList에 넣기(10번~)
            list.add(boardVO);
        }

        model.addAttribute("result", list.get(id));

        return "board/mainView";
    }

    @RequestMapping("/write")
    public String boardWrite(Model model) {
        return "board/write";
    }

    public BoardVO generate(int id, String title, String content, String writer, int count) {
        BoardVO boardVO = new BoardVO();

        boardVO.setId(id);
        boardVO.setTitle(title);
        boardVO.setContent(content);
        boardVO.setWriter(writer);
        boardVO.setRegDate(LocalDateTime.of(2021,1,1,12,30,30));
        boardVO.setCount(count);

        return boardVO;
    }

}
