package com.cefothe.bgjudge.submissions.controller;

import com.cefothe.bgjudge.submissions.dto.SubmissionResultTO;
import com.cefothe.bgjudge.submissions.dto.SubmissionTO;
import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.submissions.services.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by cefothe on 05.05.17.
 */
@RestController
@RequestMapping("/api/submission")
public class SubmissionController {

    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("create")
    public void submit(@RequestBody SubmissionTO submissionTO) throws IOException {
       submissionService.create(submissionTO);
    }

    @GetMapping("exam/{examId}/task/{taskId}")
    public Page<SubmissionResultTO> findSubmissionByExamAndTask(@PathVariable("examId") Long examId, @PathVariable("taskId") Long taskId, @PageableDefault Pageable pageable){
        return submissionService.findSubmissionByExamAndTask(examId,taskId,pageable);
    }
}
