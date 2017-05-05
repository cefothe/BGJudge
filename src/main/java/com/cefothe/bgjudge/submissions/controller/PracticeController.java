package com.cefothe.bgjudge.submissions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cefothe on 05.05.17.
 */
@RequestMapping("/practice")
@Controller
public class PracticeController {

    @GetMapping("index")
    public String practicePage(Model model){

        model.addAttribute("view", "submisions/index");
        model.addAttribute("title", "Submissions");

        return "base-layout";
    }
}
