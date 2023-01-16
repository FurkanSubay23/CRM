package com.hibernateprojectmvc.DAO;

import com.hibernateprojectmvc.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    public List<Customer> getCustomers(int theSortField);

    public void saveCustomer(Customer customer);

    Customer updateCustomer(int id);

    void deleteCustomer(int id);

    List<Customer> searchCustomer(String name);
}
