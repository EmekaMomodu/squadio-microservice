package com.emekamomodu.squadio.service;

import com.emekamomodu.squadio.entity.AccountStatement;
import com.emekamomodu.squadio.exception.custom.ObjectNotFoundException;
import com.emekamomodu.squadio.model.request.AccountStatementRequest;
import com.emekamomodu.squadio.model.response.Response;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 9:26 PM
 */
public interface IAccountStatementService {

    /**
     * Create and persist new Account Statement object
     *
     * @param accountStatement request object.
     * @return AccountStatement object stored in data store
     */
    AccountStatement create(AccountStatement accountStatement);

    /**
     * Get all account's statement
     *
     * @return Response response object
     */
    Response getAllAccountStatements(AccountStatementRequest accountStatementRequest) throws ObjectNotFoundException;
}
