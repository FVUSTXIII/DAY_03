package com.faust.bank.model;

public class DebitAccount extends Account{

    public DebitAccount(Integer account_number, double balance, Client customer) {
        super(account_number, balance, customer);
    }
}
