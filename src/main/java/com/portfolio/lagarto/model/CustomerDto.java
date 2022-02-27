package com.portfolio.lagarto.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class CustomerDto extends CustomerEntity {
    private String secretYn;
    private String changeYn;
    private List<Integer> fileIdxs;
}
