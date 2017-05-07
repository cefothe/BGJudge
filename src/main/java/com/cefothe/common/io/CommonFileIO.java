package com.cefothe.common.io;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by cefothe on 06.05.17.
 */
@Component
public class CommonFileIO implements FileIO {

    @Value("${file.directory}")
    public String fileDirectory;

    @Override
    public File write(String fileContent, String fileName) throws IOException {
        try (
                OutputStream outputStream = new FileOutputStream(fileDirectory + fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))
        ) {
            bufferedWriter.write(fileContent);
        }
        return new File(fileDirectory + fileName);
    }
}
