package com.emekamomodu.squadio.service.implementation;

import com.emekamomodu.squadio.entity.AccountType;
import com.emekamomodu.squadio.repository.AccountTypeRepository;
import com.emekamomodu.squadio.service.IAccountTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/4/22 5:51 AM
 */
@Service
public class AccountTypeService implements IAccountTypeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public AccountType create(AccountType accountType) {
        AccountType createdAccountType = new AccountType();
        try {
            logger.info("Creating new AccountType: {}", accountType);
            createdAccountType = accountTypeRepository.save(accountType);
        } catch (Exception exception) {
            logger.error("Error in creating AccountType: {}", exception.getMessage());
        }
        logger.info("AccountType created successfully: {}", createdAccountType);
        return createdAccountType;
    }

}
