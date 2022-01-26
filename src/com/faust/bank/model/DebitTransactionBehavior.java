package com.faust.bank.model;

import com.faust.bank.service.DebitTransactionable;

import java.util.Date;

public class DebitTransactionBehavior implements DebitTransactionable {
    DebitAccount host;

    public DebitTransactionBehavior(DebitAccount host) {
        this.host = host;
    }

    @Override
    public double withdrawAmnt(double amount_to_withdraw) {
        double amount = 0.00;
        if (host.getBalance() > amount_to_withdraw) {
            amount = amount_to_withdraw;
            ActualizarSaldo(-1 * amount_to_withdraw);
        } else {
            System.out.println("Insufficient funds!");
        }
        return amount;
    }

    @Override
    public double depositAmnt(double amount_to_deposit) {
        ActualizarSaldo(amount_to_deposit);
        return host.getBalance();
    }

    @Override
    public void transferirACuenta(Account receiver_account, double amount) {
        double amnt = withdrawAmnt(amount);
        if (amnt > 0.00) {
            if (receiver_account instanceof CreditAccount) {
                receiver_account.setBalance(receiver_account.getBalance() - amount);
            } else if (receiver_account instanceof DebitAccount) {
                receiver_account.setBalance(receiver_account.getBalance() + amount);
            }
        }
        ActualizarSaldo(amount);
    }

    @Override
    public boolean ActualizarSaldo(double amount) {
        boolean flag = false;
        double new_balance = host.getBalance() + amount;
        if (new_balance > 0) {
            host.setBalance(new_balance);
            flag = true;
        }
        return flag;
    }



}
