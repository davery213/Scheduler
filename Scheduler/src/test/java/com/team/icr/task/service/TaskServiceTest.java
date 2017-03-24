package com.team.icr.task.service;

import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.team.icr.task.entity.Task;
import com.team.icr.task.repo.TaskRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {

	private static String TASK_NAME = "Golf";

	@MockBean
	private TaskRepository taskRepo;

	@Autowired
	private TaskService service;

	private List<Task> tasks;

	@Before
	public void setup() {
		this.tasks = new ArrayList<>();
		this.tasks.add(new Task(TASK_NAME, 2));
		this.tasks.add(new Task(TASK_NAME, 1));
	}

	@Test
	public void testGetTaskByName() {
		given(this.taskRepo.findByNameOrderByVersionDesc("Golf")).willReturn(this.tasks);
		final Task latestVersion = this.service.findTaskByName(TASK_NAME);

		Assert.assertEquals("Versions don't match!", 2, latestVersion.getVersion());
	}
}
