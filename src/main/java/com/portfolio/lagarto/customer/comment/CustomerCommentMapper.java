package com.portfolio.lagarto.customer.comment;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerCommentMapper {
    int insCustomerCmt(CustomerCommentEntity entity);
    List<CustomerCommentVo> selCustomerCmtList(CustomerCommentEntity entity);
    int updCustomerCmt(CustomerCommentEntity entity);
    int delCustomerCmt(CustomerCommentEntity entity);
    int delCustomerCmtAll(CustomerCommentEntity entity);
}
