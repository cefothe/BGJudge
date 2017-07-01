package com.cefothe.bgjudge.workers.executors;

import com.cefothe.bgjudge.workers.Result;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by cefothe on 08.05.17.
 */

public class ExecutorResult extends Result {

    @Getter
    private List<String> input;

    @Getter
    @Setter
    @NotNull
    private List<String> output;

    public ExecutorResult(List<String> input) {
        this.input = input;
    }

}
