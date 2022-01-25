package com.faust.bank.Factory;

import com.faust.bank.model.*;

public class TransactionFactory {
    private Account sender;
    private Account receiver;
    private Integer id;
    private Double amount;
    private String date;

    public TransactionFactory(Account sender, Account receiver, Integer id, Double amount, String date) {
        this.sender = sender;
        this.receiver = receiver;
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public Transaction createTransaction() {
        Transaction tra = null;
        if (this.sender instanceof CreditAccount) {
            tra = new CreditTransaction(this.id, this.sender.getAccount_number(), this.receiver.getAccount_number(), this.amount, this.date);
        } else if (this.sender instanceof DebitAccount) {
            tra = new DebitTransaction(this.id, this.receiver.getAccount_number(), this.sender.getAccount_number(), this.amount, this.date);
        }
        return tra;
    }
}
