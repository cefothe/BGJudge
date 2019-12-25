package com.cefothe.bgjudge.submissions.services;

import com.cefothe.bgjudge.exam.entities.Examens;
import com.cefothe.bgjudge.exam.repositories.ExamRepository;
import com.cefothe.bgjudge.exam.services.participant.ParticipantService;
import com.cefothe.bgjudge.queue.SendToWorker;
import com.cefothe.bgjudge.submissions.dto.SubmissionResultTO;
import com.cefothe.bgjudge.submissions.dto.SubmissionTO;
import com.cefothe.bgjudge.submissions.entities.Submission;
import com.cefothe.bgjudge.submissions.entities.SubmissionStatus;
import com.cefothe.bgjudge.submissions.repositories.SubmissionRepository;
import com.cefothe.bgjudge.tasks.entities.Task;
import com.cefothe.bgjudge.tasks.repositories.TaskRepositories;
import com.cefothe.common.component.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.io.IOException;

/**
 * Created by cefothe on 06.05.17.
 */
@Service
public class SubmissionServiceBean implements SubmissionService{


    private final SubmissionRepository submissionRepository;

    private final SendToWorker sendToWorker;

    private final ParticipantService participantService;

    private final AuthenticationFacade authenticationFacade;

    private  final TaskRepositories taskRepositories;

    private final ExamRepository examRepository;

    @Autowired
    public SubmissionServiceBean(SubmissionRepository submissionRepository, SendToWorker sendToWorker, ParticipantService participantService, AuthenticationFacade authenticationFacade, TaskRepositories taskRepositories, ExamRepository examRepository) {
        this.submissionRepository = submissionRepository;
        this.sendToWorker= sendToWorker;
        this.participantService = participantService;
        this.authenticationFacade = authenticationFacade;
        this.taskRepositories = taskRepositories;
        this.examRepository = examRepository;
    }

    @Override
    public void create(SubmissionTO submissionTO) throws IOException {
        if(participantService.checkParticipantAndExam(submissionTO.getExamId())) {
            Task task = taskRepositories.findById(submissionTO.getTaskId()).get();
            Examens examens = examRepository.findById(submissionTO.getExamId()).get();
            Submission submission = submissionRepository.save(new Submission(this.authenticationFacade.getUser(),task,examens, submissionTO.getCode(), SubmissionStatus.CREATED));
            sendToWorker.sendMessage(submission);
        }
    }

    @Override
    public Page<SubmissionResultTO> findSubmissionByExamAndTask(Long examId, Long taskId, Pageable pageable) {
        Task task = taskRepositories.findById(taskId).get();
        Examens examens = examRepository.findById(examId).get();
        Page<Submission> submissions = this.submissionRepository.findSubmissionByUserTaskExam(examens, task,this.authenticationFacade.getUser(), pageable);
        return submissions.map(source -> new SubmissionResultTO(source.getStatus(), source.getId(), source.getResult()));
    }
}
