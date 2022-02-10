package com.example.chapter6.mapper;

import com.example.chapter6.model.BoardVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    void insertBoardVO(BoardVO boardVO);
}
