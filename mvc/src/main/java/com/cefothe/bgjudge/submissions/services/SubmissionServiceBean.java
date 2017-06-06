package com.cefothe.bgjudge.submissions.services;

import com.cefothe.bgjudge.submissions.dto.SubmissionTO;
import com.cefothe.common.io.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Created by cefothe on 06.05.17.
 */
@Service
public class SubmissionServiceBean implements SubmissionService{

    private final FileIO fileIO;

    @Autowired
    public SubmissionServiceBean(FileIO fileIO) {
        this.fileIO = fileIO;
    }

    @Override
    public void create(SubmissionTO submissionTO) throws IOException {
        File file = fileIO.write(submissionTO.getCode(), "Main.java");
        System.out.println(file.getAbsolutePath());
    }
}
