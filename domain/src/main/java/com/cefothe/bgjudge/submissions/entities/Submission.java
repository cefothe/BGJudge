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

import static com.cefothe.bgjudge.submissions.entities.Submission.FIND_SUBMISSIONS_BY_USER_TASK_EXAM;
import static com.cefothe.bgjudge.submissions.entities.Submission.FIND_SUBMISSIONS_MAX_SCORE_BY_EXAM;

/**
 * Created by cefothe on 08.05.17.
 */
@Entity
@Table(name = "submissions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedQueries({
        @NamedQuery(name = FIND_SUBMISSIONS_MAX_SCORE_BY_EXAM,
                query = "SELECT new Submission(sub.createdBy, MAX(sub.result), sub.task) FROM Submission sub WHERE sub.exam =:exam GROUP BY sub.exam, sub.createdBy, sub.task"),
        @NamedQuery(name = FIND_SUBMISSIONS_BY_USER_TASK_EXAM,
                query = "SELECT sub FROM Submission sub WHERE sub.exam =:exam and sub.task =:task and sub.createdBy =:user")
})
public class Submission extends BaseEntity {

    public static final String FIND_SUBMISSIONS_MAX_SCORE_BY_EXAM = "Submission.findSubmissionMaxScoreByExam";
    public static final String FIND_SUBMISSIONS_BY_USER_TASK_EXAM = "Submission.findSubmissionByUserTaskExam";

    @Getter
    @OneToOne
    private User createdBy;

    @Getter
    @OneToOne
    private Task task;

    @Getter
    @OneToOne
    private Examens exam;

    @Getter
    @Lob
    private String code;

    @Getter
    @Setter
    @Max(value = 100)
    @Min(value = 0)
    @Column(name = "result")
    private Integer result = new Integer(0);

    @Getter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Test> tests = new ArrayList<>();

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;

    public Submission(User createdBy, Task task, Examens exam, String code, SubmissionStatus submissionStatus) {
        this.createdBy = createdBy;
        this.task = task;
        this.exam = exam;
        this.code = code;
        this.status = submissionStatus;
    }

    /**
     * Use this constructor only in named query
     * @param createdBy
     * @param result
     * @param task
     */
    public Submission(User createdBy, Integer result, Task task){
        this.createdBy = createdBy;
        this.result = result;
        this.task = task;
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
