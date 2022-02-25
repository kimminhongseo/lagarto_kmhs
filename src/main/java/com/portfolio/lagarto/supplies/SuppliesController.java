package com.portfolio.lagarto.supplies;


import com.portfolio.lagarto.model.SuppliesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/supplies")
public class SuppliesController {

    @Autowired
    private SuppliesService service;

    @GetMapping("/write")
    public String write(@ModelAttribute("suppliesEntity") SuppliesEntity suppliesEntity){
        System.out.println(suppliesEntity.getIcategory());
        service.insSuppliesList(suppliesEntity);
        return  "/supplies/write";
    }






}
