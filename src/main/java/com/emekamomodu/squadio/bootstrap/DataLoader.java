package com.emekamomodu.squadio.bootstrap;

import com.emekamomodu.squadio.entity.Role;
import com.emekamomodu.squadio.model.request.CreateUserRequest;
import com.emekamomodu.squadio.service.IRoleService;
import com.emekamomodu.squadio.service.IUserService;
import com.emekamomodu.squadio.model.ERole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserService userService;

	@Override
	public void run(String... args) throws Exception {

		logger.info("Loading data");

		// Create Roles
		Role adminRole = new Role(ERole.ROLE_ADMIN);
		Role userRole = new Role(ERole.ROLE_USER);
		roleService.create(adminRole);
		roleService.create(userRole);

		// Create Users
		CreateUserRequest adminUser = new CreateUserRequest("Admin", "admin", new HashSet<>(Collections.singletonList("ADMIN")));
		CreateUserRequest user1 = new CreateUserRequest("Mohamed", "user", new HashSet<>(Collections.singletonList("USER")));
		CreateUserRequest user2 = new CreateUserRequest("John", "user", new HashSet<>(Collections.singletonList("USER")));
		CreateUserRequest user3 = new CreateUserRequest("Kumar", "user", new HashSet<>(Collections.singletonList("USER")));
		userService.create(adminUser);
		userService.create(user1);
		userService.create(user2);
		userService.create(user3);

		logger.info("Data loaded successfully");

	}

}
