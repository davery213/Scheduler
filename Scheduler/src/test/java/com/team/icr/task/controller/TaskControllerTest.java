package com.team.icr.task.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.team.icr.task.entity.Task;
import com.team.icr.task.service.TaskService;
import com.team.icr.util.JsonHelper;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

	private static final String TASK_NAME = "Golf";

	@Autowired
	private MockMvc mvc;

	@MockBean
	private TaskService taskService;

	@Test
	public void testFindTaskByName() throws Exception {
		final Task golfTask = new Task(TASK_NAME, 2);
		final String golfTaskJson = JsonHelper.convertObjectToJson(golfTask);

		given(this.taskService.findTaskByName(TASK_NAME)).willReturn(golfTask);
		this.mvc.perform(get("/task/findTask?taskName=Golf").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(golfTaskJson));
	}

}
