package com.cefothe.bgjudge.submissions.services;

import com.cefothe.bgjudge.submissions.dto.SubmissionTO;

import java.io.IOException;

/**
 * Created by cefothe on 06.05.17.
 */
public interface SubmissionService {
    void create(SubmissionTO submissionTO) throws IOException;
}
