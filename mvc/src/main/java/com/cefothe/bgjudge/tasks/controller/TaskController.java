package com.cefothe.bgjudge.tasks.controller;

import lombok.Getter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cefothe on 14.06.17.
 */
@Controller
@RequestMapping("task")
@PreAuthorize("hasAuthority('TEACHER')")
public class TaskController {

    @GetMapping("{id}/create")
    public String createTask(Model model, @PathVariable("id") Long examId){
        model.addAttribute("title", "Create task");
        model.addAttribute("view", "task/create");
        return "base-layout";
    }
}
