package com.cefothe.bgjudge.workers;

import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by cefothe on 07.05.17.
 */
public class JavaCompilerTest {

    @After
    public void clearCompilerDirectoryFromClasses(){
        File directory = new File(JavaCompilerTest.class.getResource("/compiler/").getFile());
        Arrays.stream(directory.listFiles((f, p) -> p.endsWith("class"))).forEach(File::delete);
    }

    @Test
    public void compileJavaFile() throws IOException {
        File file = new File(JavaCompilerTest.class.getResource("/compiler/Test.java").getFile());
        ProgramLanguages programLanguages = ProgramLanguages.JAVA;
        programLanguages.compiler().compile(file);
        new File(JavaCompilerTest.class.getResource("/compiler/Test.class").getFile());
    }

    @Test(expected = IllegalArgumentException.class)
    public void failCompileJavaFile() throws IOException {
        File file = new File(JavaCompilerTest.class.getResource("/compiler/TestFail.java").getFile());
        ProgramLanguages programLanguages = ProgramLanguages.JAVA;
        programLanguages.compiler().compile(file);
    }
}
