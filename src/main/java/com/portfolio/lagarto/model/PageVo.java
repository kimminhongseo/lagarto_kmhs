package com.portfolio.lagarto.model;

import lombok.Data;

@Data
public class PageVo{
    private int recordCount = 1;
    private int currentPage = 5;
    private int startIdx =1;
}
