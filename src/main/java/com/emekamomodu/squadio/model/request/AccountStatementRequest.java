package com.emekamomodu.squadio.model.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/4/22 9:56 AM
 */
public class AccountStatementRequest {

    private Long accountId;

    private Date fromDate;

    private Date toDate;

    private BigDecimal fromAmount;

    private BigDecimal toAmount;

    public AccountStatementRequest() {
    }

    public AccountStatementRequest(Long accountId, Date fromDate, Date toDate, BigDecimal fromAmount, BigDecimal toAmount) {
        this.accountId = accountId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromAmount = fromAmount;
        this.toAmount = toAmount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public BigDecimal getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(BigDecimal fromAmount) {
        this.fromAmount = fromAmount;
    }

    public BigDecimal getToAmount() {
        return toAmount;
    }

    public void setToAmount(BigDecimal toAmount) {
        this.toAmount = toAmount;
    }

    @Override
    public String toString() {
        return "AccountStatementRequest{" +
                "accountId='" + accountId + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", fromAmount='" + fromAmount + '\'' +
                ", toAmount='" + toAmount + '\'' +
                '}';
    }
}
