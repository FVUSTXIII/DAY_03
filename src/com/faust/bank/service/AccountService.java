package com.faust.bank.service;

import com.faust.bank.dao.AccountDao;
import com.faust.bank.model.Account;

public class AccountService {
    private Account account;
    public AccountService(Integer id) {
        this.account = AccountDao.getInstance().fetchAccount(id);
    }
    public void checkBalance() {
        System.out.println("The current balance on account " + this.account.getAccount_number() + " is: " +  this.account.getBalance());
    }

}
