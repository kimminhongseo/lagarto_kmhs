package com.portfolio.lagarto.model;

import lombok.Data;

@Data
public class FollowEntity extends UserEntity{
    private int follow_num;
    private int iuserMe;
    private int iuserYou;
}