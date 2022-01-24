package com.faust.bank.service;

import com.faust.bank.dao.TransactionDao;
import com.faust.bank.model.*;

public class TransactionService {
    public TransactionService() {

    }
    public void excecTransaction(Integer id,Account sender, Account receiver, double amount, String transaction_type, String date) {

        if (sender instanceof CreditAccount) {
            CreditTransaction aux = new CreditTransaction(id, receiver.getAccount_number(), sender.getAccount_number(), amount, date);
            switch (transaction_type) {
                case "PWC": //Pay with credit
                    if (aux.transaction_behavior instanceof CreditTransactionBehavior) {
                        aux.transaction_behavior.transferirACuenta(aux.getReceiver_account(), aux.getAmount());
                        TransactionDao.getInstance().addTransaction(aux);
                        aux.getSender_account().getCustomer().addTransaction(aux);
                        aux.getReceiver_account().getCustomer().addTransaction(aux);
                    }
                    break;
                case "PCB": //Pay balance
                    if (aux.transaction_behavior instanceof CreditTransactionBehavior) {
                        ((CreditTransactionBehavior) aux.transaction_behavior).payUpDebt(amount);
                        TransactionDao.getInstance().addTransaction(aux);
                        aux.getSender_account().getCustomer().addTransaction(aux);
                        aux.getReceiver_account().getCustomer().addTransaction(aux);
                    }
                    break;
                default:
                    break;
            }
        } else if (sender instanceof DebitAccount) {
            DebitTransaction aux = new DebitTransaction(id, receiver.getAccount_number(), sender.getAccount_number(),amount, date);
            switch (transaction_type) {
                case "WFD":
                    if (aux.transaction_behavior instanceof DebitTransactionBehavior) {
                        ((DebitTransactionBehavior) aux.transaction_behavior).withdrawAmnt(amount);
                        TransactionDao.getInstance().addTransaction(aux);
                        aux.getReceiver_account().getCustomer().addTransaction(aux);
                        aux.getSender_account().getCustomer().addTransaction(aux);
                    }
                    break;
                case "DTD":
                    if (aux.transaction_behavior instanceof DebitTransactionBehavior) {
                        ((DebitTransactionBehavior) aux.transaction_behavior).depositAmnt(amount);
                        TransactionDao.getInstance().addTransaction(aux);
                        aux.getReceiver_account().getCustomer().addTransaction(aux);
                        aux.getSender_account().getCustomer().addTransaction(aux);
                    }
                    break;
                case "PTD":
                    if (aux.transaction_behavior instanceof DebitTransactionBehavior) {
                       aux.transaction_behavior.transferirACuenta(aux.getReceiver_account(), amount);
                        TransactionDao.getInstance().addTransaction(aux);
                        aux.getReceiver_account().getCustomer().addTransaction(aux);
                        aux.getSender_account().getCustomer().addTransaction(aux);
                    }
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("INVALID TRANSACTION");
        }
    }
}
