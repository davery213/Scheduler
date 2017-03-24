package com.team.icr.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team.icr.task.entity.Task;
import com.team.icr.task.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;

	@RequestMapping("/task/create")
	public Task createTask(@RequestParam(value = "taskName") final String taskName,
			@RequestParam(value = "version") final int version) {
		return this.taskService.createNewTask(taskName, version);
	}

	@RequestMapping("/task/findTask")
	public Task findTask(@RequestParam(value = "taskName") final String taskName) {
		return this.taskService.findTaskByName(taskName);
	}
}
