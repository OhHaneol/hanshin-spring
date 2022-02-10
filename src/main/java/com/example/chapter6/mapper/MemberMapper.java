package com.example.chapter6.mapper;

import com.example.chapter6.model.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    Boolean duplicateId(String id);

    Boolean duplicateEmail(String email);

    void insertMember(MemberVO memberVO);

    MemberVO loginProcess(MemberVO memberVO);

//     두 번째 단계. MemberService.java 에 똑같이 붙여넣기.
    String findUserId(MemberVO memberVO);

    String findPassword(MemberVO memberVO);

    void updatePassword(MemberVO memberVO);
}

