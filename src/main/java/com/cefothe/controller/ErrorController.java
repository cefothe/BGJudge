package com.cefothe.controller;

import com.cefothe.bgjudge.user.models.binding.LoginUserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cefothe on 09.05.17.
 */
@Controller
public class ErrorController {

    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String renderErrorPage(Model model){

        model.addAttribute("title", "404 Page not found");
        model.addAttribute("view", "error/error-404");

        return "base-layout";
    }
}
