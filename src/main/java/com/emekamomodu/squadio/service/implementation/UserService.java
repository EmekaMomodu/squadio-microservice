package com.emekamomodu.squadio.service.implementation;

import com.emekamomodu.squadio.entity.Role;
import com.emekamomodu.squadio.entity.User;
import com.emekamomodu.squadio.exception.custom.InvalidRequestObjectException;
import com.emekamomodu.squadio.exception.custom.ObjectAlreadyExistsException;
import com.emekamomodu.squadio.exception.custom.ObjectNotFoundException;
import com.emekamomodu.squadio.model.UserModel;
import com.emekamomodu.squadio.model.request.CreateUserRequest;
import com.emekamomodu.squadio.model.response.Response;
import com.emekamomodu.squadio.repository.RoleRepository;
import com.emekamomodu.squadio.repository.UserRepository;
import com.emekamomodu.squadio.service.IUserService;
import com.emekamomodu.squadio.model.ERole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;

import static com.emekamomodu.squadio.utility.Utility.capitalize;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/31/21 9:29 AM
 */
@Service
public class UserService implements IUserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Response create(CreateUserRequest createUserRequest) throws ObjectAlreadyExistsException, ObjectNotFoundException, InvalidRequestObjectException {

        logger.info("Creating new User: {}", createUserRequest);

        // Validate request object
        validateCreateUserRequest(createUserRequest);

        // Remove all whitespaces from username and capitalise
        createUserRequest.setUsername(capitalize(createUserRequest.getUsername().replaceAll("\\s","")));

        // Check if Username is already taken
        if (userRepository.existsByUsernameIgnoreCase(createUserRequest.getUsername())) {
            throw new ObjectAlreadyExistsException("User with username '" + createUserRequest.getUsername() + "' already exists");
        }

        User user = new User(createUserRequest.getUsername(), passwordEncoder.encode(createUserRequest.getPassword()));
        Set<String> setStringOfRoles = createUserRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (setStringOfRoles == null) {
            Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
                    .orElseThrow(() -> new ObjectNotFoundException("Role with name '" + ERole.ROLE_USER + "' not found"));
            roles.add(userRole);
        } else {
            setStringOfRoles.forEach(role -> {
                if(role == null || role.replaceAll("\\s", "").equals("")){
                    throw new InvalidRequestObjectException("User role specified is invalid");
                }
                role = role.replaceAll("\\s", "");
                if ("ADMIN".equalsIgnoreCase(role)) {
                    Role adminRole = roleRepository.findByRoleName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new ObjectNotFoundException("Role with name '" + ERole.ROLE_ADMIN + "' not found"));
                    roles.add(adminRole);
                } else if ("USER".equalsIgnoreCase(role)) {
                    Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
                            .orElseThrow(() -> new ObjectNotFoundException("Role with name '" + ERole.ROLE_USER + "' not found"));
                    roles.add(userRole);
                } else {
                    throw new InvalidRequestObjectException("User role specified is invalid");
                }
            });
        }

        user.setUserRoles(roles);
        User createdUser = userRepository.save(user);

        UserModel userModel = new UserModel(createdUser.getUserId(), createdUser.getUsername());

        logger.info("User Created Successfully: {}", userModel);

        return new Response(true, "User Created Successfully", userModel);

    }

    private void validateCreateUserRequest(CreateUserRequest createUserRequest) throws InvalidRequestObjectException {

        if (createUserRequest == null) {
            throw new InvalidRequestObjectException("Create User Request Object is Null");
        }

        if (createUserRequest.getUsername() == null
                || createUserRequest.getPassword() == null
                || createUserRequest.getUsername().replaceAll("\\s","").equals("")
                || createUserRequest.getPassword().replaceAll("\\s","").equals("") ) {
            throw new InvalidRequestObjectException("Username and/or Password is Null or Empty");
        }
    }
}
