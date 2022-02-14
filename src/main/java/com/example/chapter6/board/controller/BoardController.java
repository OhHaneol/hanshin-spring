package com.example.chapter6.board.controller;

import com.example.chapter6.model.BoardVO;
import com.example.chapter6.model.MemberVO;
import com.example.chapter6.model.Message;
import com.example.chapter6.model.SearchHelper;
import com.example.chapter6.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping("/list")
    public String boardList(
            @ModelAttribute SearchHelper searchHelper,
            Model model
    ) {

        List<BoardVO> result = boardService.selectBoardVO(searchHelper);

        model.addAttribute("searchHelpr", searchHelper);    // 값 유지하기 위해서.
        model.addAttribute("result", result);

        return "board/list";
    }

    @RequestMapping("/modify")
    public String boardModify(
            @RequestParam(value = "id", defaultValue = "0") int id,
            @ModelAttribute SearchHelper searchHelper,
            Model model
    ) {

        if (id > 0) {
            // 게시물 조회
            BoardVO boardVO = boardService.selectBoardVOById(id);
            model.addAttribute("boardVO", boardVO);
            model.addAttribute("searchHelper", searchHelper);
        } else {
            Message message = new Message();
            message.setMessage("게시글이 없습니다.");
            message.setHref("/board/list?srchCode=" + searchHelper.getSrchCode() + "$srchType=" + searchHelper.getSrchType() + "$srchKeyword=" + searchHelper.getSrchKeyword());
            model.addAttribute("data", message);
            return "message/message";
        }

        return "board/write";
    }

    @RequestMapping("/view")
    public String boardView(
            @RequestParam(value = "id", defaultValue = "0") int id,
            Model model
    ) {

        if (id > 0) {
            // 게시물 조회
            BoardVO boardVO = boardService.selectBoardVOById(id);
            model.addAttribute("boardVO", boardVO);
        } else {
            Message message = new Message();
            message.setMessage("게시글이 없습니다.");
            message.setHref("/board/list");
            model.addAttribute("data", message);
            return "message/message";
         }

        return "board/view";
    }

    @RequestMapping("/write")
    public String boardWrite(Model model) {
        BoardVO boardVO = new BoardVO();
        boardVO.setCode(1000);
        model.addAttribute("boardVO", boardVO);
        return "board/write";
    }

    @PostMapping("/save")
    public String boardSave(
            @ModelAttribute BoardVO boardVO,
            HttpServletRequest request,
            Model model     // MemberController 에서 ModelAndView 랑 비슷. 그렇게 해도 되고 이렇게 해도 되고!
            ) {

        HttpSession session = request.getSession();
        MemberVO sessionResult = (MemberVO) session.getAttribute("memberVO");

        // 로그인 여부(세션 유무)에 따라서 게시글 생성 가능/불가능
        if(sessionResult != null) {
            // 저장
            String userId = sessionResult.getUserId();

            // code, title, content, userId
            // 오류나는 부분 ch.7 배포 파일과 비교해서 해결... >> Getter, Setter Annotation 안 함... 해결!
            boardVO.setRegId(userId);

            if(boardVO.getId() > 0) {
                // 수정
                boardService.updateBoardVO(boardVO);
            } else {
                // 저장
                boardService.insertBoardVO(boardVO);
            }
        } else {
            // 세션 없음
            model.addAttribute("data", new Message("로그인 후 이용하세요.", "/member/login"));
            return "message/message";
       }

        return "redirect:/board/list";
    }

    /**
     * 배열 형태로 게시물 삭제
     * @param del
     * @param model
     * @return
     */
    @PostMapping("/delete")
    public String delete(
            @RequestParam(value = "del[]", defaultValue = "") int[] del,
            Model model
    ) {
        logger.info("삭제 배열 -{}", del);

        Message message = new Message();

        if(del.length > 0) {
            for (int i = 0; i<del.length; i++) {
                boardService.deleteById(del[i]);
            }
            message.setMessage("삭제되었습니다.");
        } else {
            message.setMessage("삭제할 게시물을 선택하세요.");
        }

        model.addAttribute("data", message);
        return "redirect:/board/list";
    }

}
