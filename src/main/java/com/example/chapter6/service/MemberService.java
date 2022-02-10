package com.example.chapter6.service;

import com.example.chapter6.model.MemberVO;
import org.springframework.validation.Errors;

import java.util.Map;

public interface MemberService {

    Boolean duplicateId(String id);

    Boolean duplicateEmail(String email);

    void insertMember(MemberVO memberVO) throws Exception;     // 예외가 발생할 수 있다면 throws Exception 을 여기와 MemberServicelmpl.java 파일에 적용

    MemberVO loginProcess(MemberVO memberVO);

    Map<String, String> formValidation(Errors errors);
}
