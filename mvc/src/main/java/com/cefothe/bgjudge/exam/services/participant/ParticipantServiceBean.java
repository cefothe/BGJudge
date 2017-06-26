package com.cefothe.bgjudge.exam.services.participant;

import com.cefothe.bgjudge.exam.models.binding.LoginIntoExamModel;
import com.cefothe.bgjudge.exam.repositories.ExamRepository;
import com.cefothe.bgjudge.participants.repositories.ParticipantRepository;
import com.cefothe.common.component.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cefothe on 26.06.17.
 */
@Service
public class ParticipantServiceBean implements ParticipantService {

    private final AuthenticationFacade authenticationFacade;
    private final ParticipantRepository participantRepository;
    private final ExamRepository examRepository;

    @Autowired
    public ParticipantServiceBean(AuthenticationFacade authenticationFacade, ParticipantRepository participantRepository, ExamRepository examRepository) {
        this.authenticationFacade = authenticationFacade;
        this.participantRepository = participantRepository;
        this.examRepository = examRepository;
    }

    @Override
    public boolean addParticipantIntoExam(Long examId, LoginIntoExamModel loginIntoExamModel) {
        return false;
    }
}
