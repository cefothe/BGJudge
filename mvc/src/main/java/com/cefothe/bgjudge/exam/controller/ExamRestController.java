package com.cefothe.bgjudge.exam.controller;

import com.cefothe.bgjudge.exam.entitities.ExamStatus;
import com.cefothe.bgjudge.exam.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cefothe on 17.06.17.
 */
@RestController
@RequestMapping("/api/exam")
public class ExamRestController {

    private final ExamService examService;

    @Autowired
    public ExamRestController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("{id}/status/{examStatus}")
    @ResponseStatus(HttpStatus.OK)
    public void changeStatus(@PathVariable("id") Long examId, @PathVariable("examStatus")ExamStatus examStatus){
        examService.changeStatus(examStatus,examId);
    }
}
