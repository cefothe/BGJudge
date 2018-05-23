package com.cefothe.bgjudge.home.controller;

import com.cefothe.bgjudge.home.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by cefothe on 04.05.17.
 */
@Controller
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }


    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("title", "Home BGJudge");
        model.addAttribute("view","home/index");
        model.addAttribute("homePage", homeService.homePage());

        return "base-layout";
    }
}
