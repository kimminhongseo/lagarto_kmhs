package com.portfolio.lagarto.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDto extends CustomerEntity {
    private String secretYn;
    private String changeYn;
    private List<Long> fileIdxs;
}
