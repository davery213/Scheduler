package com.team.icr.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.icr.task.entity.Task;
import com.team.icr.task.repo.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskDao;

	public Task createNewTask(final String taskName, final int version) {
		final Task task = new Task(taskName, version);
		return this.taskDao.save(task);
	}

	public Task createNewTaskJson(final Task task) {
		return this.taskDao.save(task);
	}

	public Task findTaskByName(final String taskName) {
		final List<Task> tasks = this.taskDao.findByNameOrderByVersionDesc(taskName);

		return tasks.get(0);
	}
}
