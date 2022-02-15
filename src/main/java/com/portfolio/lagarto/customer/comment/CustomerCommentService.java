package com.portfolio.lagarto.customer.comment;

import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerCommentService {

    @Autowired private CustomerCommentMapper mapper;
    @Autowired private Utils utils;

    public ResultVo insCustomerCmt(CustomerCommentEntity entity) {
        entity.setIuser(utils.getLoginUserPk());
        int result = mapper.insCustomerCmt(entity);
        return new ResultVo(result);
    }

    public List<CustomerCommentVo> selCustomerCmtList(CustomerCommentEntity entity) {
        return mapper.selCustomerCmtList(entity);
    }

}
