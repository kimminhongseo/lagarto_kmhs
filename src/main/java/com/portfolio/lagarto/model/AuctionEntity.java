package com.portfolio.lagarto.model;


import lombok.Data;

@Data
public class AuctionEntity {
    private int iboard;
    private int icategory;
    private String title;
    private String ctnt;
    private int iuser;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private int hits;
    private int isdel;
    private int buy;
    private int imbuy;
    private String rdt;
    private String mdt;
}
