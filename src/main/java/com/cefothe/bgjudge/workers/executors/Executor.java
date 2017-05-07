package com.cefothe.bgjudge.workers.executors;


import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by cefothe on 13.07.16.
 */
public interface Executor {
    List<String> execute(File file, List<String> params)   throws IOException;
}