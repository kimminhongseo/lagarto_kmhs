package com.portfolio.lagarto.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Data
public class AuctionEntity {
    private int iboard;
    private int icategory;
    private String title;
    private String ctnt;
    private int iuser;
    private String images;
    private int hits;
    private int isdel;
    private int buy;
    private int imbuy;
    private String rdt;
    private String mdt;
}
