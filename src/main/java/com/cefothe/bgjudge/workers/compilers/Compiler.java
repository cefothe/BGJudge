package com.cefothe.bgjudge.workers.compilers;

import java.io.IOException;

/**
 * This interface represent main function on each compiles
 * Created by cefothe on 07.05.17.
 */
public interface Compiler {
    /**
     * This run compile
     * @param directory Directory when is a class
     * @param className Current class name
     */
    void compile(String directory, String className) throws IOException;
}
