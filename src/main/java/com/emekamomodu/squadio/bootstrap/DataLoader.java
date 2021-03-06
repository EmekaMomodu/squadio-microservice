package com.emekamomodu.squadio.bootstrap;

import com.emekamomodu.squadio.entity.*;
import com.emekamomodu.squadio.model.EAccountType;
import com.emekamomodu.squadio.model.ERole;
import com.emekamomodu.squadio.model.request.CreateUserRequest;
import com.emekamomodu.squadio.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;

import static com.emekamomodu.squadio.utility.Utility.generateRandomAccount;

@SuppressWarnings("Duplicates")
@Component
public class DataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAccountTypeService accountTypeService;

    @Autowired
    private ICurrencyService currencyService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IAccountStatementService accountStatementService;

    @Override
    public void run(String... args) throws Exception {

        logger.info("Loading data");

        // Create Roles
        roleService.create(new Role(ERole.ROLE_ADMIN));
        roleService.create(new Role(ERole.ROLE_USER));

        // Create Users
        User userAdmin = userService.create(new CreateUserRequest("Admin", "admin", new HashSet<>(Collections.singletonList("ADMIN"))));
        User userMohamed = userService.create(new CreateUserRequest("Mohamed", "user", new HashSet<>(Collections.singletonList("USER"))));
        User userJohn = userService.create(new CreateUserRequest("John", "user", new HashSet<>(Collections.singletonList("USER"))));
        User userKumar = userService.create(new CreateUserRequest("Kumar", "user", new HashSet<>(Collections.singletonList("USER"))));

        // Create Account Types
        AccountType savingsAccountType = accountTypeService.create(new AccountType(EAccountType.SAVINGS));
        AccountType currentAccountType = accountTypeService.create(new AccountType(EAccountType.CURRENT));
        AccountType fixedDepositAccountType = accountTypeService.create(new AccountType(EAccountType.FIXED_DEPOSIT));

        // Create Currencies
        Currency usdCurrency = currencyService.create(new Currency("USD", "United State Dollars"));
        Currency aedCurrency = currencyService.create(new Currency("AED", "United Arab Emirates Dirham"));
        Currency egpCurrency = currencyService.create(new Currency("EGP", "Egyptian Pound"));
        Currency ngnCurrency = currencyService.create(new Currency("NGN", "Nigerian Naira"));

        // Create Accounts
        // accountMohamed
        Account accountMohamed1 = accountService.create(new Account(generateRandomAccount(""),
                generateRandomAccount("US"),
                new BigDecimal("538356.58"),
                userMohamed,
                savingsAccountType,
                usdCurrency));
        Account accountMohamed2 = accountService.create(new Account(generateRandomAccount(""),
                generateRandomAccount("AE"),
                new BigDecimal("8367836.37"),
                userMohamed,
                fixedDepositAccountType,
                aedCurrency));
        Account accountMohamed3 = accountService.create(new Account(generateRandomAccount(""),
                generateRandomAccount("EG"),
                new BigDecimal("122577.91"),
                userMohamed,
                currentAccountType,
                egpCurrency));

        // accountJohn
        Account accountJohn1 = accountService.create(new Account(generateRandomAccount(""),
                generateRandomAccount("US"),
                new BigDecimal("47363873.10"),
                userJohn,
                savingsAccountType,
                usdCurrency));
        Account accountJohn2 = accountService.create(new Account(generateRandomAccount(""),
                generateRandomAccount("AE"),
                new BigDecimal("283368.00"),
                userJohn,
                fixedDepositAccountType,
                aedCurrency));
        Account accountJohn3 = accountService.create(new Account(generateRandomAccount(""),
                generateRandomAccount("NG"),
                new BigDecimal("987333.91"),
                userJohn,
                currentAccountType,
                ngnCurrency));

        // accountKumar
        Account accountKumar1 = accountService.create(new Account(generateRandomAccount(""),
                generateRandomAccount("EG"),
                new BigDecimal("55377.10"),
                userKumar,
                savingsAccountType,
                egpCurrency));
        Account accountKumar2 = accountService.create(new Account(generateRandomAccount(""),
                generateRandomAccount("AE"),
                new BigDecimal("100.00"),
                userKumar,
                fixedDepositAccountType,
                aedCurrency));
        Account accountKumar3 = accountService.create(new Account(generateRandomAccount(""),
                generateRandomAccount("NG"),
                new BigDecimal("28672.07"),
                userKumar,
                currentAccountType,
                ngnCurrency));


        // Create Account Statements
        // accountStatementMohamed1
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        AccountStatement accountStatementMohamed1 = accountStatementService.create(new AccountStatement("purchase of item abc",
                new BigDecimal("200.00"),
                formatter.parse("1-jan-2022"),
                accountMohamed1));

        AccountStatement accountStatementMohamed2 = accountStatementService.create(new AccountStatement("payment of electricity bill",
                new BigDecimal("2500.00"),
                formatter.parse("25-dec-2021"),
                accountMohamed1));

        AccountStatement accountStatementMohamed3 = accountStatementService.create(new AccountStatement("school fees payment",
                new BigDecimal("763.37"),
                formatter.parse("17-nov-2021"),
                accountMohamed1));

        AccountStatement accountStatementMohamed4 = accountStatementService.create(new AccountStatement("hospital bill payment",
                new BigDecimal("58000.37"),
                formatter.parse("1-jun-2021"),
                accountMohamed1));


        AccountStatement accountStatementMohamed5 = accountStatementService.create(new AccountStatement("grocery shopping",
                new BigDecimal("1010.00"),
                formatter.parse("4-jan-2022"),
                accountMohamed2));

        AccountStatement accountStatementMohamed6 = accountStatementService.create(new AccountStatement("wedding anniversary party",
                new BigDecimal("45000.00"),
                formatter.parse("8-dec-2021"),
                accountMohamed2));

        AccountStatement accountStatementMohamed7 = accountStatementService.create(new AccountStatement("club on tuesday",
                new BigDecimal("999.37"),
                formatter.parse("17-feb-2021"),
                accountMohamed2));

        AccountStatement accountStatementMohamed8 = accountStatementService.create(new AccountStatement("cable tv bill",
                new BigDecimal("892.00"),
                formatter.parse("4-jun-2021"),
                accountMohamed3));

        AccountStatement accountStatementMohamed9 = accountStatementService.create(new AccountStatement("wedding anniversary party",
                new BigDecimal("45000.00"),
                formatter.parse("8-dec-2021"),
                accountMohamed3));

        AccountStatement accountStatementMohamed10 = accountStatementService.create(new AccountStatement("club on tuesday",
                new BigDecimal("999.37"),
                formatter.parse("17-nov-2021"),
                accountMohamed3));


        logger.info("Data loaded successfully");

    }

}
