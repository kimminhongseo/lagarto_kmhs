package com.portfolio.lagarto.customer;

import com.portfolio.lagarto.Criteria;
import com.portfolio.lagarto.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    boolean insCustomer(CustomerEntity entity);
    List<CustomerVo> selCustomerList(TestDto dto);
    List<CustomerVo> selMyReviewList(TestDto dto);
    List<CustomerVo> selNoticeList(TestDto dto);
    CustomerVo selCustomerDetail(CustomerDto dto);
    int updCustomer(CustomerDto dto);
    int delCustomer(CustomerEntity entity);
    int totalCount(TestDto dto);
}
