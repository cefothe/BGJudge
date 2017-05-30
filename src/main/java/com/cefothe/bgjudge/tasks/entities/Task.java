package com.cefothe.bgjudge.tasks.entities;

import com.cefothe.bgjudge.taskparams.entities.TaskParam;
import com.cefothe.common.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cefothe on 04.05.17.
 */
@Entity
@Table(name = "tasks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task extends BaseEntity{

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    @Lob
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Getter
    private List<TaskParam> taskPrams = new ArrayList<>(0);

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void addTaskParam(TaskParam taskParam){
        if(taskParam != null){
            taskPrams.add(taskParam);
        }
    }
}
