package com.faust.bank.service;

import com.faust.bank.Factory.AccountFactory;
import com.faust.bank.Factory.ClientFactory;
import com.faust.bank.dao.AccountDao;
import com.faust.bank.dao.CustomerDao;
import com.faust.bank.model.Client;

public class CustomerService {
    private Integer id;
    private Client c;
    public CustomerService(Integer id) {
        this.id = id;
    }

    public boolean login() {
        this.c = CustomerDao.getInstance().fetchCustomer(this.id);
        return (c != null);
    }

    public void signUp(String name, Integer customer_id, String mobile_number, String email_id) {
        CustomerDao.getInstance().addCustomer(new ClientFactory().createClient(name, customer_id, mobile_number, email_id));
    }

    public void CreateCustomerAccount(String type, Integer acc_n, Double init_balance, Double loc, Double i_r) {
        AccountDao.getInstance().addAccount(new AccountFactory().createAccount(type, acc_n, init_balance, loc, i_r, this.c));
    }

    public Client getC() {
        return c;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setC(Client c) {
        this.c = c;
    }
}

