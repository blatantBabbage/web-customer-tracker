package com.nitesh.springdemo.controller;

import com.nitesh.springdemo.dao.CustomerDAO;
import com.nitesh.springdemo.entity.Customer;
import com.nitesh.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    // need to inject CustomerDAO dependency
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel) {

        // get the customers from the DAO
        List<Customer> theCustomers = customerService.getCustomers();

        // add the customer to the Model
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";
    }
}
