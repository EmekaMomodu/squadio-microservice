package com.emekamomodu.squadio.repository;

import com.emekamomodu.squadio.entity.AccountStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/4/22 9:46 AM
 */
public interface AccountStatementRepository extends JpaRepository<AccountStatement, Long>, JpaSpecificationExecutor<AccountStatement> {

}
