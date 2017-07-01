package com.cefothe.bgjudge.tasks.entities;

import com.cefothe.bgjudge.taskparams.entities.TaskParam;
import com.cefothe.common.entities.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cefothe on 04.05.17.
 */
@Entity
@Table(name = "tasks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public class Task extends BaseEntity{

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    @Lob
    private String description;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
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

    public void addTaskParams(List<TaskParam> taskParams){
        if(taskParams!=null){
            this.taskPrams.addAll(taskParams);
        }
    }
}
