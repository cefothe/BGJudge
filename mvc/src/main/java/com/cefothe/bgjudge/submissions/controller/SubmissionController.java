package com.cefothe.bgjudge.submissions.controller;

import com.cefothe.bgjudge.submissions.dto.SubmissionTO;
import com.cefothe.bgjudge.submissions.services.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity submit(@RequestBody SubmissionTO submissionTO) throws IOException {
       submissionService.create(submissionTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
