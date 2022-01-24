package com.example.demo.board.controller;

import com.example.demo.board.model.BoardVO;
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

//            1. 파라미터를 가져와서
            @RequestParam(value = "srchType", defaultValue = "") String srchType,
            @RequestParam(value = "srchKeyword", defaultValue = "") String srchKeyword,
            Model model
    ) {

//        2. ArrayList 로 객체 생성하여 게시글을 쌓아논 다음에
        ArrayList<BoardVO> list = new ArrayList<>();

        for(int i=0;i<=10;i++) {
            //  객체 생성
            BoardVO boardVO = generate(i,i+"번 게시물", i + "번 게시물 내용", "관리자", 100);
            //  생성한 객체 ArrayList에 넣기(10번~)
            list.add(boardVO);
        }

        System.out.println("srchType" + srchType);
        System.out.println("srchKeyword" + srchKeyword);

//        3. ArrayList 를 결과용으로 하나 더 생성하여
        ArrayList<BoardVO> result = new ArrayList<>();

//        4. 파라미터가 제목(또는 본문)이라면 제목에 keyword를 포함하고 있을 때 result에 추가한다.
//        만약 srchType이 아무것도 지정되어 있지 않을 경우 result에다 list 전부를 집어넣어라.
        if(srchType.equals("title")) {
            for(BoardVO b:list) {
                if(b.getTitle().contains(srchKeyword)) result.add(b);
            }
        } else if(srchType.equals("content")) {
            for(BoardVO b:list) {
                if(b.getContent().contains(srchKeyword)) result.add(b);
            }
        } else {
            result.addAll(list);
        }

        model.addAttribute("srchType", srchType);
        model.addAttribute("srchKeyword", srchKeyword);
        model.addAttribute("result", result);

        //  게시글 검색에 입력해도 password 타입으로만 입력되고 검색 기능도 안됨.
        //  그런데 URI 창에다 직접 http://localhost:8080/board/list?srchType=title&srchKeyword=0%EB%B2%88 치면은 검색 결과 나옴
        //  확인해보기...

        return "board/list";
    }

    @RequestMapping("/mainView")
    public String boardView(
            @RequestParam(value = "id", defaultValue = "1") int id,
            @RequestParam(value = "srchType", defaultValue = "") String srchType,
            @RequestParam(value = "srchKeyword", defaultValue = "") String srchKeyword,
            Model model
    ) {

        ArrayList<BoardVO> list = new ArrayList<>();

        for(int i=0;i<=10;i++) {
            //  객체 생성
            BoardVO boardVO = generate(i,i+"번 게시물", i + "번 게시물 내용", "관리자", 100);
            //  생성한 객체 ArrayList에 넣기(10번~)
            list.add(boardVO);
        }

        model.addAttribute("srchType", srchType);
        model.addAttribute("srchKeyword", srchKeyword);
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
