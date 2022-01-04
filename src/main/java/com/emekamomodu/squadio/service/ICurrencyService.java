package com.emekamomodu.squadio.service;

import com.emekamomodu.squadio.entity.Currency;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/4/22 6:28 AM
 */
public interface ICurrencyService {

    /**
     * Create and persist new Currency object
     *
     * @param currency Currency object creation request object.
     * @return Currency object stored in data store
     */
    Currency create(Currency currency);
}
