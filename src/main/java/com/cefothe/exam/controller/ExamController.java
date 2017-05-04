package com.cefothe.exam.controller;

import com.cefothe.exam.models.binding.CreateExamModel;
import com.cefothe.exam.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by cefothe on 04.05.17.
 */

@Controller
@RequestMapping("exam")
@PreAuthorize("hasAuthority('TEACHER')")
public class ExamController {

    private ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("create")
    public String createExamPage(Model model, CreateExamModel createExamModel){
        model.addAttribute("view", "exam/create");
        model.addAttribute("title", "Create exam");
        model.addAttribute("createExamModel", createExamModel);

        return "base-layout";
    }

    @PostMapping("create")
    public String createExam(CreateExamModel createExamModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        examService.create(createExamModel);

        return "redirect:/login";
    }

}
