package com.example.solidprinciplesandroid.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 51778499 on 25,April,2019
 * Hcl Technologies,
 * India.
 */
public class AndroidExample {

    public interface CustomerRepository {
        List<Customer> getCustomersWithIds(List<Integer> ids);
    }

    public class CustomerRepositoryImpl implements CustomerRepository {
        WebServicesApi api;
        @Override
        public List<Customer> getCustomersWithIds(List<Integer> ids) {
            // Go to API, DB, etc and get the customers.
            ArrayList<Customer> customers = api.getWholeLottaCustomers(ids);
            return customers;
        }
    }
    private interface WebServicesApi{
        ArrayList<Customer> getWholeLottaCustomers(List<Integer> ids);
    }
    private interface Customer {}

    /*
    In the above code, getCustomersWithIds method return type is List<Customer> but we are returning ArrayList<Customer>.
    then How it works?
    The ArrayList<> implements List<> interface. So we can substitute super class (List<>) with sub-class(Array<>)
    Not only this, The ArrayList<> & List<> both implements Collection interface one level up in the root,
    So we can substitute ArrayList<> & List<> (& all the classes that implements Collection interface) with Collection.
    We can easily relate this principle with IS-A relationship.
     */
}
