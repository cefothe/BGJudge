package com.cefothe.common.io;

import java.io.File;
import java.io.IOException;

/**
 * Created by cefothe on 06.05.17.
 */
public interface FileIO {
    File write(String content, String file) throws IOException;
}
