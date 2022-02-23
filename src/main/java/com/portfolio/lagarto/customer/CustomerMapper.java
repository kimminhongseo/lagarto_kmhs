package com.portfolio.lagarto.customer;

import com.portfolio.lagarto.model.CustomerDto;
import com.portfolio.lagarto.model.CustomerEntity;
import com.portfolio.lagarto.model.CustomerVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    boolean insCustomer(CustomerEntity entity);
    List<CustomerVo> selCustomerList(CustomerDto dto);
    CustomerVo selCustomerDetail(CustomerDto dto);
    int delCustomer(CustomerEntity entity);
}
