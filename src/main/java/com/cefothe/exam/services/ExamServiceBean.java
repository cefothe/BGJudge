package com.cefothe.exam.services;

import com.cefothe.exam.entitities.Examens;
import com.cefothe.exam.repositories.ExamRepository;
import com.cefothe.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cefothe on 04.05.17.
 */
@Service
public class ExamServiceBean implements ExamService {

    private ExamRepository examRepository;

    @Autowired
    public ExamServiceBean(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public void create(Examens examens, User user) {
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
