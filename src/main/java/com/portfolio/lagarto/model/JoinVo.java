package com.portfolio.lagarto.model;

import com.portfolio.lagarto.enums.JoinResult;
import lombok.Data;

@Data
public class JoinVo extends UserEntity {
    private JoinResult result;
}
