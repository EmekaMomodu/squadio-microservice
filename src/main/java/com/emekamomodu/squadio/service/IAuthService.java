package com.emekamomodu.squadio.service;

import com.emekamomodu.squadio.exception.custom.InvalidRequestObjectException;
import com.emekamomodu.squadio.exception.custom.ObjectAlreadyExistsException;
import com.emekamomodu.squadio.model.request.AuthRequest;
import com.emekamomodu.squadio.model.response.Response;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/31/21 7:30 PM
 */
public interface IAuthService {

    /**
     * log user in by authenticating user credentials
     *
     * @param authRequest request object with user credential
     * @return Response response object
     */
    Response login(AuthRequest authRequest) throws InvalidRequestObjectException, AuthenticationException, ObjectAlreadyExistsException;

    /**
     * log user out
     * @param request servlet request
     * @return Response response object
     */
    Response logout(HttpServletRequest request);

}
