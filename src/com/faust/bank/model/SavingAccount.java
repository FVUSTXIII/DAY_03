package com.faust.bank.model;

public class SavingAccount extends DebitAccount{
    private double interestRate;
    public SavingAccount(Integer account_number, double balance, Client customer, double interes_rate) {
        super(account_number, balance, customer);
        this.interestRate = interes_rate;
    }


    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void setBalance(double balance) {
        super.setBalance(balance * getInterestRate());
    }
}
