package com.emekamomodu.squadio.repository;

import com.emekamomodu.squadio.entity.Role;
import com.emekamomodu.squadio.utility.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 10:29 PM
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(ERole roleName);
}
