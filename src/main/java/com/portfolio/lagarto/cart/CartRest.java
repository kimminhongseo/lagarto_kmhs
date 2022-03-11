package com.portfolio.lagarto.cart;

import com.portfolio.lagarto.Utils;
import com.portfolio.lagarto.model.SuppliesEntity;
import com.portfolio.lagarto.model.SuppliesVo;
import com.portfolio.lagarto.supplies.SuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/list")
    public List<SuppliesVo> myCartList(SuppliesVo vo, Model model){
        model.addAttribute("cart1",service.myCartList(vo));
        return service.myCartList(vo);
    }

//    @GetMapping("/check")
//    public int checkcart()


    @PutMapping("/plus")
    public Map<String,Integer> plusnum(@RequestBody SuppliesVo vo){
        Map<String,Integer> result = new HashMap<>();
        result.put("result",service.plusnum(vo));
        return result;
    }

    @PutMapping("/minus")
    public Map<String,Integer> minusnum(@RequestBody SuppliesVo vo) {
        Map<String,Integer> result = new HashMap<>();
        result.put("result",service.minusnum(vo));
        return result;
    }
}
