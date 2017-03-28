package com.team.icr.task.repo;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.team.icr.task.entity.Task;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryTest {

	private static final String TASK_NAME = "Golf";

	@Autowired
	private TaskRepository taskDao;

	@Before
	public void setup() {
		cleanupTaskTable();
	}

	private Task createTask(final int version) {
		final Task task = new Task();
		task.setName(TASK_NAME);
		task.setVersion(version);
		final LocalDateTime start = LocalDateTime.now();
		task.setStartTime(start);
		task.setEndTime(start.plusMinutes(20));
		task.setPriority(115);

		return task;
	}

	private void cleanupTaskTable() {
		this.taskDao.deleteAll();
	}

	@Test
	public void testInsertTask() {
		final Task task = createTask(1);
		final Task newTask = this.taskDao.save(task);

		Assert.assertNotEquals("Id was not generated!", 0, newTask.getId());
	}

	@Test
	public void testDeleteTask() {
		final Task task = createTask(1);
		final Task newTask = this.taskDao.save(task);

		this.taskDao.delete(newTask);
		final boolean hasElements = this.taskDao.findAll()
				.iterator()
				.hasNext();

		Assert.assertTrue("The Task table still had elements!", !hasElements);
	}

	@Test
	public void testFindTaskByTaskName() {
		final Task task1 = createTask(1);
		final Task task2 = createTask(2);
		this.taskDao.save(Arrays.asList(task1, task2));

		final List<Task> tasks = this.taskDao.findByNameOrderByVersionDesc(TASK_NAME);

		Assert.assertTrue("tasks is empty!", !tasks.isEmpty());
	}
}
