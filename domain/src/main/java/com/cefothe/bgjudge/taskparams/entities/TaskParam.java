package com.cefothe.bgjudge.taskparams.entities;

import com.cefothe.common.entities.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cefothe on 05.05.17.
 */
@Entity
@Table(name = "task_param")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TaskParam extends BaseEntity{

    @Getter
    @Setter
    private String input;

    @Getter
    @Setter
    private String output;

    public List<String> getInputs(){
        return Arrays.asList(input.split(System.lineSeparator()));
    }
    public List<String> getOutputs(){
        return Arrays.asList(output.split(System.lineSeparator()));
    }

}
