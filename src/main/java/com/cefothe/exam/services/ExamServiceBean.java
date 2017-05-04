package com.cefothe.exam.services;

import com.cefothe.common.component.AuthenticationFacade;
import com.cefothe.exam.entitities.Examens;
import com.cefothe.exam.models.binding.CreateExamModel;
import com.cefothe.exam.models.view.ViewExamModel;
import com.cefothe.exam.repositories.ExamRepository;
import com.cefothe.user.entities.User;
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
    public List<ViewExamModel> getAllExams() {
        List<ViewExamModel> viewExamModels = new ArrayList<>();
        examRepository.findAll().forEach(examens -> viewExamModels.add(this.modelMapper.map(examens,ViewExamModel.class)));
        return viewExamModels;
    }
}
