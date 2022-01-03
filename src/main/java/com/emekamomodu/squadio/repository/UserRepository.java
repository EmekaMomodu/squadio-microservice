package com.emekamomodu.squadio.repository;

import com.emekamomodu.squadio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 9:30 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameIgnoreCase(String username);

    Boolean existsByUsernameIgnoreCase(String username);

    @Transactional
    @Modifying
    @Query("update User user set user.loginFlag = :loginFlag where user.userId = :userId")
    void updateLoginFlag(@Param(value = "userId") Long userId, @Param(value = "loginFlag") String loginFlag);

}
