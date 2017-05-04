package com.cefothe.exam.entitities;

import com.cefothe.common.entities.BaseEntity;
import com.cefothe.tasks.entities.Task;
import com.cefothe.user.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cefothe on 04.05.17.
 */
@Entity
@Table(name = "examens")
@NoArgsConstructor
public class Examens  extends BaseEntity{

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @Column(name = "exam_date", nullable = false)
    private Timestamp examDate;

    @Getter
    @Setter
    @Column(name = "exam_lenght", nullable = false)
    private long examLength;

    @OneToMany
    @Getter
    private List<Task> tasks = new ArrayList<>();

    @Getter
    @Setter
    @OneToOne(optional = false)
    private User createdBy;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private ExamStatus examStatus = ExamStatus.IN_PROGRESS;

    public Examens(String name, Timestamp examDate, long examLength, User createdBy) {
        this.name = name;
        this.examDate = examDate;
        this.examLength = examLength;
        this.createdBy = createdBy;
        this.examStatus = ExamStatus.IN_PROGRESS;
    }

    public void addTask(Task task){
        if(task != null){
            this.tasks.add(task);
        }
    }
}
