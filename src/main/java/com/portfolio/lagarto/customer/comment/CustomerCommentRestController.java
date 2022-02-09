package com.portfolio.lagarto.customer.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ajax/customer")
public class CustomerCommentRestController {

    @Autowired private CustomerCommentService service;
}
