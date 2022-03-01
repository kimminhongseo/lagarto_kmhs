package com.portfolio.lagarto.model;

import com.portfolio.lagarto.CommonDTO;
import lombok.Data;

import java.util.List;

@Data
public class TestDto extends CommonDTO {
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
    private String secretYn;
    private String changeYn;
    private List<Integer> fileIdxs;
}
