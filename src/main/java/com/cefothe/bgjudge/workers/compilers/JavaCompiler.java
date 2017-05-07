package com.cefothe.bgjudge.workers.compilers;

import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * Created by cefothe on 13.07.16.
 */
public class JavaCompiler implements  Compiler {

    @Override
    public CompilationResult compile(File file) throws IOException {

        CompilationResult compilationResult = new CompilationResult();
        Process process = new ProcessBuilder("javac", file.getAbsolutePath())
                .redirectErrorStream(false)
                .start();

        StringWriter errorWriter = new StringWriter();
        IOUtils.copy(process.getErrorStream(),errorWriter);
        compilationResult.setErrorStream(errorWriter.toString());


        return  compilationResult;
    }
}