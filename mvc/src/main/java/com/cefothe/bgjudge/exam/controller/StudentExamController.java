package com.cefothe.bgjudge.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cefothe on 17.06.17.
 */
@Controller
@RequestMapping("exam")
public class StudentExamController {

    @GetMapping("/{examId}/login")
    public String loginIntoExam(@PathVariable("examId") Long examId, Model model){
        model.addAttribute("title", "Login into exam");
        model.addAttribute("view", "exam/logginIntoExam");
        return "base-layout";
    }
}
