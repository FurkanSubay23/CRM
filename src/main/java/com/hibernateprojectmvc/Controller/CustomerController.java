package com.hibernateprojectmvc.Controller;

import com.hibernateprojectmvc.Util.SortUtils;
import com.hibernateprojectmvc.entity.Customer;
import com.hibernateprojectmvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /*
        @GetMapping("/")
        public String customerList(Model theModel) {

            List<Customer> customers = customerService.getCustomers();
            theModel.addAttribute("customers", customers);

            return "list-customers";
        }
     */
    @GetMapping("/")
    public String listCustomers(Model theModel, @RequestParam(required = false) String sort) {
        //@RequestParam(required = false) Thanks to this option, even if this parameter is not sent during the method's invocation,
        // the method still runs and gets null by default. This allows the user to use the method without having to send this parameter.
        // get customers from the service
        List<Customer> theCustomers = null;

        // check for sort field
        if (sort != null) {
            int theSortField = Integer.parseInt(sort);
            theCustomers = customerService.getCustomers(theSortField);
        } else {
            // no sort field provided ... default to sorting by last name
            theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
        }

        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Customer customer = new Customer();
        theModel.addAttribute("customer", customer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);

        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerID") int ID, Model theModel) {
        Customer customer = customerService.updateCustomer(ID);
        theModel.addAttribute("customer", customer);
        return "customer-form";
    }

    @GetMapping("/showFormForDelete")
    public String showFormForDelete(@RequestParam("customerID") int id) {
        customerService.deleteCustomer(id);

        return "redirect:/";
    }

    @GetMapping("/searchCustomer")
    public String searchCustomer(@RequestParam("searchName") String name, Model theModel) {
        List<Customer> customers = customerService.searchCustomer(name);
        System.out.println(customers);
        theModel.addAttribute("customers", customers);
        //We make use of redirect when we are modifying the database. Adding a new customer or updating an existing customer.
        return "list-customers";
    }
}
