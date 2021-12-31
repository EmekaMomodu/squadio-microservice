package com.emekamomodu.squadio.service.implementation;

import com.emekamomodu.squadio.dto.RoleDto;
import com.emekamomodu.squadio.entity.Role;
import com.emekamomodu.squadio.exception.ObjectNotFoundException;
import com.emekamomodu.squadio.repository.RoleRepository;
import com.emekamomodu.squadio.service.IRoleService;
import com.emekamomodu.squadio.utility.enums.ERole;
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
    public RoleDto create(RoleDto roleDto) {
        RoleDto createdRoleDto = new RoleDto();
        try {
            logger.info("Creating new Role: {}", roleDto);
            Role newRole = new Role();
            newRole.setRoleName(roleDto.getName());
            Role createdRole = roleRepository.save(newRole);
            createdRoleDto.setId(createdRole.getRoleId());
            createdRoleDto.setName(createdRole.getRoleName());
        } catch (Exception exception) {
            logger.error("Error in creating role: {}", exception.getMessage());
        }
        return createdRoleDto;
    }

    @Override
    public RoleDto update(RoleDto roleDto, Long id) {
        logger.info("Updating role id {} details with {}", id.toString(), roleDto);
        return roleRepository.findById(id).map(role -> {
            if (!Objects.isNull(roleDto.getName())) {
                role.setRoleName(roleDto.getName());
            }
            return roleRepository.save(role);
        }).map(RoleDto::new).orElseThrow(() -> new ObjectNotFoundException("Role", id));
    }

    @Override
    public List<RoleDto> getAll() {
        List<RoleDto> roles = new ArrayList<>();
        try {
            logger.info("Getting all roles");
            for (Role role : roleRepository.findAll()) {
                roles.add(new RoleDto(role));
            }
        } catch (Exception ex) {
            logger.error("Error in fetching all roles: {}", ex.getMessage());
        }
        return roles;
    }

    @Override
    public RoleDto getByID(Long id) {
        logger.info("Finding role with id: {}", id.toString());
        return roleRepository.findById(id).map(RoleDto::new).orElseThrow(() -> new ObjectNotFoundException("Role", id));
    }

    @Override
    public RoleDto getByRoleName(ERole roleName) {
        logger.info("Finding role with name: {}", roleName);
        return roleRepository.findByRoleName(roleName).map(RoleDto::new).orElseThrow(() -> new ObjectNotFoundException("Role", roleName));
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting role with id: {}", id.toString());
        roleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Role", id));
        roleRepository.deleteById(id);
    }
}
