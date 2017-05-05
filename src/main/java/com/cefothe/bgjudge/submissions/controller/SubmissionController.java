package com.cefothe.bgjudge.submissions.controller;

import com.cefothe.bgjudge.submissions.dto.SubmissionTO;
import com.cefothe.bgjudge.submissions.services.SubmissionService;
import com.cefothe.bgjudge.submissions.services.SubmissionServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by cefothe on 05.05.17.
 */
@RestController
@RequestMapping("/submission")
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
}
