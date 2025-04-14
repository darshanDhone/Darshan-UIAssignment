package com.infosys.customer_reward_assignment.service;

import com.infosys.customer_reward_assignment.model.Customer;
import com.infosys.customer_reward_assignment.repo.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void registerTest() {

        Customer customer = new Customer("Darshan", "@Darshan1");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        ResponseEntity<Customer> response = customerService.register(customer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
        verify(customerRepository).save(customer);

    }

    @Test
    void loginTest() {
        Customer customer = new Customer("Darshan", "D@123");
        when(customerRepository.findByUsername("Darshan")).thenReturn(Optional.of(customer));

        ResponseEntity<?> response = customerService.login(new Customer("john", "pass123"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("login successful", response.getBody());
    }

    @Test
    void getAllTest() {

        List<Customer> mockList = List.of(
                new Customer("Darshan", "@D123"),
                new Customer("Pallavi", "@P123"));

        when(customerRepository.findAll()).thenReturn(mockList);

        List<Customer> result = customerService.getAll();

        assertEquals(2, result.size());
        assertEquals("Darshan", result.getFirst().getUsername());
    }
}