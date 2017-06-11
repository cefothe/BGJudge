package com.cefothe.bgjudge.submissions.services;

import com.cefothe.bgjudge.queue.SendToWorker;
import com.cefothe.bgjudge.submissions.dto.SubmissionTO;
import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.submissions.repositories.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

/**
 * Created by cefothe on 06.05.17.
 */
@Service
public class SubmissionServiceBean implements SubmissionService{


    private final SubmissionRepository submissionRepository;

    private final SendToWorker sendToWorker;

    @Autowired
    public SubmissionServiceBean(SubmissionRepository submissionRepository, SendToWorker sendToWorker) {
        this.submissionRepository = submissionRepository;
        this.sendToWorker= sendToWorker;
    }

    @Override
    public void create(SubmissionTO submissionTO) throws IOException {
        Submission submission = submissionRepository.save(new Submission());
        sendToWorker.sendMessage(submission);

    }
}
