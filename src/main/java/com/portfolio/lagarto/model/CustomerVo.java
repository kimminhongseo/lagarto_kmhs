package com.portfolio.lagarto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerVo extends CustomerEntity {
    private String productTitle;
    private String nickname;
    private String board_nm;
}
