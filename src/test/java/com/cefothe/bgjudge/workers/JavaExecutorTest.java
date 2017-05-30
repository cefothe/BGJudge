package com.cefothe.bgjudge.workers;

import com.cefothe.bgjudge.workers.executors.ExecutorResult;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * Created by cefothe on 08.05.17.
 */
public class JavaExecutorTest {

    @Test
    public void executeJavaFile() throws IOException {
        File executeFile = compileJavaFile("Test");
        ProgramLanguages programLanguages = ProgramLanguages.JAVA;
        ExecutorResult executorResult = new ExecutorResult(Collections.EMPTY_LIST);
        executorResult = programLanguages.executor().execute(executeFile, executorResult);
        assertThat(executorResult.getErrorStream(),isEmptyOrNullString());
        assertThat(executorResult.getOutput(),emptyCollectionOf(String.class));
    }

    @Test
    public void executeWithInputParams() throws IOException {
        File executeFile = compileJavaFile("CorrectExecutorTest");
        ProgramLanguages programLanguages = ProgramLanguages.JAVA;
        List<String> inputParams= Arrays.asList("Test");
        ExecutorResult executorResult = new ExecutorResult(inputParams);
        executorResult = programLanguages.executor().execute(executeFile, executorResult);
        assertThat(executorResult.getErrorStream(),isEmptyOrNullString());
        assertThat(executorResult.getOutput(), is(inputParams));

    }

    @Test
    public void executeJavaFileWithNullPointer() throws IOException {
        File executeFile = compileJavaFile("NullPointerTest");
        ProgramLanguages programLanguages = ProgramLanguages.JAVA;
        ExecutorResult executorResult = new ExecutorResult(Collections.EMPTY_LIST);
        executorResult = programLanguages.executor().execute(executeFile, executorResult);
        assertThat(executorResult.getErrorStream(),notNullValue());
    }

    public File compileJavaFile(String fileName) throws IOException {
        ProgramLanguages programLanguages = ProgramLanguages.JAVA;
        File file = new File(JavaCompilerTest.class.getResource("/compiler/" + fileName + programLanguages.getExtension()).getFile());
        programLanguages.compiler().compile(file);
      return new File(JavaCompilerTest.class.getResource("/compiler/"+fileName+".class").getFile());
    }
}
