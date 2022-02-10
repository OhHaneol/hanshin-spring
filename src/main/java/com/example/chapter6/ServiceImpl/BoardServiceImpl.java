package com.example.chapter6.ServiceImpl;

import com.example.chapter6.mapper.BoardMapper;
import com.example.chapter6.model.BoardVO;
import com.example.chapter6.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

    private BoardMapper boardMapper;

    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }


    @Override
    public void insertBoardVO(BoardVO boardVO) {
        boardMapper.insertBoardVO(boardVO);
    }
}
