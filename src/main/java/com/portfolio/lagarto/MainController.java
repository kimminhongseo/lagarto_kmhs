package com.portfolio.lagarto;

import com.portfolio.lagarto.auction.AuctionService;
import com.portfolio.lagarto.model.AuctionVo;
import com.portfolio.lagarto.model.SuppliesVo;
import com.portfolio.lagarto.model.UserEntity;
import com.portfolio.lagarto.recaptcha.VerifyRecaptcha;
import com.portfolio.lagarto.supplies.SuppliesService;
import com.portfolio.lagarto.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class MainController {
    @Autowired
    private MainService service;

    @GetMapping("/")
    public String gomain(){
        return "redirect:/main";
    }

    @GetMapping("main")
    public void main(Model model, AuctionVo auctionVo, SuppliesVo suppliesVo) {
        model.addAttribute("auctionList", service.selMainAuctionList(auctionVo));
        model.addAttribute("suppliesList", service.selMainSuppliesList(suppliesVo));
        model.addAttribute("suppliesTopList", service.selMainTopSuppliesList(suppliesVo));
    }

    @ResponseBody
    @PostMapping("/valid-recaptcha")
    public int VerifyRecaptcha(HttpServletRequest request) {
        VerifyRecaptcha.setSecretKey("6Ldq34seAAAAAL0ORNsQCNASfwXp4eNUvI1LBl1n");
        String gRecaptchaResponse = request.getParameter("recaptcha");
        try {
            if(VerifyRecaptcha.verify(gRecaptchaResponse))
                return 0; // 성공
            else return 1; // 실패
        } catch (Exception e) {
            e.printStackTrace();
            return -1; //에러
        }
    }


}
