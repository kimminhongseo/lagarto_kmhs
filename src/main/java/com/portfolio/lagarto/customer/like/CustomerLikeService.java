package com.portfolio.lagarto.customer.like;

import com.portfolio.lagarto.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerLikeService {

    @Autowired private CustomerLikeMapper mapper;
    @Autowired private Utils utils;

    public int insCustomerLike(CustomerLikeEntity entity) {
        entity.setIuser(utils.getLoginUserPk());
        return mapper.insCustomerLike(entity);
    }

    public CustomerLikeEntity selCustomerLike(int iboard) {
        return mapper.selCustomerLike(createCustomerLikeEntity(iboard));
    }

    public int delCustomerLike(int iboard) {
        return mapper.delCustomerLike(createCustomerLikeEntity(iboard));
    }

    private CustomerLikeEntity createCustomerLikeEntity(int iboard) {
        CustomerLikeEntity entity = new CustomerLikeEntity();
        entity.setIboard(iboard);
        entity.setIuser(utils.getLoginUserPk());
        return entity;
    }

}
