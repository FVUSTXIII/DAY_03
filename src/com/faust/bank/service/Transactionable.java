package com.faust.bank.service;

import com.faust.bank.model.Account;

public interface Transactionable {
    void transferirACuenta(Account receiver_account,  double amount);
    boolean ActualizarSaldo(double amount);

}
