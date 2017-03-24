package com.team.icr.user.controller;

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

import com.team.icr.user.entity.User;
import com.team.icr.user.entity.UserRole;
import com.team.icr.user.enums.Role;
import com.team.icr.user.service.UserService;
import com.team.icr.util.JsonHelper;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;

	@Test
	public void testFindUserById() throws Exception {
		final User daniel = new User("Daniel", "Avery", new UserRole(Role.ADMIN));
		daniel.setId(23);
		final String danielJson = JsonHelper.convertObjectToJson(daniel);
		given(this.userService.findUserById(daniel.getId())).willReturn(daniel);
		this.mvc.perform(get("/findUserById?id=23").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(danielJson));
	}
}
