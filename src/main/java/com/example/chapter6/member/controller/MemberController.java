package com.example.chapter6.member.controller;

import com.example.chapter6.model.MemberVO;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    @PostMapping("/loginProcess")
    public String loginProcess(
            @RequestParam(value = "userId", defaultValue = "") String userId,
            @RequestParam(value = "password", defaultValue = "") String password
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

    @RequestMapping("/find_id")
    public String findId() {
        return "member/find_id";
    }

    @RequestMapping("/find_pw")
    public String findPw() {
        return "member/find_pw";
    }

}

