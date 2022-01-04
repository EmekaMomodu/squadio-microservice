package com.emekamomodu.squadio.repository;

import com.emekamomodu.squadio.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/4/22 6:26 AM
 */
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
