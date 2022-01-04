package com.emekamomodu.squadio.service;

import com.emekamomodu.squadio.entity.Account;
import com.emekamomodu.squadio.exception.custom.ObjectNotFoundException;
import com.emekamomodu.squadio.model.response.Response;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 9:25 PM
 */
public interface IAccountService {

    /**
     * Create and persist new Account object
     *
     * @param account Account object creation request object.
     * @return Account object stored in data store
     */
    Account create(Account account);

    /**
     * Get all accounts for a user
     * @return Response response object
     */
    Response getAllUserAccounts(String userId) throws ObjectNotFoundException;

}
