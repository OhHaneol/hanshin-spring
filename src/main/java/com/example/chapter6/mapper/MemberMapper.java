package com.example.chapter6.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    Boolean duplicateId(String id);

}

