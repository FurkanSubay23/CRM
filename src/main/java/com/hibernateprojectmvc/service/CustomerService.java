package com.hibernateprojectmvc.service;

import com.hibernateprojectmvc.entity.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> getCustomers(int theSortField);

    public void saveCustomer(Customer customer);

    Customer updateCustomer(int id);

    void deleteCustomer(int id);

    List<Customer> searchCustomer(String name);
}
