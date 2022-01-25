package com.faust.bank.Factory;

import com.faust.bank.model.Client;

public class ClientFactory {
    public Client createClient(String name, Integer customer_id, String mobile_number, String email_id) {
        Client c = new Client(name, customer_id, mobile_number, email_id);
        return c;
    }
}
