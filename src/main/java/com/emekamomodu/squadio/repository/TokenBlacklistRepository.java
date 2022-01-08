package com.emekamomodu.squadio.repository;

import com.emekamomodu.squadio.entity.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/3/22 11:21 AM
 */
public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, Long> {

    @Query("SELECT tokenBlacklist.token FROM TokenBlacklist tokenBlacklist where upper(tokenBlacklist.user.username) = upper(:username)")
    Set<String> getBlacklistedTokensForUser(@Param("username") String username);

}
