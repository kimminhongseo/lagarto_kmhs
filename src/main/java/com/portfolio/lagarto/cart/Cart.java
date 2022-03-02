package com.portfolio.lagarto.cart;

import com.portfolio.lagarto.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class Cart {
    @Autowired
    private Utils utils;
    @GetMapping("/cart")
    public String cart(){
        if (0 != utils.getLoginUserPk()) {
            return "/cart/cart";
        }
        return "redirect:/user/login";
    }
}
