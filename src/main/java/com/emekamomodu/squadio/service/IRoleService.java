package com.emekamomodu.squadio.service;

import com.emekamomodu.squadio.dto.RoleDto;
import com.emekamomodu.squadio.utility.enums.ERole;

import java.util.List;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 10:42 PM
 */
public interface IRoleService {

    /**
     * Create and persist new Role object
     * @param roleDto role object creation request object. Contains name
     * @return RoleDto roleDto object stored in data store
     */
    RoleDto create(RoleDto roleDto);

    /**
     * Update role object name
     * @param roleDto role object update request object. Contains name
     * @param id role object identifier
     * @return RoleDto roleDto object stored in data store
     */
    RoleDto update(RoleDto roleDto, Long id);

    /**
     * Fetch all existing role objects
     * @return List<RoleDto> list of role objects
     */
    List<RoleDto> getAll();

    /**
     * Fetch role object by identifier
     * @param id role object identifier
     * @return RoleDto roleDto object stored in data store
     */
    RoleDto getByID(Long id);

    /**
     * Fetch role object by name
     * @param roleName role object name
     * @return RoleDto roleDto object stored in data store
     */
    RoleDto getByRoleName(ERole roleName);

    /**
     * Delete role object from data store
     * @param id role object identifier
     */
    void delete(Long id);
}
