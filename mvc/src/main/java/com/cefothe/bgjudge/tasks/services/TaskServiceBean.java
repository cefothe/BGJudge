package com.cefothe.bgjudge.tasks.services;

import com.cefothe.bgjudge.exam.entitities.Examens;
import com.cefothe.bgjudge.exam.repositories.ExamRepository;
import com.cefothe.bgjudge.taskparams.entities.TaskParam;
import com.cefothe.bgjudge.tasks.entities.Task;
import com.cefothe.bgjudge.tasks.models.binding.CreateTaskModel;
import com.cefothe.bgjudge.tasks.repositories.TaskRepositories;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cefothe on 15.06.17.
 */
@Service
public class TaskServiceBean implements TaskService {

    private final ModelMapper modelMapper;
    private final TaskRepositories taskRepositories;
    private final ExamRepository examRepository;

    @Autowired
    public TaskServiceBean(ModelMapper modelMapper, TaskRepositories taskRepositories, ExamRepository examRepository) {
        this.modelMapper = modelMapper;
        this.taskRepositories = taskRepositories;
        this.examRepository = examRepository;
    }

    @Override
    public void createTask(CreateTaskModel createTaskModel, Long examId) {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Converter<CreateTaskModel, Task> taskConverter = new Converter<CreateTaskModel, Task>() {
            @Override
            public Task convert(MappingContext<CreateTaskModel, Task> context) {
            CreateTaskModel source = context.getSource();
            List<TaskParam> taskParams = Arrays.asList(new TaskParam(source.getInputOne(), source.getOutputOne()),new TaskParam(source.getInputTwo(), source.getOutputTwo()));
            Task task = new Task(source.getTitle(),source.getDescription());
            task.addTaskParams(taskParams);
            return task;
        }};

        this.modelMapper.addConverter(taskConverter);
        Task task = this.modelMapper.map(createTaskModel,Task.class);
        taskRepositories.save(task);
        Examens exam = examRepository.findOne(examId);
        exam.addTask(task);
        examRepository.save(exam);
    }
}
