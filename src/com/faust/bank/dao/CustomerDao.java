package com.faust.bank.dao;


import com.faust.bank.model.Account;
import com.faust.bank.model.Client;

import java.util.HashMap;

public class CustomerDao {
    private static CustomerDao instance;
    private HashMap<Integer, Client> customers;

    private CustomerDao() {
        this.customers = new HashMap<Integer, Client>();
    }

    public static CustomerDao getInstance() {
        if (instance == null) {
            instance = new CustomerDao();
        }
        return instance;
    }

    public Client fetchCustomer(Integer customer_id) {
        Client aux = null;
        if (customers.containsKey(customer_id)) {
            aux = customers.get(customer_id);
        }
        return aux;
    }

    public void addCustomer(Client new_customer) {
        if (!customers.containsKey(new_customer.getCustomer_id())) {
            customers.put(new_customer.getCustomer_id(), new_customer);
        } else {
            System.out.println("El cliente ya existe");
        }
    }

    public void deleteCustomer(Client customer_to_delete) {
        customers.remove(customer_to_delete.getCustomer_id());
        System.out.println("The client: " + customer_to_delete.getName() + " has been deleted successfully.");

    }

    public void updateCustomer(Client updated_customer) {
        customers.replace(updated_customer.getCustomer_id(), fetchCustomer(updated_customer.getCustomer_id()), updated_customer);
        System.out.println("The customer: " + updated_customer.getName() + " has been deleted successfully.");
    }

}
