package com.emekamomodu.squadio.bootstrap;

import com.emekamomodu.squadio.dto.RoleDto;
import com.emekamomodu.squadio.service.IRoleService;
import com.emekamomodu.squadio.utility.enums.ERole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IRoleService roleService;

	@Override
	public void run(String... args) throws Exception {

		logger.info("Loading data");

		// Create Roles
		RoleDto adminRole = new RoleDto(ERole.ROLE_ADMIN);
		RoleDto userRole = new RoleDto(ERole.ROLE_USER);
		RoleDto adminRoleCreated = roleService.create(adminRole);
		RoleDto userRoleCreated = roleService.create(userRole);

		// Create Users


		logger.info("Data loaded successfully");

	}

}
