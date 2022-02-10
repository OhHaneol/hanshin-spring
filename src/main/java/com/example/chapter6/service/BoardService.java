package com.example.chapter6.service;

import com.example.chapter6.model.BoardVO;

import java.util.List;

public interface BoardService {

    List<BoardVO> selectBoardVO();

    BoardVO selectBoardVOById(int id);

    void updateBoardVO(BoardVO boardVO);

    void insertBoardVO(BoardVO boardVO);

}
