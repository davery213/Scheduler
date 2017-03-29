package com.team.icr.task.repo;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.team.icr.task.entity.Task;
import com.team.icr.util.TaskCreator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryTest {

	@Autowired
	private TaskRepository taskRepo;

	@Before
	public void setup() {
		cleanupTaskTable();
	}

	@After
	public void tearDown() {
		cleanupTaskTable();
	}

	private void cleanupTaskTable() {
		this.taskRepo.deleteAll();
	}

	@Test
	public void testInsertTask() {
		final Task task = TaskCreator.createTask(1);
		final Task newTask = this.taskRepo.save(task);

		Assert.assertNotEquals("Id was not generated!", 0, newTask.getId());
	}

	@Test
	public void testDeleteTask() {
		final Task task = TaskCreator.createTask(1);
		final Task newTask = this.taskRepo.save(task);

		this.taskRepo.delete(newTask);
		final boolean hasElements = this.taskRepo.findAll()
				.iterator()
				.hasNext();

		Assert.assertTrue("The Task table still had elements!", !hasElements);
	}

	@Test
	public void testFindTaskByTaskName() {
		final Task task1 = TaskCreator.createTask(1);
		final Task task2 = TaskCreator.createTask(2);
		this.taskRepo.save(Arrays.asList(task1, task2));

		final List<Task> tasks = this.taskRepo.findByNameOrderByVersionDesc(TaskCreator.TASK_NAME);

		Assert.assertTrue("tasks is empty!", !tasks.isEmpty());
	}
}
