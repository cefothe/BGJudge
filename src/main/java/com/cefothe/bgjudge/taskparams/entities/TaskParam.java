package com.cefothe.bgjudge.taskparams.entities;

import com.cefothe.common.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by cefothe on 05.05.17.
 */
@Entity
@Table(name = "task_param")
@NoArgsConstructor
@AllArgsConstructor
public class TaskParam  extends BaseEntity{

    @Getter
    @Setter
    private String input;

    @Getter
    @Setter
    private String output;


}
