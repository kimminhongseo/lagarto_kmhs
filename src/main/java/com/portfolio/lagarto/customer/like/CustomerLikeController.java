package com.portfolio.lagarto.customer.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customer/like")
public class CustomerLikeController {

    @Autowired private CustomerLikeService service;

    @PostMapping
    public Map<String, Integer> insCustomerLike(@RequestBody CustomerLikeEntity entity) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.insCustomerLike(entity));
        return result;
    }

    @GetMapping("/{iboard}")
    public Map<String, Integer> isLike(@PathVariable int iboard) {
        CustomerLikeEntity dbEntity = service.selCustomerLike(iboard);
        Map<String, Integer> result = new HashMap<>();
        result.put("result", dbEntity == null ? 0 : 1);
        return result;
    }

    @DeleteMapping("/{iboard}")
    public Map<String, Integer> delCustomerLike(@PathVariable int iboard) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.delCustomerLike(iboard));
        return result;
    }

}
