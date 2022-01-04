package com.emekamomodu.squadio.service;

import com.emekamomodu.squadio.entity.User;
import com.emekamomodu.squadio.exception.custom.InvalidRequestObjectException;
import com.emekamomodu.squadio.exception.custom.ObjectAlreadyExistsException;
import com.emekamomodu.squadio.exception.custom.ObjectNotFoundException;
import com.emekamomodu.squadio.model.request.CreateUserRequest;
import com.emekamomodu.squadio.model.response.Response;
import org.springframework.security.access.AccessDeniedException;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 9:24 PM
 */
public interface IUserService {

    /**
     * Create and persist new User object
     * @param createUserRequest User object creation request object. Contains createUserRequest details
     * @return User object
     */
    User create(CreateUserRequest createUserRequest) throws ObjectAlreadyExistsException, ObjectNotFoundException, InvalidRequestObjectException;

    /**
     * Get all users
     * @return Response response object
     */
    Response getAllUsers() throws ObjectNotFoundException;

    /**
     * Get specified user information
     * @return Response response object
     */
    Response getUserInformation(String username) throws ObjectNotFoundException, AccessDeniedException;
}
