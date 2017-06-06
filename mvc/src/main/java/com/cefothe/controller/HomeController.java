package com.cefothe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by cefothe on 04.05.17.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("title", "Home BGJudge");
        model.addAttribute("view","home/index");

        return "base-layout";
    }
}
