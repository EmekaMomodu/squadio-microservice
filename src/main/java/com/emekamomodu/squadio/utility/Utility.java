package com.emekamomodu.squadio.utility;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/3/22 12:36 AM
 */
public class Utility {

    public static String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }

    public static String hashAccountNumber(String accountNumber) {
        return DigestUtils.sha256Hex(accountNumber);
    }

    public static String generateRandomAccount(String prefix) {
        Random rand = new Random();
        StringBuilder stringBuilder = new StringBuilder(prefix);
        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(10);
            stringBuilder.append(n);
        }
        return stringBuilder.toString();
    }

}
