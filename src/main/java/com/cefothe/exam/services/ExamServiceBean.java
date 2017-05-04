package com.cefothe.exam.services;

import com.cefothe.common.component.AuthenticationFacade;
import com.cefothe.exam.entitities.Examens;
import com.cefothe.exam.models.binding.CreateExamModel;
import com.cefothe.exam.repositories.ExamRepository;
import com.cefothe.user.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Examens examens = modelMapper.map(createExamModel,Examens.class);
        examens.setCreatedBy(authenticationFacade.getUser());
        examRepository.save(examens);
    }

    @Override
    public Examens update(Examens examens) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
