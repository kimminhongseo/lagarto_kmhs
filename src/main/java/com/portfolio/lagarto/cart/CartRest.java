package com.portfolio.lagarto.cart;

import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.SuppliesEntity;
import com.portfolio.lagarto.supplies.SuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart1")
public class CartRest {

    @Autowired
    private SuppliesService service;

    @Autowired
    private Utils utils;

    @PostMapping("/ajax")
    public  int cartlist(@RequestParam("iuser") int iuser ,@RequestParam("iboard") int iboard)
    {
        SuppliesEntity entity = new SuppliesEntity();

        entity.setIboard(iboard);
        entity.setIuser(utils.getLoginUserPk());

        return service.cartList(entity);
    }

}
