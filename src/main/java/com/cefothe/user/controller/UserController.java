package com.cefothe.user.controller;

import com.cefothe.user.models.binding.LoginUserModel;
import com.cefothe.user.models.binding.RegisterUserModel;
import com.cefothe.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by cefothe on 02.05.17.
 */
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute("view", "user/login");
        model.addAttribute("user", new LoginUserModel());

        return "base-layout";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("view", "user/register");
        model.addAttribute("title", "Register");

        if (!model.containsAttribute("userRegistrationModel")) {
            model.addAttribute("userRegistrationModel", new RegisterUserModel());
        }

        return "base-layout";
    }

    @PostMapping("/register")
    public String registerProcess(RegisterUserModel registerUserModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationModel", registerUserModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationModel", bindingResult);
            return "redirect:/register";
        }

        this.userService.register(registerUserModel);

        return "redirect:/login";

    }

    @ResponseBody
    @GetMapping("/user/test")
    public String test(){
        return "test";
    }
}
