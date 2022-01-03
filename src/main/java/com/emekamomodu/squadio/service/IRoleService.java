package com.emekamomodu.squadio.service;

import com.emekamomodu.squadio.entity.Role;
import com.emekamomodu.squadio.model.ERole;

import java.util.List;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 10:42 PM
 */
public interface IRoleService {

    /**
     * Create and persist new Role object
     * @param role role object creation request object. Contains name
     * @return Role role object stored in data store
     */
    Role create(Role role);

    /**
     * Update role object name
     * @param role role object update request object. Contains name
     * @param id role object identifier
     * @return Role role object stored in data store
     */
    Role update(Role role, Long id);

    /**
     * Fetch all existing role objects
     * @return List<Role> list of role objects
     */
    List<Role> getAll();

    /**
     * Fetch role object by identifier
     * @param id role object identifier
     * @return Role role object stored in data store
     */
    Role getByID(Long id);

    /**
     * Fetch role object by name
     * @param roleName role object name
     * @return Role role object stored in data store
     */
    Role getByRoleName(ERole roleName);

    /**
     * Delete role object from data store
     * @param id role object identifier
     */
    void delete(Long id);
}
