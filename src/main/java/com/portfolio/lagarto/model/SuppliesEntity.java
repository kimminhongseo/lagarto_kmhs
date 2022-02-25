package com.portfolio.lagarto.model;


import lombok.Data;

@Data
public class SuppliesEntity {
    private int iboard;
    private int icategory;
    private String title;
    private String ctnt;
    private int iuser; //
    private int hits;
    private int isdel;
    private int price; // 구매가
    private String rdt;
    private String mdt;
}
