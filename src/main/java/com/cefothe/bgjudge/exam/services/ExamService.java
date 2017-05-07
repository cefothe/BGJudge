package com.cefothe.bgjudge.exam.services;

import com.cefothe.bgjudge.exam.entitities.Examens;
import com.cefothe.bgjudge.exam.models.binding.CreateExamModel;
import com.cefothe.bgjudge.exam.models.view.ViewExamDetailsModel;
import com.cefothe.bgjudge.exam.models.view.ViewExamModel;

import java.util.List;

/**
 * Created by cefothe on 04.05.17.
 */
public interface ExamService {
    void create(CreateExamModel createExamModel);
    Examens update(Examens examens);
    void delete(Long id);
    List<ViewExamModel> getAllExams();
    ViewExamDetailsModel get(Long id);
}
