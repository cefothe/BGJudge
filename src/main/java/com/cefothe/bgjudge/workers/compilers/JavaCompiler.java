package com.cefothe.bgjudge.workers.compilers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by cefothe on 13.07.16.
 */
public class JavaCompiler implements  Compiler {

    @Override
    public void compile(String directory, String className) throws IOException {

        Process process = new ProcessBuilder("javac",directory+className)
                .redirectErrorStream(false)
                .start();


        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        //Throw same exeption
        if(br!=null && br.readLine()!=null && !br.readLine().isEmpty()){
            throw  new IllegalArgumentException();
        }
    }
}