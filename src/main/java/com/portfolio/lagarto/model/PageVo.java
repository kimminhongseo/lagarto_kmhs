package com.portfolio.lagarto.model;

import lombok.Data;

@Data
public class PageVo extends UserEntity{
    private int currentPage;
    private int recordCount=5; //0,5 5,10
    private int startIdx=1;
}
