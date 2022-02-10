package com.example.chapter6.member.controller;

import com.example.chapter6.model.MemberVO;
import com.example.chapter6.model.Message;
import com.example.chapter6.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {

    private Logger logger = LoggerFactory.getLogger(MemberController.class);

    //  memberController 에서 membereMapper 로 바로 접근하는 게 아니라 memberService 를 거쳐야 함.
    //  이렇게 해서 memberService 를 바로 호출할 수 있음.
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String memberLogin() {
        return "member/login";
    }

    @GetMapping("/join")
    public String mebmerJoin() {
        return "member/join";
    }


    /*
    * 회원 가입 처리
    * @param memberVO
    * @param errors
    * @param model
    * @return
    * */
    @PostMapping("/join")
    public String memberJoinPost(
//            @RequestParam(value = "userId", defaultValue = "") String userId
            @Valid MemberVO memberVO, Errors errors, Model model
    ) throws Exception {

        if (errors.hasErrors()) {
            Map<String, String> validate = memberService.formValidation(errors);

            for(String key : validate.keySet()) {
                logger.info(key, validate.get(key));
                //  controller 에서 view 로 데이터를 넘기려면? model!
                model.addAttribute(key, validate.get(key));
            }

            return "member/join";

        }

        //  아이디 중복 체크
        boolean idCheck = memberService.duplicateId(memberVO.getUserId());
        //  이메일 중복 체크
        boolean emailCheck = memberService.duplicateEmail(memberVO.getEmail());
        //  만약 아이디와 이메일 모두 중복이 아니라면 회원 정보 추가해줌.
        if(!idCheck && !emailCheck) memberService.insertMember(memberVO);

        //  회원가입 후 login 페이지를 반환해줌. 만약 member/login 으로만 하면 여긴 view?를 뿌리는거라 회원가입 다시 나와서 아래처럼 redirect 로 도메인 뒤에 페이지로 가라~ 해야 함.
        return "redirect:/member/login";
    }

    /**
     * 로그인 처리
     * @param userId
     * @param password
     * @return
     */
    @PostMapping("/loginProcess")
    public String loginProcess(
            @RequestParam(value = "userId", defaultValue = "") String userId,
            @RequestParam(value = "password", defaultValue = "") String password,
            HttpServletRequest request
    ) {
        if(!userId.equals("") && !password.equals("")) {
            MemberVO memberVO = new MemberVO();
            memberVO.setUserId(userId);
            memberVO.setPassword(password);

            Boolean result = memberService.loginProcess(memberVO, request);

            logger.info("로그인 ={}", result);

            //  로그인 실패 시 다시 로그인 페이지 로딩
            if(result == false) {
                return "redirect:/member/login";
            }

            //  로그인 완료되면 메인페이지로(아직 메인페이지 안돼서 list로)
            return "redirect:/board/list";
        }
        //  로그인 실패 시 다시 로그인 페이지 로딩
        return "redirect:/member/login";
    }

    /**
     * 아이디 찾기 페이지
     * @return
     */
    @GetMapping("/find_id")
    public String findId() {
        return "member/find_id";
    }

    @PostMapping("/find_id")
    public ModelAndView findIdPost(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "email", defaultValue = "") String email,
            ModelAndView mav
    ) throws IOException {

        if (!name.equals("") && !email.equals("")) {
            // 전달받은 name과 email 을 통해서 memberVO 생성
            MemberVO memberVO = new MemberVO();
            memberVO.setName(name);
            memberVO.setEmail(email);

            // findUserId 통해서 id 찾기.
            String id = memberService.findUserId(memberVO);

            logger.info("찾은 id -{}", id);

            // 찾은 id 띄워주기.
            if (id == null) {
                // 찾는 ID 가 없습니다.
                mav.addObject("data", new Message("찾으시는 계정이 없습니다.", "/member/find_id"));
                mav.setViewName("message/message");     // messege 폴더의 message html 에 던져라
                return mav;
            } else {
                // 찾는 id가 있습니다. id=abcd 로 가정
                // 끝 두 자리는 ** 로 마스킹해서 전달

                int idLength = id.length();         // 4
                id = id.substring(0, idLength -2);  // ab
                id += "**";                         // ab**

                logger.info("id 마스킹 -{}", id);
                mav.addObject("data", new Message(name + "님이 찾으시는 ID는 " + id + "입니다.", "/member/login"));
                mav.setViewName("message/message");     // messege 폴더의 message html 에 던져라
                return mav;
            }

        }

        mav.addObject("data", new Message("이름과 이메일을 확인하세요.", "/member/login"));
        mav.setViewName("message/message");     // messege 폴더의 message html 에 던져라
        return mav;

    }

    @RequestMapping("/find_pw")
    public String findPw() {
        return "member/find_pw";
    }

    /**
     * 로그아웃
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        //  세션 삭제
        HttpSession session = request.getSession(false);
        if(session != null) session.invalidate();

        return "redirect:/member/login";
    }

}

