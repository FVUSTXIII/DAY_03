package com.faust.bank.model;

public class MortageAccount extends CreditAccount {

    public MortageAccount(Integer account_number, double balance, Client customer,double line_of_credit) {
        super(account_number, balance, customer, line_of_credit);
    }
}
