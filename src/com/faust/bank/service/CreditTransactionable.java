package com.faust.bank.service;

public interface CreditTransactionable extends Transactionable {
    void payUpDebt(double amount);
}
