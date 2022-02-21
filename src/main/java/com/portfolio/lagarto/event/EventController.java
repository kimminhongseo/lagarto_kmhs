package com.portfolio.lagarto.event;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Controller
@RequestMapping("/event")
public class EventController {
    @GetMapping("/event")
    public void caleander(){

    }
}
