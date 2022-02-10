package com.example.chapter6.mapper;

import com.example.chapter6.model.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardVO> selectBoardVO();

    BoardVO selectBoardVOById(int id);

    void updateBoardVO(BoardVO boardVO);

    void insertBoardVO(BoardVO boardVO);
}
