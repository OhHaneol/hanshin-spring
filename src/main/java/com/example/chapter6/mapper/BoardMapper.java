package com.example.chapter6.mapper;

import com.example.chapter6.model.BoardVO;
import com.example.chapter6.model.SearchHelper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardVO> selectBoardVO(SearchHelper searchHelper);

    BoardVO selectBoardVOById(int id);

    void updateBoardVO(BoardVO boardVO);

    void updateCount(int id);

    void deleteById(int id);

    void insertBoardVO(BoardVO boardVO);
}
