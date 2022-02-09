package com.portfolio.lagarto.model;


import com.portfolio.lagarto.enums.JoinResult;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
    private int iuser;
    private String email;
    private String uid;
    private String upw;
    private String nm;
    private String nickname;
    private String contact_first;
    private String contact_second;
    private String contact_third;
    private String profile_img;
    private String address_post;
    private String address_primary;
    private String address_secondary;
    private int disc_agree_a;
    private int disc_agree_b;
    private int disc_agree_c;
    private int platform_cd;
    private int level;
    private int level_bar;
    private String rdt;

    private JoinResult result;
}
