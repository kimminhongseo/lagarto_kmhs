package com.portfolio.lagarto.customer.comment;

import com.portfolio.lagarto.customer.comment.model.CustomerCommentEntity;
import com.portfolio.lagarto.customer.comment.model.CustomerCommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerCommentMapper {
    int insCustomerCmt(CustomerCommentEntity entity);
    List<CustomerCommentVo> selCustomerCmtList(CustomerCommentEntity entity);
}
