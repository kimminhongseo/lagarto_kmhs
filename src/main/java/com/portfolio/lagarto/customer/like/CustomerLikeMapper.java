package com.portfolio.lagarto.customer.like;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerLikeMapper {
    int insCustomerLike(CustomerLikeEntity entity);
    CustomerLikeEntity selCustomerLike(CustomerLikeEntity entity);
    int delCustomerLike(CustomerLikeEntity entity);
}
