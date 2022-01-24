package com.faust.bank.model;

public abstract class Account {
    private Integer account_number;
    private double balance;
    private Client customer;

    public Account(Integer account_number, double balance, Client customer) {
        this.account_number = account_number;
        this.balance = balance;
        this.customer = customer;
    }


    public Integer getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Integer account_number) {
        this.account_number = account_number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client getCustomer() {
        return customer;
    }

    public void setCustomer(Client customer) {
        this.customer = customer;
    }


}
