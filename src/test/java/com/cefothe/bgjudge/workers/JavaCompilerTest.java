package com.cefothe.bgjudge.workers;

import com.cefothe.bgjudge.workers.compilers.CompilationResult;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        CompilationResult compilationResult = programLanguages.compiler().compile(file);
        assertThat(compilationResult.getErrorStream(),isEmptyOrNullString());
        new File(JavaCompilerTest.class.getResource("/compiler/Test.class").getFile());
    }

    @Test
    public void failCompileJavaFile() throws IOException {
        File file = new File(JavaCompilerTest.class.getResource("/compiler/TestFail.java").getFile());
        ProgramLanguages programLanguages = ProgramLanguages.JAVA;
        CompilationResult compilationResult = programLanguages.compiler().compile(file);
        assertThat(compilationResult.getErrorStream(),notNullValue());

    }
}
