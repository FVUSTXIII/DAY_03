package com.faust.bank.dao;

import com.faust.bank.model.Account;
import com.faust.bank.model.CreditAccount;
import com.faust.bank.model.DebitAccount;
import com.faust.bank.model.Transaction;

import java.util.HashMap;

public class TransactionDao {
    private static TransactionDao instance;
    private HashMap<Integer, Transaction> transactions;

    private TransactionDao() {
        this.transactions = new HashMap<Integer, Transaction>();
    }

    public static TransactionDao getInstance() {
        if (instance == null) {
            instance = new TransactionDao();
        }
        return instance;
    }
    public void addTransaction(Transaction transaction) {
        if(!transactions.containsKey(transaction.getId())) {
            transactions.put(transaction.getId(), transaction);
            transaction.getSender_account().getCustomer().addTransaction(transaction);
            transaction.getReceiver_account().getCustomer().addTransaction(transaction);
        } else {
            System.out.println("This transaction has already been registered");
        }
    }
    public Transaction fetchTransaction(Integer transaction_id) {
        Transaction aux = null;
        if (transactions.containsKey(transaction_id)) {
            aux = transactions.get(transaction_id);
        } else {
            System.out.println("Transaction not found!");
        }
        return aux;
    }




}
