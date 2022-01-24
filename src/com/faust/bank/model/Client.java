package com.faust.bank.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Client {
    private String name;
    private Integer customer_id;
    private String mobile_number;
    private String email_id;
    private List<Account> accounts_owned;
    private List<Transaction> transactions_made;

    public Client(String name, Integer customer_id, String mobile_number, String email_id) {
        this.name = name;
        this.customer_id = customer_id;
        this.mobile_number = mobile_number;
        this.email_id = email_id;
        accounts_owned = new LinkedList<>();
        transactions_made = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public List<Account> getAccounts_owned() {
        return accounts_owned;
    }

    public void addAccount(Account account) {
        accounts_owned.add(account);
    }
    public void addTransaction(Transaction transaction) {
        transactions_made.add(transaction);
    }
    public void setAccounts_owned(List<Account> accounts_owned) {
        this.accounts_owned = accounts_owned;
    }

    public List<Transaction> getTransactions_made() {
        return transactions_made;
    }

    public void setTransactions_made(List<Transaction> transactions_made) {
        this.transactions_made = transactions_made;
    }
}
