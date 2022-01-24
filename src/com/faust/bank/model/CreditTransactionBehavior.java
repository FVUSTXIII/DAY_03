package com.faust.bank.model;

import com.faust.bank.service.CreditTransactionable;

public class CreditTransactionBehavior implements CreditTransactionable {

    private CreditAccount host;

    public CreditTransactionBehavior(CreditAccount host) {
        this.host = host;
    }

    @Override
    public void transferirACuenta(Account receiver_account, double amount) {
        double threshold = host.getLine_of_credit() - host.getBalance();
        if (amount <= threshold) {
            receiver_account.setBalance(receiver_account.getBalance() + amount);
            ActualizarSaldo(amount);
        } else {
            System.out.println("You have exceeded your line of credit");
        }
    }

    @Override
    public boolean ActualizarSaldo(double amount) {
        boolean flag = false;
        double aux = host.getBalance() + amount;
        if ((aux >= 0) && (aux <= host.getLine_of_credit())) {
            host.setBalance(aux);
            flag = true;
        }
        return flag;
    }

    @Override
    public void payUpDebt(double amount) {
        ActualizarSaldo((amount * -1.00));
    }

}
