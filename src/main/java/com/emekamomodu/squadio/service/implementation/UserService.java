package com.emekamomodu.squadio.service.implementation;

import com.emekamomodu.squadio.entity.Role;
import com.emekamomodu.squadio.entity.User;
import com.emekamomodu.squadio.exception.custom.InvalidRequestObjectException;
import com.emekamomodu.squadio.exception.custom.ObjectAlreadyExistsException;
import com.emekamomodu.squadio.exception.custom.ObjectNotFoundException;
import com.emekamomodu.squadio.model.ERole;
import com.emekamomodu.squadio.model.UserModel;
import com.emekamomodu.squadio.model.request.CreateUserRequest;
import com.emekamomodu.squadio.model.response.Response;
import com.emekamomodu.squadio.repository.RoleRepository;
import com.emekamomodu.squadio.repository.UserRepository;
import com.emekamomodu.squadio.security.service.UserDetailsImpl;
import com.emekamomodu.squadio.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User create(CreateUserRequest createUserRequest) throws ObjectAlreadyExistsException, ObjectNotFoundException, InvalidRequestObjectException {

        logger.info("Creating new User: {}", createUserRequest);

        // Validate request object
        validateCreateUserRequest(createUserRequest);

        // Remove all whitespaces from username and capitalise
        createUserRequest.setUsername(capitalize(createUserRequest.getUsername().replaceAll("\\s", "")));

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
                if (role == null || role.replaceAll("\\s", "").equals("")) {
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

        return createdUser;

    }

    @Override
    public Response getAllUsers() throws ObjectNotFoundException {

        logger.info("Getting all users");

        List<UserModel> userModels = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            UserModel userModel = new UserModel(user.getUserId(), user.getUsername());
            userModels.add(userModel);
        }

        if (userModels.size() > 0){
            logger.info("Users fetched successfully : {}", userModels);
            return new Response(true, "All Users fetched Successfully", userModels);
        }

        logger.info("No user was found : {}", userModels);
        throw new ObjectNotFoundException("No user was found");
    }

    @Override
    public Response getUserInformation(String username) throws ObjectNotFoundException, AccessDeniedException {

        logger.info("Getting user information for user with username: {}", username);

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        GrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");

        boolean isNotAdmin = !roles.contains(roleAdmin);
        String loggedInUsername = userDetails.getUsername();

        // revoke access if not admin and owner of username
        if(isNotAdmin && !loggedInUsername.equalsIgnoreCase(username)){
            throw new AccessDeniedException("Not allowed to access another user's information");
        }

        User user = userRepository.findByUsernameIgnoreCase(username).map(User::new).orElseThrow(() ->
                new ObjectNotFoundException("User with username '" + username + "' not found"));
        UserModel userInformation = new UserModel(user);
        logger.info("User information fetched Successfully : {}", userInformation);
        return new Response(true, "User information fetched Successfully", userInformation);

    }

    private void validateCreateUserRequest(CreateUserRequest createUserRequest) throws InvalidRequestObjectException {

        if (createUserRequest == null) {
            throw new InvalidRequestObjectException("Create User Request Object is Null");
        }

        if (createUserRequest.getUsername() == null
                || createUserRequest.getPassword() == null
                || createUserRequest.getUsername().replaceAll("\\s", "").equals("")
                || createUserRequest.getPassword().replaceAll("\\s", "").equals("")) {
            throw new InvalidRequestObjectException("Username and/or Password is Null or Empty");
        }
    }

}
