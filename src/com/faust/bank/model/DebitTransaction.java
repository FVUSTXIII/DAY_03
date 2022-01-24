package com.faust.bank.model;

public class DebitTransaction extends Transaction{
    public DebitTransaction(Integer id, Integer receiver_account, Integer sender_account, double amount, String date) {
        super(id, receiver_account, sender_account, amount, date);
        if (transaction_behavior instanceof DebitTransactionBehavior) {
            transaction_behavior = new DebitTransactionBehavior((DebitAccount) getSender_account(sender_account));
        }
    }
}
