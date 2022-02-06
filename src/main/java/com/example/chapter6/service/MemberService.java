package com.example.chapter6.service;

import org.springframework.validation.Errors;

import java.util.Map;

public interface MemberService {

    Boolean duplicateId(String id);

    Map<String, String> formValidation(Errors errors);
}
