package com.portfolio.lagarto.customer.comment;

import com.portfolio.lagarto.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCommentService {

    @Autowired private CustomerCommentMapper mapper;
    @Autowired private Utils utils;
}
