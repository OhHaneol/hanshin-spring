package com.example.chapter6.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SearchHelper {

    private int srchCode = 1000;
    private String srchType;
    private String srchKeyword;

    // 한 페이지에 출력될 row 개수
    private int pageSize = 10;
    // 한 페이지에 페이징 번호가 몇 개까지 출력되게 할 것인지
    private int blockSize = 10;
    // 현재 페이지 번호
    private int page = 1;
    // 현재 블럭 번호
    private int block = 1;
    // 전체 row 개수
    private int totalRowCnt;
    // 전체 페이지 수
    private int totalPageCnt;
    // 전체 블럭 수
    private int totalBlockCnt;
    // 블럭 시작 페이지
    private int startPage = 1;
    // 블럭 마지막 페이지
    private int endPage = 1;
    // sql 시작 index
    private int startIndex = 0;
    // 이전 블럭의 마지막 페이지
    private int prevBlock;
    // 다음 블럭의 시작 페이지
    private int nextBlock;

    public SearchHelper(int totalRowCnt, int page) {
        // 현재 페이지 번호
        setPage(page);
        // 전체 row 개수
        setTotalRowCnt(totalRowCnt);
        // 전체 페이지 수 = 총 row의 개수 / 한 페이지에 출력될 row의 개수
        setTotalPageCnt((int) Math.ceil(totalRowCnt * 1.0 / pageSize));
        // 총 블럭 수 = 총 페이지 수 / 페이지 숫자 목록을 한 번에 몇 개까지 출력할 것인지
        setTotalBlockCnt((int)Math.ceil(totalPageCnt * 1.0 / blockSize));
        // 현재 블럭 = 현재 페이지 번호 / 페이징 숫자 목록을 한 번에 몇 개까지 출력할 것인지
        setBlock((int) Math.ceil(page * 1.0 / blockSize));
        // 블럭 시작 페이지
        setStartPage((block - 1) * blockSize + 1);
        // 블럭 마지막 페이지
        setEndPage(startPage + blockSize - 1);

        // 마지막 페이지 번호가 전체 페이지 번호 카운트보다 크면 마지막 페이지에 전체 페이지 번호값을 삽입
        if(endPage > totalPageCnt) this.endPage = totalPageCnt;

        // 이전 블럭 클릭 시 이전 블럭의 마지막 페이지
        setPrevBlock((block * blockSize) - blockSize);

        // 이전 블럭 값이 1보다 작으면 prevBlock에 1 삽입
        if(prevBlock < 1) this.prevBlock = 1;

        // 다음 블럭 클릭 시 다음 블럭의 첫 페이지
        setNextBlock((block * blockSize) + 1);

        // 다음 블럭 클릭 시 nextBlock 번호가 전체 페이지 수보다 크면 nextBlock에 전체 페이지 카운트 수 삽입
        if(nextBlock > totalPageCnt) this.nextBlock = totalPageCnt;

        // 첫 페이지의 index
        setStartIndex((page - 1) * pageSize);


    }

}
