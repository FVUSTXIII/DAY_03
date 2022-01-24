package com.faust.bank.dao;

import com.faust.bank.model.Account;

import java.util.HashMap;

public final class AccountDao {
    private static AccountDao instance;
    private HashMap<Integer, Account> accounts;
    private AccountDao() {
        this.accounts = new HashMap<Integer, Account>();
    }
    public static AccountDao getInstance() {
        if (instance == null) {
            instance = new AccountDao();
        }
        return instance;
    }

    public void addAccount(Account account) {
        if (!accounts.containsKey(account.getAccount_number())) {
            accounts.put(account.getAccount_number(), account);
            account.getCustomer().addAccount(account);
        } else {
            System.out.println("Account already exists!");
        }
    }

    public Account fetchAccount(Integer account_num) {
        Account aux = null;
        if (accounts.containsKey(account_num)) {
            aux = accounts.get(account_num);
        } else {
            System.out.println("Account not found!");
        }
        return aux;
    }

    public void updateAccount(Account updated_acc) {
        accounts.replace(updated_acc.getAccount_number(), fetchAccount(updated_acc.getAccount_number()), updated_acc);
        System.out.println("The account: " + updated_acc.getAccount_number() + " has been updated successfully.");
    }

    public void deleteAccount(Account accnt_to_delete) {
        accounts.remove(accnt_to_delete.getAccount_number());
        System.out.println("The account: " + accnt_to_delete.getAccount_number() + " has been deleted successfully.");
    }

}
