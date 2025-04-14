package com.infosys.customer_reward_assignment.repo;

import com.infosys.customer_reward_assignment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);
}
