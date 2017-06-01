package com.cefothe.bgjudge.workers.compilers;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by cefothe on 13.07.16.
 */
public class JavaCompiler implements  Compiler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaCompiler.class);

    @Override
    public CompilationResult compile(File file) throws IOException {

        LOGGER.info("Start compiling file {}", file.getAbsolutePath());

        CompilationResult compilationResult = new CompilationResult();
        Process process = new ProcessBuilder("javac", file.getAbsolutePath())
                .redirectErrorStream(false)
                .start();

        StringWriter errorWriter = new StringWriter();
        IOUtils.copy(process.getErrorStream(),errorWriter);
        compilationResult.setErrorStream(errorWriter.toString());
        process.destroy();

        LOGGER.info("Finish compiling file {}", file.getAbsolutePath());
        return  compilationResult;
    }
}