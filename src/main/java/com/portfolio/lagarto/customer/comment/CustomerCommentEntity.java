package com.portfolio.lagarto.customer.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerCommentEntity {
    private int icmt;
    private int iboard;
    private int iuser;
    private String ctnt;
    private String rdt;
    private String mdt;
}
