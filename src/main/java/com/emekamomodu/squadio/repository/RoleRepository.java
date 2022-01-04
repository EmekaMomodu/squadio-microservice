package com.emekamomodu.squadio.repository;

import com.emekamomodu.squadio.entity.Role;
import com.emekamomodu.squadio.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 10:29 PM
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(ERole roleName);

}
