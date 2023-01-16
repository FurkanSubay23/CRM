package com.hibernateprojectmvc.DAO;

import com.hibernateprojectmvc.Util.SortUtils;
import com.hibernateprojectmvc.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerDAOImp implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers(int theSortField) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // determine sort field
        String theFieldName = null;

        switch (theSortField) {
            case SortUtils.FIRST_NAME:
                theFieldName = "firstName";
                break;
            case SortUtils.LAST_NAME:
                theFieldName = "lastName";
                break;
            case SortUtils.EMAIL:
                theFieldName = "email";
                break;
            default:
                // if nothing matches the default to sort by lastName
                theFieldName = "lastName";
        }

        // create a query
        String queryString = "from Customer order by " + theFieldName;
        Query theQuery =
                currentSession.createQuery(queryString, Customer.class);

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer updateCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, id);
        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("DELETE from Customer s where s.id=" + id).executeUpdate();

    }

    @Override
    public List<Customer> searchCustomer(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = null;
        if (name.trim().length() > 0 && name != null) {
            query = session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            query.setParameter("theName", "%" + name.toLowerCase() + "%");
        } else {
            query = session.createQuery("from Customer", Customer.class);
        }
        List<Customer> customers = query.getResultList();


        return customers;

    }
}
