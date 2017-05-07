package com.cefothe.bgjudge.workers.compilers;

import java.io.File;
import java.io.IOException;

/**
 * This interface represent main function on each compiles
 * Created by cefothe on 07.05.17.
 */
public interface Compiler {
    /**
     * This run compile
     */
    CompilationResult compile(File file) throws IOException;
}
