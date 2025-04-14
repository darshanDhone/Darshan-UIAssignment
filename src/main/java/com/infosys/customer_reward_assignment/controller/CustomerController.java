package com.infosys.customer_reward_assignment.controller;

import com.infosys.customer_reward_assignment.model.Customer;
import com.infosys.customer_reward_assignment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody Customer customer){
        return customerService.register(customer);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Customer customer){

        return customerService.login(customer);
    }
    @GetMapping("/getAll")
    public List<Customer> getAll(){
        return customerService.getAll();
    }
}
