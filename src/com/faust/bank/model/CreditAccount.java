package com.faust.bank.model;

public class CreditAccount extends Account{
    private double line_of_credit;


    public CreditAccount(Integer account_number, double balance, Client customer,double line_of_credit) {
        super(account_number,  balance,  customer);
        this.line_of_credit = line_of_credit;
    }

    public double getLine_of_credit() {
        return line_of_credit;
    }

    public void setLine_of_credit(double line_of_credit) {
        this.line_of_credit = line_of_credit;
    }

}
