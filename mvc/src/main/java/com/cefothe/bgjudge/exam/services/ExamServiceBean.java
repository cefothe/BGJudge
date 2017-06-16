package com.cefothe.bgjudge.exam.services;

import com.cefothe.bgjudge.exam.entitities.ExamStatus;
import com.cefothe.bgjudge.exam.models.view.ViewExamDetailsModel;
import com.cefothe.common.component.AuthenticationFacade;
import com.cefothe.bgjudge.exam.entitities.Examens;
import com.cefothe.bgjudge.exam.models.binding.CreateExamModel;
import com.cefothe.bgjudge.exam.models.view.ViewExamModel;
import com.cefothe.bgjudge.exam.repositories.ExamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cefothe on 04.05.17.
 */
@Service
@Transactional
public class ExamServiceBean implements ExamService {

    private final ExamRepository examRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public ExamServiceBean(ExamRepository examRepository, ModelMapper modelMapper , AuthenticationFacade authenticationFacade) {
        this.examRepository = examRepository;
        this.modelMapper = modelMapper;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public void create(CreateExamModel createExamModel) {
        Examens examens = this.modelMapper.map(createExamModel,Examens.class);
        examens.setCreatedBy(this.authenticationFacade.getUser());
        this.examRepository.save(examens);
    }

    @Override
    public Examens update(Examens examens) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void changeStatus(ExamStatus examStatus, Long examId) {
        Examens examens = examRepository.findOne(examId);
        examens.setExamStatus(examStatus);
        examRepository.save(examens);
    }

    @Override
    public List<ViewExamModel> getAllExams() {
        List<ViewExamModel> viewExamModels = new ArrayList<>();
        this.examRepository.findAll().forEach(examens -> viewExamModels.add(this.modelMapper.map(examens,ViewExamModel.class)));
        return viewExamModels;
    }

    @Override
    public ViewExamDetailsModel get(Long id) {
        Examens examens = this.examRepository.findOne(id);
        return this.modelMapper.map(examens, ViewExamDetailsModel.class);
    }
}
