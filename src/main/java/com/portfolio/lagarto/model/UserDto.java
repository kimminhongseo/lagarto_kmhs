package com.portfolio.lagarto.model;

import lombok.Data;

@Data
public class UserDto extends UserEntity{
    private String newUpw;
    private int opponent;
    private int reportNum;
    private String reportContent;
}
