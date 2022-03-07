package com.portfolio.lagarto.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AuctionVo extends AuctionEntity {
    private String writernm;
    private String profile_img;
    private String categorynm;
    private String nicknm;
    private String buyernickname;
    private String mdt;

    private int bid;
    private int buyer;
    private int prebuy; //입력전 현재 입찰가
    private int icategory;



}
