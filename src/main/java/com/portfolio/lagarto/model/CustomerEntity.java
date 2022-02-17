package com.portfolio.lagarto.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerEntity {
    private int iboard;
    private int board_cd;
    private int product_cd;
    private String title;
    private String ctnt;
    private String ctnt_img;
    private int iuser;
    private String rdt;
    private String mdt;
    private int hits;
    private int isdel;
    private int rating;
}
