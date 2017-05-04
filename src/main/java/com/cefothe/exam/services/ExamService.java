package com.cefothe.exam.services;

import com.cefothe.exam.entitities.Examens;
import com.cefothe.user.entities.User;

/**
 * Created by cefothe on 04.05.17.
 */
public interface ExamService {
    void create(Examens examens, User user);
    Examens update(Examens examens);
    void delete(Long id);
}
