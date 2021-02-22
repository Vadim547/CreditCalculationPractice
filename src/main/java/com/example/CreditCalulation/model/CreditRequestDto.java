package com.example.CreditCalulation.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditRequestDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private String passport;
    private double salary;
    private double loanAmount;
    //Срок в месяцах
    private int term;
    private String loanType;
    public CreditRequestDto() {
    }

    public CreditRequestDto(String firstName, String lastName, String middleName, String passport, double salary, int term, double loanAmount, String loanType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.passport = passport;
        this.salary = salary;
        this.term = term;
        this.loanAmount = loanAmount;
        this.loanType = loanType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
}
