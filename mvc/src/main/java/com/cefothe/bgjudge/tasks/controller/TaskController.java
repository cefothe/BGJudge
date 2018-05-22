package com.cefothe.bgjudge.tasks.controller;

import com.cefothe.bgjudge.tasks.models.binding.CreateTaskModel;
import com.cefothe.bgjudge.tasks.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by cefothe on 14.06.17.
 */
@Controller
@RequestMapping("task")
@PreAuthorize("hasAuthority('TEACHER')")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("{id}/create")
    public String createTask(@PathVariable("id") Long examId, @Valid CreateTaskModel createTaskModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createExamModel", createTaskModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createExamModel", bindingResult);
            return "redirect:/task/"+examId+"/create";
        }
        taskService.createTask(createTaskModel,examId);
        return "redirect:/exam/open/"+ examId;
    }

    @GetMapping("{id}/create")
    public String createTaskPage(Model model, CreateTaskModel createTaskModel, @PathVariable("id") Long examId){
        model.addAttribute("title", "Create task");
        model.addAttribute("view", "task/create");
        model.addAttribute("examId", examId);
        model.addAttribute("createTaskModel", createTaskModel);
        return "base-layout";
    }
}
