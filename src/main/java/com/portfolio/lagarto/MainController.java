package com.portfolio.lagarto;

import com.portfolio.lagarto.recaptcha.VerifyRecaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class MainController {

    @GetMapping("/")
    public String gomain(){
        return "redirect:/main";
    }

    @GetMapping("main")
    public void main() {};

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
