package com.cefothe.bgjudge.workers.executors;


import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by cefothe on 13.07.16.
 */
public interface Executor {
    ExecutorResult execute(File file, ExecutorResult result)   throws IOException;
}