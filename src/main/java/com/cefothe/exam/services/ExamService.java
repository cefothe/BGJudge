package com.cefothe.exam.services;

import com.cefothe.exam.entitities.Examens;
import com.cefothe.exam.models.binding.CreateExamModel;
import com.cefothe.exam.models.view.ViewExamModel;
import com.cefothe.user.entities.User;

import java.util.List;

/**
 * Created by cefothe on 04.05.17.
 */
public interface ExamService {
    void create(CreateExamModel createExamModel);
    Examens update(Examens examens);
    void delete(Long id);
    List<ViewExamModel> getAllExams();

}
