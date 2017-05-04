package com.cefothe.exam.entitities;

import com.cefothe.common.entities.BaseEntity;
import com.cefothe.tasks.entities.Task;
import com.cefothe.user.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String name;

    @Getter
    @Column(name = "exam_date", nullable = false)
    private LocalDateTime examDate;

    @Getter
    @Column(name = "exam_lenght", nullable = false)
    private long examLength;

    @OneToMany
    @Getter
    private List<Task> tasks = new ArrayList<>();

    @OneToOne
    @Getter
    private User user;

    public Examens(String name, LocalDateTime examDate, long examLength, User user) {
        this.name = name;
        this.examDate = examDate;
        this.examLength = examLength;
        this.user = user;
    }

    public void addTask(Task task){
        if(task != null){
            this.tasks.add(task);
        }
    }
}
