package com.cefothe.bgjudge.exam.controller;

import com.cefothe.bgjudge.exam.models.binding.CreateExamModel;
import com.cefothe.bgjudge.exam.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by cefothe on 04.05.17.
 */

@Controller
@RequestMapping("exam")
@PreAuthorize("hasAuthority('TEACHER')")
public class ExamController {

    private final ExamService examService;

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
    public String createExam(@Valid CreateExamModel createExamModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createExamModel", createExamModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createExamModel", bindingResult);
            return "redirect:/create";
        }
        examService.create(createExamModel);
        return "redirect:/exam/all";
    }

    @GetMapping("all")
    public String allExams(Model model){
        model.addAttribute("title", "View all exams");
        model.addAttribute("view", "exam/all");
        model.addAttribute("exams", this.examService.getAllExams());

        return "base-layout";
    }

    @GetMapping("open/{id}")
    public String getExam(Model model, @PathVariable("id") Long id){
        model.addAttribute("title", "Exam details");
        model.addAttribute("view", "exam/exam");
        model.addAttribute("viewExamDetailsModel", this.examService.get(id));
        return "base-layout";
    }


}
