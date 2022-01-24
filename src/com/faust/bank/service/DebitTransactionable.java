package com.faust.bank.service;

public interface DebitTransactionable extends Transactionable{
    double withdrawAmnt(double amount_to_deposit);
    double depositAmnt(double amount_to_deposit);
}
