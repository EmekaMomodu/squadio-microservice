package com.emekamomodu.squadio.service.implementation;

import com.emekamomodu.squadio.entity.Currency;
import com.emekamomodu.squadio.repository.CurrencyRepository;
import com.emekamomodu.squadio.service.ICurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/4/22 6:30 AM
 */
@Service
public class CurrencyService implements ICurrencyService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Currency create(Currency currency) {
        Currency createdCurrency = new Currency();
        try {
            logger.info("Creating new Currency: {}", currency);
            createdCurrency = currencyRepository.save(currency);
        } catch (Exception exception) {
            logger.error("Error in creating Currency: {}", exception.getMessage());
        }
        logger.info("Currency created successfully: {}", createdCurrency);
        return createdCurrency;
    }
}
