package com.portfolio.lagarto.cart;

import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.SuppliesEntity;
import com.portfolio.lagarto.model.SuppliesVo;
import com.portfolio.lagarto.supplies.SuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class Cart {
    @Autowired
    private Utils utils;

    @Autowired
    private SuppliesService service;

    @GetMapping("/cart")
    public String cart(SuppliesVo vo, Model model){
        model.addAttribute("cartinfo",service.cartmoney(vo));

        if (0 != utils.getLoginUserPk()) {
            return "/cart/cart";
        }
        return "redirect:/user/login";
    }





}
