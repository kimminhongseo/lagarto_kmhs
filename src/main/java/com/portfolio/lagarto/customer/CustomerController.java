package com.portfolio.lagarto.customer;

import com.portfolio.lagarto.model.CustomerDto;
import com.portfolio.lagarto.model.CustomerEntity;
import com.portfolio.lagarto.model.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    public CustomerService service;

    @GetMapping("/list/{board_cd}")
    public String list(@PathVariable int board_cd, CustomerDto dto, Model model) {
        model.addAttribute("board_cd", board_cd);
        model.addAttribute("list", service.selCustomerList(dto));
        dto.setBoard_cd(board_cd);
        return "customer/list";
    }

    @GetMapping("/write")
    public String write(@ModelAttribute("entity") CustomerEntity entity) {
        return "customer/write";
    }

    @PostMapping("/write")
    public String writeProc(CustomerEntity entity) {
        int result = service.insCustomer(entity);
        return "redirect:/customer/list/" + entity.getBoard_cd();
    }

    @GetMapping("/detail")
//    public String detail(CustomerDto dto, Model model) {
//        model.addAttribute("data", service.selCustomerDetail(dto));
//        return "customer/detail";
//    }
    public void detail() {}

    @GetMapping("/detail_item")
    public void selCustomerDetail(Model model, CustomerDto dto) {
        model.addAttribute("data", service.selCustomerDetail(dto));
    }
}
