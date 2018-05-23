package com.cefothe.bgjudge.submissions.services;

import com.cefothe.bgjudge.submissions.dto.SubmissionResultTO;
import com.cefothe.bgjudge.submissions.dto.SubmissionTO;
import com.cefothe.bgjudge.submissions.entities.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

/**
 * Created by cefothe on 06.05.17.
 */
public interface SubmissionService {
    void create(SubmissionTO submissionTO) throws IOException;
    Page<SubmissionResultTO> findSubmissionByExamAndTask(Long examId, Long TaskId, Pageable pageable);
}
