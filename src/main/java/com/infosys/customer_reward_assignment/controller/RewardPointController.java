package com.infosys.customer_reward_assignment.controller;

import com.infosys.customer_reward_assignment.service.RewardPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rewards")
public class RewardPointController {

    @Autowired
    private RewardPointService rewardPointService;

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getCustomerRewards(@PathVariable Long customerId) {
        return rewardPointService.getRewardsForCustomer(customerId);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllRewards() {
        return rewardPointService.getAllRewards();
    }

    @GetMapping("/customer/{customerId}/summary")
    public ResponseEntity<?> getCustomerRewardSummary(@PathVariable Long customerId) {
        return rewardPointService.getRewardSummaryForCustomer(customerId);
    }

}
