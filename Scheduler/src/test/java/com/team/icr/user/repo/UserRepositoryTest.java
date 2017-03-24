package com.team.icr.user.repo;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.team.icr.user.entity.User;
import com.team.icr.user.entity.UserRole;
import com.team.icr.user.enums.Role;
import com.team.icr.user.repo.UserRepository;
import com.team.icr.user.repo.UserRoleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userDao;

	@Autowired
	private UserRoleRepository roleDao;

	private User daniel;
	private User sara;
	private User jack;
	private UserRole admin;
	private UserRole scheduler;
	private UserRole viewer;

	@Before
	public void setup() {
		cleanupTables();
		setupUserRoles();
		setupUsers();
	}

	private void cleanupTables() {
		this.userDao.deleteAll();
		this.roleDao.deleteAll();
	}

	private void setupUserRoles() {
		this.admin = new UserRole(Role.ADMIN);
		this.scheduler = new UserRole(Role.SCHEDULER);
		this.viewer = new UserRole(Role.VIEWER);

		this.roleDao.save(Arrays.asList(this.admin, this.scheduler, this.viewer));
	}

	private void setupUsers() {
		this.daniel = new User("Daniel", "Avery", this.admin);
		this.sara = new User("Sara", "James", this.scheduler);
		this.jack = new User("Jack", "Avery", this.viewer);

		this.userDao.save(Arrays.asList(this.daniel, this.sara, this.jack));
	}

	@Test
	public void testFindUserById() {
		User lookupUser = this.userDao.findOne(this.daniel.getId());
		Assert.assertEquals("User lookup failed for Daniel!", this.daniel, lookupUser);

		lookupUser = this.userDao.findOne(this.sara.getId());
		Assert.assertEquals("User lookup failed for Sara!", this.sara, lookupUser);

		lookupUser = this.userDao.findOne(this.jack.getId());
		Assert.assertEquals("User lookup failed for Jack!", this.jack, lookupUser);
	}

	@Test
	public void testCreateUser() {
		final User newUser = new User("Dummy", "Data", this.viewer);
		this.userDao.save(newUser);

		Assert.assertNotEquals("Id is 0!", 0, newUser.getId());
	}
}
