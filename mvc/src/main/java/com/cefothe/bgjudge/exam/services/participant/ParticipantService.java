package com.cefothe.bgjudge.exam.services.participant;

import com.cefothe.bgjudge.exam.models.binding.LoginIntoExamModel;

/**
 * Created by cefothe on 26.06.17.
 */
public interface ParticipantService {
    boolean addParticipantIntoExam(Long examId, LoginIntoExamModel loginIntoExamModel);
    boolean checkParticipantAndExam(Long examId);
}
