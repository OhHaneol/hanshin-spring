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

    // 한 페
    private int startIndex = 0;
    // 한 페이지에 출력될 row 개수
    private int pageSize = 10;
    // 한 페이지에 페이징 번호가 몇 개까지 출력되게 할 것인지
    private int blockSize = 10;
    // 현재 페이지 번호
    private int page = 1;
    // 현재 블럭 번호
    private int block = 1;

}
