package com.portfolio.lagarto.model;


import lombok.Data;

@Data
public class SuppliesVo extends SuppliesEntity {

    private String writernm;
    private String categorynm;
    private String nicknm;
    private String save_name; //리스트에서 사진 뿌려주려고..
    private int num; //갯수

    private int balance; //잔액계산 위해서.

}
