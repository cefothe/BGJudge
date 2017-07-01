package com.cefothe.bgjudge.io;

import com.cefothe.bgjudge.workers.strategies.StrategyBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonFileIO.class);

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
        LOGGER.info("Create source file in {}", file.getAbsolutePath());
        return file;
    }

    @Override
    public void deleteDirectory(File fileName) throws IOException {
        File directory = fileName.getParentFile();
        if(!FileSystemUtils.deleteRecursively(directory)){
            throw  new IOException("Can't delete directory " + directory.getName());
        }
        LOGGER.info("Delete source file and directory related to it {}", fileName.getAbsolutePath());
    }

    private String generateSubDirectory() throws IOException {
        Path directory = Paths.get(fileDirectory, UUID.randomUUID().toString());
        Files.createDirectories(directory);
        return directory.toString();
    }
}
