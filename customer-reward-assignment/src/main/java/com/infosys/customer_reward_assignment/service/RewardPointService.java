package com.infosys.customer_reward_assignment.service;

import com.infosys.customer_reward_assignment.model.Customer;
import com.infosys.customer_reward_assignment.model.CustomerTransaction;
import com.infosys.customer_reward_assignment.model.RewardPoint;
import com.infosys.customer_reward_assignment.repo.CustomerTransactionRepository;
import com.infosys.customer_reward_assignment.repo.RewardsPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class RewardPointService {

    @Autowired
    private RewardsPointRepository rewardPointRepository;

    @Autowired
    private CustomerTransactionRepository transactionRepository;

    public int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (int)((amount - 100) * 2);
            points += 50;
        } else if (amount > 50) {
            points += (int)(amount - 50);
        }
        return points;
    }

    public void processTransactionReward(CustomerTransaction transaction) {
        Customer customer = transaction.getCustomer();
        int points = calculatePoints(transaction.getAmount());

        LocalDate date = transaction.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = date.getMonthValue();
        int year = date.getYear();

        RewardPoint rewardPoint = rewardPointRepository
                .findByCustomerIdAndMonthAndYear(customer.getId(), month, year)
                .orElse(null);

        if (rewardPoint == null) {
            rewardPoint = new RewardPoint(customer, points, year, month);
        } else {
            rewardPoint.setPoints(rewardPoint.getPoints() + points);
        }

        rewardPointRepository.save(rewardPoint);
    }

    public ResponseEntity<?> getRewardsForCustomer(Long customerId) {
        List<RewardPoint> rewards = rewardPointRepository.findByCustomerId(customerId);
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllRewards() {
        return new ResponseEntity<>(rewardPointRepository.findAll(), HttpStatus.OK);
    }
}
