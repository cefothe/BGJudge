package com.cefothe.bgjudge.workers.executors;


import java.io.IOException;
import java.util.List;

/**
 * Created by cefothe on 13.07.16.
 */
public interface Executor {
    List<String> execute(String directory, String classNam, List<String> params)   throws IOException;
}