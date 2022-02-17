package com.portfolio.lagarto.customer.comment;

import com.portfolio.lagarto.model.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @DeleteMapping("/{icmt}")
    public Map<String, Integer> delCustomerCmt(@PathVariable int icmt) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.delCustomerCmt(icmt));
        return result;
    }

}
