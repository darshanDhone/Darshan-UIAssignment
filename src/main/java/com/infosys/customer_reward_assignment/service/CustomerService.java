package com.infosys.customer_reward_assignment.service;

import com.infosys.customer_reward_assignment.exception.UsernameNotFoundException;
import com.infosys.customer_reward_assignment.model.Customer;
import com.infosys.customer_reward_assignment.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<Customer> register(Customer customer) {
        Customer createdCustomer =  customerRepository.save(customer);

        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    public ResponseEntity<?> login(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findByUsername(customer.getUsername());

        if (optionalCustomer.isPresent()) {
            if(optionalCustomer.get().getPassword().equals(customer.getPassword())){
                return new ResponseEntity<>("login successful", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
            }
        }
        else {
            return new ResponseEntity<>(new UsernameNotFoundException("User not found").getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
