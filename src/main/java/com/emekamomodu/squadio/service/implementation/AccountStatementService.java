package com.emekamomodu.squadio.service.implementation;

import com.emekamomodu.squadio.entity.Account;
import com.emekamomodu.squadio.entity.AccountStatement;
import com.emekamomodu.squadio.exception.custom.InvalidRequestObjectException;
import com.emekamomodu.squadio.exception.custom.ObjectNotFoundException;
import com.emekamomodu.squadio.model.AccountStatementModel;
import com.emekamomodu.squadio.model.request.AccountStatementRequest;
import com.emekamomodu.squadio.model.response.Response;
import com.emekamomodu.squadio.repository.AccountRepository;
import com.emekamomodu.squadio.repository.AccountStatementRepository;
import com.emekamomodu.squadio.security.service.UserDetailsImpl;
import com.emekamomodu.squadio.service.IAccountStatementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.*;

import static com.emekamomodu.squadio.utility.Utility.hashAccountNumber;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/4/22 9:48 AM
 */
@SuppressWarnings("Duplicates")
@Service
public class AccountStatementService implements IAccountStatementService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountStatementRepository accountStatementRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountStatement create(AccountStatement accountStatement) {
        AccountStatement createdAccountStatement = new AccountStatement();
        try {
            logger.info("Creating new Account Statement: {}", accountStatement);
            createdAccountStatement = accountStatementRepository.save(accountStatement);
        } catch (Exception exception) {
            logger.error("Error in creating Account Statement: {}", exception.getMessage());
        }
        logger.info("Account Statement created successfully: {}", createdAccountStatement);
        return createdAccountStatement;
    }

    @Override
    public Response getAllAccountStatements(AccountStatementRequest accountStatementRequest) throws ObjectNotFoundException, InvalidRequestObjectException {

        logger.info("Fetching Account Statement for request payload: {}", accountStatementRequest);

        // validate request
        validateAccountStatementRequest(accountStatementRequest);

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        GrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");

        boolean isNotAdmin = !roles.contains(roleAdmin);

        if (isNotAdmin) {
            Long loggedInUserId = userDetails.getId();
            List<Account> userAccounts = accountRepository.findAllByUser(loggedInUserId);
            boolean isNotOwnerOfAccount = true;
            for (Account userAccount : userAccounts) {
                if (Objects.equals(userAccount.getAccountId(), accountStatementRequest.getAccountId())) {
                    isNotOwnerOfAccount = false;
                    break;
                }
            }
            if (isNotOwnerOfAccount) {
                throw new AccessDeniedException("Not allowed to access another user's account");
            }
        }

        List<AccountStatement> accountStatements = accountStatementRepository
                .findAll((Specification<AccountStatement>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    Account account = new Account(accountStatementRequest.getAccountId());
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("account"), account)));
                    if (accountStatementRequest.getFromAmount() != null) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("amount"), accountStatementRequest.getFromAmount())));
                    }
                    if (accountStatementRequest.getToAmount() != null) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("amount"), accountStatementRequest.getToAmount())));
                    }

                    if (accountStatementRequest.getFromDate() != null) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("date"), accountStatementRequest.getFromDate())));
                    }
                    if (accountStatementRequest.getToDate() != null) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("date"), accountStatementRequest.getToDate())));
                    }

                    Date threeMonthsBack = new Date((new Date()).getTime() - 2629800000L);

                    if (accountStatementRequest.getFromDate() == null && accountStatementRequest.getToDate() == null) {
                        predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("date"), threeMonthsBack)));
                    }


                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                });


        List<AccountStatementModel> accountStatementModels = new ArrayList<>();

        for (AccountStatement accountStatement : accountStatements) {
            AccountStatementModel accountStatementModel = new AccountStatementModel(
                    hashAccountNumber(accountStatement.getAccount().getAccountNumber()),
                    accountStatement.getDescription(),
                    accountStatement.getAmount(),
                    accountStatement.getDate());

            accountStatementModels.add(accountStatementModel);
        }

        if (accountStatementModels.size() > 0) {
            logger.info("Account Statements fetched successfully");
            return new Response(true, "Account Statements fetched Successfully", accountStatementModels);
        }

        logger.info("No Account statement found for specified search criteria");
        throw new ObjectNotFoundException("No Account statement found for specified search criteria");

    }

    private void validateAccountStatementRequest(AccountStatementRequest accountStatementRequest) throws InvalidRequestObjectException {

        if (accountStatementRequest == null) {
            throw new InvalidRequestObjectException("Account Statement Request Object is Null");
        }

        if (accountStatementRequest.getAccountId() == null) {
            throw new InvalidRequestObjectException("Account ID is not specified");
        }

        if (accountStatementRequest.getFromAmount() != null && accountStatementRequest.getToAmount() != null
                && accountStatementRequest.getFromAmount().compareTo(accountStatementRequest.getToAmount()) > 0) {
            throw new InvalidRequestObjectException("FromAmount should not be greater than ToAmount");
        }

        if (accountStatementRequest.getFromDate() != null && accountStatementRequest.getToDate() != null
                && accountStatementRequest.getFromDate().after(accountStatementRequest.getToDate())) {
            throw new InvalidRequestObjectException("FromDate should not come after ToDate");
        }
    }

}
