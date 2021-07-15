package com.ifisolution.simpleproject.controller;

import com.ifisolution.simpleproject.entity.Customers;
import com.ifisolution.simpleproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customers> getCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping
    public void addCustomer(@RequestBody Customers customers){
        customerService.addCustomer(customers);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteCustomer(id);
    }

    @PutMapping("{id}")
    public void updateCustomer(@PathVariable("id") Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String phone){
        customerService.updateCustomer(id, name, phone);
    }


}

