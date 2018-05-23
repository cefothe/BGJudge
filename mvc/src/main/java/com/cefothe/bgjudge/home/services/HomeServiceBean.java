package com.cefothe.bgjudge.home.services;

import com.cefothe.bgjudge.exam.repositories.ExamRepository;
import com.cefothe.bgjudge.home.models.view.ContestModel;
import com.cefothe.bgjudge.home.models.view.HomePageModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cefothe on 26.06.17.
 */
@Service
public class HomeServiceBean implements HomeService {

    private final ExamRepository examRepository;

    @Autowired
    public HomeServiceBean(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public HomePageModel homePage() {
        List<ContestModel> contests = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");
        examRepository.findAll().forEach(examens -> {
            ContestModel contestModel = new ContestModel(examens.getName(),dateFormat.format(examens.getExamDate()),examens.getId());
            contests.add(contestModel);
        });

        return new HomePageModel(contests);
    }
}
