package com.emekamomodu.squadio.repository;

import com.emekamomodu.squadio.entity.Account;
import com.emekamomodu.squadio.entity.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/4/22 6:44 AM
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("FROM Account account where account.user.userId = :userId")
    List<Account> findAllByUser(@Param("userId") Long userId);

}
