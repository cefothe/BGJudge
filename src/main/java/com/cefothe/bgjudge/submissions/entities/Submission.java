package com.cefothe.bgjudge.submissions.entities;

import com.cefothe.bgjudge.exam.entitities.Examens;
import com.cefothe.bgjudge.tasks.entities.Task;
import com.cefothe.bgjudge.user.entities.User;
import com.cefothe.common.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by cefothe on 08.05.17.
 */
@Entity
@Table(name = "submissions")
@AllArgsConstructor
public class Submission extends BaseEntity {

    @OneToOne
    @Getter
    private User createdBy;

    @OneToOne
    @Getter
    private Task task;

    @OneToOne
    @Getter
    private Examens exam;

    @Getter
    private String code;


}
