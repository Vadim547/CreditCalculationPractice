package com.example.CreditCalulation.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditResponseDto {
    private boolean approved;
    private String loanType;
    private ZonedDateTime timestamp;
    private List<Map<String, BigDecimal>> paymentStatement;

    public CreditResponseDto() {
    }

    public CreditResponseDto(boolean approved, String loanType, ZonedDateTime timestamp, List<Map<String, BigDecimal>> paymentStatement) {
        this.approved = approved;
        this.timestamp = timestamp;
        this.paymentStatement = paymentStatement;
        this.loanType = loanType;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<Map<String, BigDecimal>> getPaymentStatement() {
        return paymentStatement;
    }

    public void setPaymentStatement(List<Map<String, BigDecimal>> paymentStatement) {
        this.paymentStatement = paymentStatement;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
}
