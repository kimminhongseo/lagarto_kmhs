package com.portfolio.lagarto.model;

import com.portfolio.lagarto.enums.ForgotIdResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ForgotIdVo extends UserEntity {
    private ForgotIdResult forgotIdResult;
}
