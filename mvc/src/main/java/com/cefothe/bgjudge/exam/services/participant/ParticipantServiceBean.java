package com.cefothe.bgjudge.exam.services.participant;

import com.cefothe.bgjudge.exam.entities.Examens;
import com.cefothe.bgjudge.exam.models.binding.LoginIntoExamModel;
import com.cefothe.bgjudge.exam.repositories.ExamRepository;
import com.cefothe.bgjudge.participants.entities.Participant;
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
        Examens exam = examRepository.findOne(examId);
        if(exam == null) {
            return false;
        }
        boolean checkIfExamAvaible = exam.checkIfExamAvailable();
        if(!checkIfExamAvaible){
            return false;
        }else{
            Participant participant = participantRepository.findByExamens(exam);
            if(participant==null){
                participant = new Participant(exam);
            }
            participant.addParticipant(authenticationFacade.getUser());
        }
        return false;
    }

    @Override
    public boolean checkParticipantAndExam(Long examId) {
        return false;
    }
}
