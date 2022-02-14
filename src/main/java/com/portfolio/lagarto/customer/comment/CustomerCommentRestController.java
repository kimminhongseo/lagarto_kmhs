package com.portfolio.lagarto.customer.comment;

import com.portfolio.lagarto.model.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ajax/customerCmt")
public class CustomerCommentRestController {

    @Autowired private CustomerCommentService service;

    @PostMapping
    public ResultVo insCustomerCmt(@RequestBody CustomerCommentEntity entity) {
        return service.insCustomerCmt(entity);
    }

    @GetMapping
    public List<CustomerCommentVo> selCustomerCmtList(CustomerCommentEntity entity) {
        return service.selCustomerCmtList(entity);
    }

//    @GetMapping
//    public CustomerCommentVo selOneCustomerCmt(CustomerCommentEntity entity) {
//        return service.selOneCustomerCmt(entity);
//    }
}
