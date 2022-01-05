package com.emekamomodu.squadio.service.implementation;

import com.emekamomodu.squadio.entity.TokenBlacklist;
import com.emekamomodu.squadio.entity.User;
import com.emekamomodu.squadio.exception.custom.InvalidRequestObjectException;
import com.emekamomodu.squadio.exception.custom.ObjectAlreadyExistsException;
import com.emekamomodu.squadio.model.AuthModel;
import com.emekamomodu.squadio.model.request.AuthRequest;
import com.emekamomodu.squadio.model.response.Response;
import com.emekamomodu.squadio.repository.TokenBlacklistRepository;
import com.emekamomodu.squadio.repository.UserRepository;
import com.emekamomodu.squadio.security.jwt.JwtUtils;
import com.emekamomodu.squadio.security.service.UserDetailsImpl;
import com.emekamomodu.squadio.service.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.emekamomodu.squadio.utility.Utility.capitalize;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/31/21 7:52 PM
 */
@SuppressWarnings("Duplicates")
@Service
public class AuthService implements IAuthService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenBlacklistRepository tokenBlacklistRepository;

    @Override
    public Response login(AuthRequest authRequest) throws InvalidRequestObjectException, AuthenticationException, ObjectAlreadyExistsException {

        logger.info("User login initiated");

        Response response = new Response();

        // Validate request object
        validateAuthRequest(authRequest);

        // Remove all whitespaces from username and capitalise
        authRequest.setUsername(capitalize(authRequest.getUsername().replaceAll("\\s", "")));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Check if user is already logged in and if true throw exception
        if (userDetails.getLoginFlag().equalsIgnoreCase("Y")) {
            throw new ObjectAlreadyExistsException("User already logged in, Kindly logout first before logging in");
        }

        String token = jwtUtils.generateJwtToken(authentication);

        if (token != null) {
            response.setSuccess(true);
            response.setMessage("User Login Successful");
            AuthModel authModel = new AuthModel(userDetails.getId(), token);
            response.setData(authModel);
            // update login flag
            userRepository.updateLoginFlagWithId(userDetails.getId(), "Y");
        }

        return response;
    }

    @Override
    public Response logout(HttpServletRequest request) {

        logger.info("logout user initiated");

        // Get user details
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        // update login flag
        userRepository.updateLoginFlagWithId(userDetails.getId(), "N");

        // invalidate token by adding to blacklist
        String headerAuth = request.getHeader("Authorization");
        String token = headerAuth.substring(7);
        User user = new User(userDetails.getId());
        TokenBlacklist tokenBlacklist = new TokenBlacklist(token, user);
        tokenBlacklistRepository.save(tokenBlacklist);

        return new Response(true, "User Logout Successful");
    }

    private void validateAuthRequest(AuthRequest authRequest) throws InvalidRequestObjectException {

        if (authRequest == null) {
            throw new InvalidRequestObjectException("Auth Request Object is Null");
        }

        if (authRequest.getUsername() == null
                || authRequest.getPassword() == null
                || authRequest.getUsername().replaceAll("\\s", "").equals("")
                || authRequest.getPassword().replaceAll("\\s", "").equals("")) {
            throw new InvalidRequestObjectException("Username and/or Password is Null or Empty");
        }
    }

}
