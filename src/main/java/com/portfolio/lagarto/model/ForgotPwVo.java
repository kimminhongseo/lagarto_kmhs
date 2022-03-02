package com.portfolio.lagarto.model;

import com.portfolio.lagarto.enums.ForgotIdResult;
import com.portfolio.lagarto.enums.ForgotPwResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ForgotPwVo extends UserEntity {
    private ForgotPwResult forgotPwResult;
}
