package com.emekamomodu.squadio.service.implementation;

import com.emekamomodu.squadio.entity.Account;
import com.emekamomodu.squadio.exception.custom.ObjectNotFoundException;
import com.emekamomodu.squadio.model.AccountModel;
import com.emekamomodu.squadio.model.response.Response;
import com.emekamomodu.squadio.repository.AccountRepository;
import com.emekamomodu.squadio.security.service.UserDetailsImpl;
import com.emekamomodu.squadio.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.emekamomodu.squadio.utility.Utility.hashAccountNumber;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/4/22 7:00 AM
 */
@SuppressWarnings("Duplicates")
@Service
public class AccountService implements IAccountService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account create(Account account) {
        Account createdAccount = new Account();
        try {
            logger.info("Creating new Account: {}", account);
            createdAccount = accountRepository.save(account);
        } catch (Exception exception) {
            logger.error("Error in creating Account: {}", exception.getMessage());
        }
        logger.info("Account created successfully: {}", createdAccount);
        return createdAccount;
    }

    @Override
    public Response getAllUserAccounts(String userId) throws ObjectNotFoundException {

        logger.info("Getting all accounts for user with id: {}", userId);

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        GrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");

        boolean isNotAdmin = !roles.contains(roleAdmin);
        Long loggedInUserId = userDetails.getId();
        Long id = Long.valueOf(userId);

        // revoke access if not admin and owner of id
        if (isNotAdmin && !Objects.equals(loggedInUserId, id)) {
            throw new AccessDeniedException("Not allowed to access another user's account");
        }

        List<AccountModel> accountModels = new ArrayList<>();

        for (Account account : accountRepository.findAllByUser(id)) {
            AccountModel accountModel = new AccountModel(account.getAccountId(),
                    account.getAccountType().getAccountTypeName().toString(),
                    hashAccountNumber(account.getAccountNumber()),
                    account.getIban(),
                    account.getBalance(),
                    account.getCurrency().getCurrencyCode());
            accountModels.add(accountModel);
        }

        if (accountModels.size() > 0) {
            logger.info("Accounts fetched successfully : {}", accountModels);
            return new Response(true, "All Accounts fetched Successfully", accountModels);
        }

        logger.info("No Account for specified user was found : {}", accountModels);
        throw new ObjectNotFoundException("No Account for specified user was found");

    }

}
