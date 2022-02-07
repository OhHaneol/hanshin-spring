package com.example.chapter6.service;

import com.example.chapter6.model.MemberVO;
import org.springframework.validation.Errors;

import java.util.Map;

public interface MemberService {

    Boolean duplicateId(String id);

    Boolean duplicateEmail(String email);

    void insertMember(MemberVO memberVO);

    Map<String, String> formValidation(Errors errors);
}
