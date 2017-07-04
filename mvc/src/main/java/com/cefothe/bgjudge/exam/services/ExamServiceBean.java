package com.cefothe.bgjudge.exam.services;

import com.cefothe.bgjudge.exam.entities.ExamSecurity;
import com.cefothe.bgjudge.exam.entities.ExamStatus;
import com.cefothe.bgjudge.exam.entities.Examens;
import com.cefothe.bgjudge.exam.models.view.ViewExamDetailsModel;
import com.cefothe.bgjudge.exam.models.view.ViewExamTasksModel;
import com.cefothe.bgjudge.exam.models.view.ViewTaskModel;
import com.cefothe.bgjudge.submissions.dto.SubmissionResultTO;
import com.cefothe.bgjudge.submissions.repositories.SubmissionRepository;
import com.cefothe.bgjudge.user.entities.User;
import com.cefothe.common.component.AuthenticationFacade;

import com.cefothe.bgjudge.exam.models.binding.CreateExamModel;
import com.cefothe.bgjudge.exam.models.view.ViewExamModel;
import com.cefothe.bgjudge.exam.repositories.ExamRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by cefothe on 04.05.17.
 */
@Service
@Transactional
public class ExamServiceBean implements ExamService {

    private final ExamRepository examRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationFacade authenticationFacade;
    private final SubmissionRepository submissionRepository;

    @Autowired
    public ExamServiceBean(ExamRepository examRepository, ModelMapper modelMapper, AuthenticationFacade authenticationFacade, SubmissionRepository submissionRepository) {
        this.examRepository = examRepository;
        this.modelMapper = modelMapper;
        this.authenticationFacade = authenticationFacade;
        this.submissionRepository = submissionRepository;
    }

    @Override
    public void create(CreateExamModel createExamModel) {
        Converter<CreateExamModel, Examens> examConverter = new Converter<CreateExamModel, Examens>() {

            @Override
            public Examens convert(MappingContext<CreateExamModel, Examens> context) {
                CreateExamModel createExamModel = context.getSource();
                ExamSecurity examSecurity = new ExamSecurity(createExamModel.getExamPassword(), Arrays.asList(createExamModel.getAllowedIP()));

                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
                Date date = null;
                try {
                    date = formatter.parse(createExamModel.getExamDate());
                } catch (ParseException e) {

                }
                java.sql.Timestamp examDate = new Timestamp(date.getTime());

                return  new Examens(createExamModel.getName(),examDate, createExamModel.getExamLength(),authenticationFacade.getUser(), examSecurity);

            }
        };
        this.modelMapper.addConverter(examConverter);
        Examens examens = this.modelMapper.map(createExamModel,Examens.class);
        this.examRepository.save(examens);
    }

    @Override
    public Examens update(Examens examens) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void changeStatus(ExamStatus examStatus, Long examId) {
        Examens examens = examRepository.findOne(examId);
        examens.setExamStatus(examStatus);
        examRepository.save(examens);
    }

    @Override
    public List<ViewExamModel> getAllExams() {
        List<ViewExamModel> viewExamModels = new ArrayList<>();
        this.examRepository.findAll().forEach(examens -> viewExamModels.add(this.modelMapper.map(examens,ViewExamModel.class)));
        return viewExamModels;
    }

    @Override
    public ViewExamDetailsModel get(Long id) {
        Examens examens = this.examRepository.findOne(id);
        return this.modelMapper.map(examens, ViewExamDetailsModel.class);
    }

    @Override
    public ViewExamTasksModel getExamTasks(Long id) {
        Examens examens = this.examRepository.findOne(id);
        ArrayList<ViewTaskModel> taskModels = new ArrayList<>();
        User user = this.authenticationFacade.getUser();
        examens.getTasks().stream().forEach(task -> {
            ArrayList<SubmissionResultTO> submissionResultTO = new ArrayList<>();
            submissionRepository.findSubmissionByUserTaskExam(examens,task,user).forEach(x->{
                submissionResultTO.add(new SubmissionResultTO(x.getStatus(),x.getId(),x.getResult()));
            });
            ViewTaskModel viewTaskModel = this.modelMapper.map(task, ViewTaskModel.class);
            viewTaskModel.setSubmissionResults(submissionResultTO);
            taskModels.add(viewTaskModel);

        });
        return new ViewExamTasksModel(examens.getId(), examens.getName(), taskModels);
    }
}
