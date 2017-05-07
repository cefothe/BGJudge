package com.cefothe.bgjudge.workers.executors;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cefothe on 13.07.16.
 */
public class JavaExecutor implements  Executor {

    @Override
    public ExecutorResult execute(File file, ExecutorResult result) throws IOException {
        Process process =
                new ProcessBuilder("java","-cp", getPath(file), FilenameUtils.getBaseName(file.getName()))
                        .redirectErrorStream(false)
                        .start();

        try (OutputStream stdin = process.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin)))
        {
         for(String param: result.getInput()){
            writer.write(param);
            }
        }
        StringWriter errorWriter = new StringWriter();
        IOUtils.copy(process.getErrorStream(),errorWriter);
        result.setErrorStream(errorWriter.toString());

        result.setOutput(IOUtils.readLines(process.getInputStream(), "UTF-8"));

        process.destroy();
        return  result;
    }

    private String getPath(File file) {
        return "/"+ FilenameUtils.getPath(file.getAbsolutePath());
    }
}
