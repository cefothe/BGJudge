package com.cefothe.bgjudge.exam.services;

import com.cefothe.bgjudge.exam.entities.ExamStatus;
import com.cefothe.bgjudge.exam.entities.Examens;
import com.cefothe.bgjudge.exam.models.binding.CreateExamModel;
import com.cefothe.bgjudge.exam.models.view.ViewExamDetailsModel;
import com.cefothe.bgjudge.exam.models.view.ViewExamModel;
import com.cefothe.bgjudge.exam.models.view.ViewExamTasksModel;

import java.util.List;

/**
 * Created by cefothe on 04.05.17.
 */
public interface ExamService {
    void create(CreateExamModel createExamModel);
    Examens update(Examens examens);
    void delete(Long id);
    void changeStatus(ExamStatus examStatus, Long examId);
    List<ViewExamModel> getAllExams();
    ViewExamDetailsModel get(Long id);
    ViewExamTasksModel getExamTasks(Long id);
}
