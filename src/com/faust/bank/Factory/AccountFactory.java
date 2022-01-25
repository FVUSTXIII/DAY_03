package com.faust.bank.Factory;

import com.faust.bank.model.Account;
import com.faust.bank.model.Client;
import com.faust.bank.model.MortageAccount;
import com.faust.bank.model.SavingAccount;

public class AccountFactory {
    public Account createAccount(String type, Integer acc_n, Double init_balance, Double loc, Double i_r, Client client) {
        Account acc = null;
        switch (type) {
            case "Mortage":
                acc = new MortageAccount(acc_n, init_balance, client,loc);
                break;
            case "Savings":
                acc = new SavingAccount(acc_n, init_balance, client, i_r);
                break;
        }
        return acc;
    }
}
