package com.infosys.customer_reward_assignment.controller;

import com.infosys.customer_reward_assignment.dto.TransactionDTO;
import com.infosys.customer_reward_assignment.model.CustomerTransaction;
import com.infosys.customer_reward_assignment.service.CustomerTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class CustomerTransactionController {

    @Autowired
    private CustomerTransactionService customerTransactionService;

    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionDTO transaction) {

        return customerTransactionService.addTransaction(transaction);
    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity<CustomerTransaction> getTransactionById(@PathVariable Long id) {
        return customerTransactionService.getTransactionById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable Long id, @RequestBody TransactionDTO transactionDTO) {
            return customerTransactionService.updateTransaction(id,transactionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        return customerTransactionService.deleteTransaction(id);
    }

}
