package com.cefothe.bgjudge.submissions.services;

import com.cefothe.bgjudge.queue.SendToWorker;
import com.cefothe.bgjudge.submissions.dto.SubmissionTO;
import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.submissions.repositories.SubmissionRepository;
import com.cefothe.common.io.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Created by cefothe on 06.05.17.
 */
@Service
public class SubmissionServiceBean implements SubmissionService{

    private final FileIO fileIO;

    private final SubmissionRepository submissionRepository;

    private final SendToWorker sendToWorker;

    @Autowired
    public SubmissionServiceBean(FileIO fileIO, SubmissionRepository submissionRepository, SendToWorker sendToWorker) {
        this.fileIO = fileIO;
        this.submissionRepository = submissionRepository;
        this.sendToWorker= sendToWorker;
    }

    @Override
    public void create(SubmissionTO submissionTO) throws IOException {
        File file = fileIO.write(submissionTO.getCode(), "Main.java");
        Submission submission = submissionRepository.save(new Submission());
        sendToWorker.sendMessage(submission);

        System.out.println(file.getAbsolutePath());
    }
}
