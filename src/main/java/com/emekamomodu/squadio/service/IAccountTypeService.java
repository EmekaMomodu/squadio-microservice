package com.emekamomodu.squadio.service;

import com.emekamomodu.squadio.entity.AccountType;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/4/22 5:47 AM
 */
public interface IAccountTypeService {
    /**
     * Create and persist new AccountTypeRepository object
     *
     * @param accountType AccountTypeRepository object creation request object.
     * @return AccountTypeRepository object stored in data store
     */
    AccountType create(AccountType accountType);
}
