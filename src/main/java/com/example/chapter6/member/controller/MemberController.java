package com.example.chapter6.member.controller;

import com.example.chapter6.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    ) {

        if (errors.hasErrors()) {
            Map<String, String> validate = memberService.formValidation(errors);

            for(String key : validate.keySet()) {
                logger.info(key, validate, get(key)); 
            }

        }
//        if(!userId.equals("")) {
//            Boolean res = memberService.duplicateId(userId);
//            logger.info("가입여부 -{}", res);
//        }

        return "member/join";
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

