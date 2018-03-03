package com.cefothe.bgjudge.exam.controller;

import com.cefothe.bgjudge.exam.models.binding.LoginIntoExamModel;
import com.cefothe.bgjudge.exam.services.ExamService;
import com.cefothe.bgjudge.exam.services.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cefothe on 17.06.17.
 */
@Controller
@RequestMapping("exam")
public class StudentExamController {

    private ParticipantService participantService;
    private ExamService examService;

    @Autowired
    public StudentExamController(ParticipantService participantService, ExamService examService) {
        this.participantService = participantService;
        this.examService = examService;
    }

    @GetMapping("/{examId}/login")
    public String loginIntoExam(@PathVariable("examId") Long examId, Model model,LoginIntoExamModel loginIntoExamModel ){
        model.addAttribute("title", "Login into exam");
        model.addAttribute("view", "exam/logginIntoExam");
        model.addAttribute("loginIntoExamModel", loginIntoExamModel);
        return "base-layout";
    }

    @PostMapping("/{examId}/login")
    public String logIntoExam(@PathVariable("examId") Long examId, LoginIntoExamModel loginIntoExamModel){
            if(participantService.addParticipantIntoExam(examId,loginIntoExamModel)){
            return "redirect:/exam/"+examId+"/tasks";
        }
        return "redirect:/exam/"+examId+"/login";
    }

    @GetMapping("/{examId}/tasks")
    public String tasks(@PathVariable("examId") Long examId, Model model){
        model.addAttribute("title", "Exam view");
        model.addAttribute("examTasks",examService.getExamTasks(examId));
        model.addAttribute("view","exam/studentExam");
        return "base-layout";
    }
}
