package com.nitesh.springdemo.dao;

import com.nitesh.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    // need to inject session factory dependency
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a HQL query ... sort by last name
        Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);

        // execute the query to get result
        List<Customer> customers = theQuery.getResultList();

        // return list of customer retrieved
        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save the customer to db
        currentSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // now retrieve/read from db using customer id primary key
        Customer theCustomer = currentSession.get(Customer.class, theId);

        return theCustomer;
    }

    @Override
    public void deleteCustomer(int theId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // delete the customer using HQL
        Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", theId);
        theQuery.executeUpdate();
    }
}
