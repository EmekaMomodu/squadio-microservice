package com.emekamomodu.squadio.service.implementation;

import com.emekamomodu.squadio.entity.Role;
import com.emekamomodu.squadio.exception.custom.ObjectNotFoundException;
import com.emekamomodu.squadio.repository.RoleRepository;
import com.emekamomodu.squadio.service.IRoleService;
import com.emekamomodu.squadio.model.ERole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 10:43 PM
 */
@Service
public class RoleService implements IRoleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
        Role createdRole = new Role();
        try {
            logger.info("Creating new Role: {}", role);
            createdRole = roleRepository.save(role);
        } catch (Exception exception) {
            logger.error("Error in creating role: {}", exception.getMessage());
        }
        return createdRole;
    }

    @Override
    public Role update(Role roleUpdate, Long id) throws ObjectNotFoundException {
        logger.info("Updating role id {} details with {}", id.toString(), roleUpdate);
        return roleRepository.findById(id).map(role -> {
            if (!Objects.isNull(roleUpdate.getRoleName())) {
                role.setRoleName(roleUpdate.getRoleName());
            }
            return roleRepository.save(role);
        }).map(Role::new).orElseThrow(() -> new ObjectNotFoundException("Role with ID '" + id + "' not found"));
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        try {
            logger.info("Getting all roles");
            for (Role role : roleRepository.findAll()) {
                roles.add(new Role(role));
            }
        } catch (Exception ex) {
            logger.error("Error in fetching all roles: {}", ex.getMessage());
        }
        return roles;
    }

    @Override
    public Role getByID(Long id) {
        logger.info("Finding role with id: {}", id.toString());
        return roleRepository.findById(id).map(Role::new).orElseThrow(() -> new ObjectNotFoundException("Role with ID '" + id + "' not found"));
    }

    @Override
    public Role getByRoleName(ERole roleName) {
        logger.info("Finding role with name: {}", roleName);
        return roleRepository.findByRoleName(roleName).map(Role::new).orElseThrow(() -> new ObjectNotFoundException("Role with name '" + roleName + "' not found"));
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting role with id: {}", id.toString());
        roleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Role with ID '" + id + "' not found"));
        roleRepository.deleteById(id);
    }
}
