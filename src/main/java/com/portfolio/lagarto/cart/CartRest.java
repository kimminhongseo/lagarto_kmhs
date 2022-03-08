package com.portfolio.lagarto.cart;

import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.SuppliesEntity;
import com.portfolio.lagarto.supplies.SuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart1")
public class CartRest {

    @Autowired
    private SuppliesService service;

    @Autowired
    private Utils utils;

    @PostMapping("/ajax") //장바구니 담기 누르면 cart 테이블로 이동함.
    public  int cartlist(@RequestParam("iuser") int iuser ,@RequestParam("iboard") int iboard)
    {
        SuppliesEntity entity = new SuppliesEntity();
        entity.setIboard(iboard);
        entity.setIuser(utils.getLoginUserPk());

        return service.cartList(entity);
    }

//    @GetMapping("/check")
//    public int checkcart()

}
