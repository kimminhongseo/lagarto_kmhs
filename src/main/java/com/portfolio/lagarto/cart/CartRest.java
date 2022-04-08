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

    //결제하면 잔액 없데이트 balance 가져와야함.
    // todo 세션에 담기.
    @PutMapping("/balance")
    public Map<String,Integer> balancenum(@RequestBody SuppliesVo vo){
        Map<String,Integer> result = new HashMap<>();
        vo.setIuser(utils.getLoginUserPk());
        result.put("result",service.balancenum(vo));
        return result;
    }


    @DeleteMapping("/{iboard}")
    public Map<String, Integer> delcart(@PathVariable int iboard){
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.delcart(iboard));
        System.out.println(iboard);
        return result;
    }
}
