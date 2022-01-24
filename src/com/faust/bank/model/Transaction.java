package com.faust.bank.model;

import com.faust.bank.dao.AccountDao;
import com.faust.bank.service.Transactionable;

public abstract class Transaction {
    private Integer id;
    private Account sender_account;
    private Account receiver_account;
    private double amount;
    private String transaction_type;
    private String date;
    public Transactionable transaction_behavior;

    public Transaction(Integer id, Integer receiver_account, Integer sender_account, double amount, String date) {
        this.id = id;
        this.receiver_account = getReceiver_account(receiver_account);
        this.sender_account = getSender_account(sender_account);
        this.amount = amount;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Account getSender_account() {
        return sender_account;
    }
    public Account getSender_account(Integer sender_account) {
        return AccountDao.getInstance().fetchAccount(sender_account);
    }

    public void setSender_account(Integer sender_account) {
        this.sender_account = AccountDao.getInstance().fetchAccount(sender_account);
    }
    public Account getReceiver_account() {
        return receiver_account;
    }
    public Account getReceiver_account(Integer receiver_account) {
        return AccountDao.getInstance().fetchAccount(receiver_account);
    }

    public void setReceiver_account(Integer receiver_account) {
        this.receiver_account = AccountDao.getInstance().fetchAccount(receiver_account);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }




}
