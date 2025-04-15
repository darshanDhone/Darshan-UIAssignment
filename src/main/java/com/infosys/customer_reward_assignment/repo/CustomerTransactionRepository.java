package com.infosys.customer_reward_assignment.repo;

import com.infosys.customer_reward_assignment.model.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction,Long> {


    List<CustomerTransaction> findByCustomerId(Long customerId);

    List<CustomerTransaction> findByCustomerIdAndDateBetween(Long customerId, LocalDate date, LocalDate date1);
}
