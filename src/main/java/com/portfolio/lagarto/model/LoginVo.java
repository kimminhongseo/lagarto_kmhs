package com.portfolio.lagarto.model;

import lombok.Data;

@Data
public class LoginVo extends UserEntity {
    private boolean auto_id_check;
}
