package com.cefothe.bgjudge.workers.executors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by cefothe on 13.07.16.
 */
public class JavaExecutor implements  Executor {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaExecutor.class);

    @Override
    public ExecutorResult execute(File file, ExecutorResult result) throws IOException {
        LOGGER.info("Start executing file {}", file.getAbsolutePath());
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

        LOGGER.info("Finish executing file {}", file.getAbsolutePath());
        return  result;
    }

    private String getPath(File file) {
        return "/"+ FilenameUtils.getPath(file.getAbsolutePath());
    }
}
