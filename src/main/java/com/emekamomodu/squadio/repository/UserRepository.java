package com.emekamomodu.squadio.repository;

import com.emekamomodu.squadio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 9:30 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
