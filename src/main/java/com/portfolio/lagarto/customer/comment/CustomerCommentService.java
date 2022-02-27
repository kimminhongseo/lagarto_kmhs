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

    public int updCustomerCmt(CustomerCommentEntity entity) {
        entity.setIuser(utils.getLoginUserPk());
        return mapper.updCustomerCmt(entity);
    }

    public int delCustomerCmt(int icmt) {
        CustomerCommentEntity entity = new CustomerCommentEntity();
        entity.setIcmt(icmt);
        entity.setIuser(utils.getLoginUserPk());
        return mapper.delCustomerCmt(entity);
    }

    public int delCustomerCmtAll(int iboard) {
        CustomerCommentEntity entity = new CustomerCommentEntity();
        entity.setIboard(iboard);
        return mapper.delCustomerCmtAll(entity);
    }
}
