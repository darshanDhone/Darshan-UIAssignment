package com.infosys.customer_reward_assignment.service;

import com.infosys.customer_reward_assignment.dto.TransactionDTO;
import com.infosys.customer_reward_assignment.model.Customer;
import com.infosys.customer_reward_assignment.model.CustomerTransaction;
import com.infosys.customer_reward_assignment.repo.CustomerRepository;
import com.infosys.customer_reward_assignment.repo.CustomerTransactionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerTransactionServiceTest {

    @InjectMocks
    private CustomerTransactionService service;

    @Mock
    private CustomerTransactionRepository transactionRepo;

    @Mock
    private CustomerRepository customerRepo;

    @Mock
    private RewardPointService rewardService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addTransactionTest() {

        TransactionDTO dto = new TransactionDTO(LocalDate.now(), 100.0, "Groceries",1L);
        Customer customer = new Customer();
        customer.setId(1L);


        when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));
        when(transactionRepo.save(any())).thenReturn(new CustomerTransaction());


        ResponseEntity<?> response = service.addTransaction(dto);


        assertEquals(201, response.getStatusCode().value());
        verify(rewardService).processTransactionReward(any());
    }

    @Test
    void transactionFoundTest() {
        CustomerTransaction transaction = new CustomerTransaction();
        transaction.setId(1L);

        when(transactionRepo.findById(1L)).thenReturn(Optional.of(transaction));

        ResponseEntity<CustomerTransaction> response = service.getTransactionById(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(transaction, response.getBody());
    }

    @Test
    void transactionNotFoundTest() {
        when(transactionRepo.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<CustomerTransaction> response = service.getTransactionById(1L);

        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void deleteTransactionFoundTest() {
        CustomerTransaction transaction = new CustomerTransaction();
        transaction.setId(1L);

        when(transactionRepo.findById(1L)).thenReturn(Optional.of(transaction));

        ResponseEntity<?> response = service.deleteTransaction(1L);

        assertEquals(200, response.getStatusCode().value());
        verify(transactionRepo).deleteById(1L);
    }

    @Test
    void deleteTransactionNotFoundTest() {
        when(transactionRepo.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = service.deleteTransaction(1L);

        assertEquals(404, response.getStatusCode().value());
    }
}
