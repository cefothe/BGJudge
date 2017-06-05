package com.cefothe.common.io;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by cefothe on 06.05.17.
 */
@Component
public class CommonFileIO implements FileIO {

    @Value("${file.directory}")
    public String fileDirectory;

    @Override
    public File write(String fileContent, String fileName) throws IOException {

        String directory = generateSubDirectory();
        File file = Paths.get(directory,fileName).toFile();
        try (
                OutputStream outputStream = new FileOutputStream(file);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))
        ) {
            bufferedWriter.write(fileContent);
        }
        return file;
    }

    private String generateSubDirectory() throws IOException {
        Path directory = Paths.get(fileDirectory, UUID.randomUUID().toString());
        Files.createDirectories(directory);
        return directory.toString();
    }
}
