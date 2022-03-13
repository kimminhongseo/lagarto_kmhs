package com.portfolio.lagarto.model;

public class CustomerVo extends CustomerEntity {
    private String productTitle;
    private String nickname;
    private String board_nm;
    private String changeYn;
    private int level;

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBoard_nm() {
        return board_nm;
    }

    public void setBoard_nm(String board_nm) {
        this.board_nm = board_nm;
    }

    public String getChangeYn() {
        return changeYn;
    }

    public void setChangeYn(String changeYn) {
        this.changeYn = changeYn;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
