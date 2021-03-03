package com.example.CreditCalulation.enums;

public enum LoanType {
    AVTOKREDIT(13), ONLINE(18), CONSUMER(14),
    EDUCATIONAL(10);
    double rate;
    LoanType(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
