package com.faust.bank.model;

public class CreditTransaction extends Transaction{
    public CreditTransaction(Integer id, Integer receiver_account, Integer sender_account, double amount, String date) {
        super(id, receiver_account, sender_account, amount, date);
        if (this.transaction_behavior instanceof CreditTransactionBehavior) {
            this.transaction_behavior = new CreditTransactionBehavior((CreditAccount) getSender_account());
        }


    }
}
