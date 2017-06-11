package com.cefothe.bgjudge.workers.entities;

import com.cefothe.bgjudge.taskparams.entities.TaskParam;
import com.cefothe.common.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by cefothe on 31.05.17.
 */
@Entity
@Table(name = "test_results")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TestResults extends BaseEntity {

    @Getter
    @ManyToOne
    private TaskParam taskParam;

    @Getter
    @Lob
    @Column(name = "actual_output")
    private String actualOutput;

    @Getter
    @Lob
    @Column(name = "runtime_error")
    private String runtimeError;

}
