package com.cefothe.bgjudge.submissions.entities;

import com.cefothe.bgjudge.exam.entitities.Examens;
import com.cefothe.bgjudge.tasks.entities.Task;
import com.cefothe.bgjudge.user.entities.User;
import com.cefothe.bgjudge.workers.entities.Test;
import com.cefothe.common.entities.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cefothe on 08.05.17.
 */
@Entity
@Table(name = "submissions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @Lob
    private String code;

    @Max(value = 100)
    @Min(value = 0)
    @Getter
    @Column(name = "result")
    private Integer result;

    @OneToMany
    @Getter
    private List<Test> tests = new ArrayList<>();

    public Submission(User createdBy, Task task, Examens exam, String code) {
        this.createdBy = createdBy;
        this.task = task;
        this.exam = exam;
        this.code = code;
    }

    public void addTest(@NonNull Test test){
        tests.add(test);
    }

    public void addTest(List<Test> tests){
        if(tests != null){
            this.tests.addAll(tests);
        }
    }
}
