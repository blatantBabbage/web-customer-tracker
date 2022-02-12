package com.nitesh.springdemo.controller;

import com.nitesh.springdemo.entity.Customer;
import com.nitesh.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        //create model attribute to bind form data
        Customer theCustomer = new Customer();
        theModel.addAttribute("customer", theCustomer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

        // save the customer using our service
        customerService.saveCustomer(theCustomer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

        // get customer from customer service
        Customer theCustomer = customerService.getCustomer(theId);

        // set customer as model attribute to pre-populate data
        theModel.addAttribute("customer", theCustomer);

        // send over to our customer form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("customerId") int theId) {
        // delete the customer
        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("theSearchName") String theSearchName, Model theModel) {

        // search customers from the service
        List<Customer> theCustomer = customerService.searchCustomer(theSearchName);

        // add the customers to the model
        theModel.addAttribute("customers", theCustomer);
        return "list-customers";
    }
}
