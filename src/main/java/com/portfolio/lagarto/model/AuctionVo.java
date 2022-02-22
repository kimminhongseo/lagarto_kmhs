package com.portfolio.lagarto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuctionVo extends AuctionEntity {
    private String writernm;
    private String profile_img;
    private String categorynm;
    private String nicknm;
    private String buyernickname;

    private int prebuy; //입력전 현재 입찰가



}
