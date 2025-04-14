package com.infosys.customer_reward_assignment.service;

import com.infosys.customer_reward_assignment.dto.TransactionDTO;
import com.infosys.customer_reward_assignment.exception.CustomerNotFoundException;
import com.infosys.customer_reward_assignment.model.Customer;
import com.infosys.customer_reward_assignment.model.CustomerTransaction;
import com.infosys.customer_reward_assignment.repo.CustomerRepository;
import com.infosys.customer_reward_assignment.repo.CustomerTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerTransactionService {

    @Autowired
    private CustomerTransactionRepository customerTransactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    RewardPointService rewardPointService;

    public ResponseEntity<?> addTransaction(TransactionDTO transaction) {
        Optional<Customer> customer = customerRepository.findById(transaction.getCustomerId());

        CustomerTransaction customerTransaction = new CustomerTransaction();

        customerTransaction.setCustomerID(customer.get());
        customerTransaction.setDate(transaction.getDate());
        customerTransaction.setAmount(transaction.getAmount());
        customerTransaction.setSpendDetails(transaction.getSpendDetails());

        CustomerTransaction savedTransaction = customerTransactionRepository.save(customerTransaction);

        rewardPointService.processTransactionReward(savedTransaction);

        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    public ResponseEntity<CustomerTransaction> getTransactionById(Long id) {
        Optional<CustomerTransaction> transaction = customerTransactionRepository.findById(id);
        if(transaction.isPresent()){
            return new ResponseEntity<>(transaction.get(),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateTransaction(Long id,TransactionDTO transactionDTO) {

        CustomerTransaction  customerTransaction = customerTransactionRepository.findById(id).orElseThrow(()-> new RuntimeException("Transaction not found with id" +id));

        Customer customer = customerRepository.findById(transactionDTO.getCustomerId()).orElseThrow(()-> new RuntimeException("Customer not found with id "+transactionDTO.getCustomerId()));

        customerTransaction.setCustomerID(customer);
        customerTransaction.setAmount(transactionDTO.getAmount());
        customerTransaction.setDate(transactionDTO.getDate());
        customerTransaction.setSpendDetails(transactionDTO.getSpendDetails());

        CustomerTransaction updated = customerTransactionRepository.save(customerTransaction);

        rewardPointService.processTransactionReward(updated);

        return new ResponseEntity<>(updated,HttpStatus.OK);
    }

    public ResponseEntity<?> deleteTransaction(Long id) {
        Optional<CustomerTransaction> transactionOptional = customerTransactionRepository.findById(id);

        if (transactionOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Transaction not found with id: " + id);
        }

        customerTransactionRepository.deleteById(id);
        return ResponseEntity.ok("Transaction deleted successfully");
    }
}
