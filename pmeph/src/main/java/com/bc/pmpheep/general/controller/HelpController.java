package com.bc.pmpheep.general.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lihuan on 2018/2/25.
 */
@Controller
@RequestMapping("help")
public class HelpController {

    @RequestMapping("home")
    public ModelAndView home() {
        return new ModelAndView("comm/helphome");
    }

}
