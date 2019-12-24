package com.cefothe.bgjudge.workers;

import com.cefothe.bgjudge.workers.compilers.Compiler;
import com.cefothe.bgjudge.workers.compilers.JavaCompiler;
import com.cefothe.bgjudge.workers.executors.Executor;
import com.cefothe.bgjudge.workers.executors.JavaExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by cefothe on 07.05.17.
 */
public enum ProgramLanguages {

    JAVA(JavaCompiler.class, JavaExecutor.class, ".java");

    private static final Logger LOG = LogManager.getLogger(ProgramLanguages.class);

    Class<? extends Compiler> compiler;
    Class<? extends Executor> executor;
    String extension;

    ProgramLanguages(Class<? extends Compiler> compiler,Class<? extends Executor> executor, String extension){
        this.compiler = compiler;
        this.executor = executor;
        this.extension = extension;
    }

    public Compiler compiler() {
        try {
            Constructor<? extends Compiler> constructor = compiler.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            LOG.warn("Problem with construct COMPILER ", e);
        }
        return null;
    }

    public Executor executor(){
        try{
            Constructor<? extends Executor> constructor = executor.getConstructor();
            return constructor.newInstance();
        }catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            LOG.warn("Problem with construct EXECUTOR ", e);
        }
        return null;
    }

    public String getExtension() {
        return extension;
    }

    public String fileWithExtension(String fileName){
        return  fileName + getExtension();
    }

}
