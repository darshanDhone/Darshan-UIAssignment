package com.infosys.customer_reward_assignment.service;

import com.infosys.customer_reward_assignment.model.Customer;
import com.infosys.customer_reward_assignment.model.CustomerTransaction;
import com.infosys.customer_reward_assignment.model.RewardPoint;
import com.infosys.customer_reward_assignment.repo.CustomerTransactionRepository;
import com.infosys.customer_reward_assignment.repo.RewardsPointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RewardPointServiceTest {

    @Mock
    private RewardsPointRepository rewardPointRepository;

    @Mock
    private CustomerTransactionRepository transactionRepository;

    @InjectMocks
    private RewardPointService rewardPointService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void processTransactionRewardTest() {

        Customer customer = new Customer();
        customer.setId(1L);

        CustomerTransaction transaction = new CustomerTransaction();
        transaction.setAmount(150.0);
        transaction.setCustomer(customer);
        transaction.setDate(LocalDate.now());

        RewardPoint existingReward = new RewardPoint(customer, 50, 2025, 4);

        when(rewardPointRepository.findByCustomerIdAndMonthAndYear(anyLong(), anyInt(), anyInt()))
                .thenReturn(Optional.of(existingReward));

        rewardPointService.processTransactionReward(transaction);

        verify(rewardPointRepository, times(1)).save(any(RewardPoint.class));
    }



    @Test
    void getRewardsForCustomerTest() {

        List<RewardPoint> rewards = Arrays.asList(new RewardPoint(new Customer(), 100, 2025, 4), new RewardPoint(new Customer(), 50, 2025, 5));
        when(rewardPointRepository.findByCustomerId(1L)).thenReturn(rewards);

        ResponseEntity<?> response = rewardPointService.getRewardsForCustomer(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, ((List<?>) response.getBody()).size());
    }

    @Test
    void getRewardsSummaryForCustomerTest() {
        Customer customer = new Customer();
        List<CustomerTransaction> transactions = Arrays.asList(
                new CustomerTransaction(customer, "Purchase A", 120.0, LocalDate.parse("2024-04-10")),
                new CustomerTransaction(customer, "Purchase B", 80.0, LocalDate.parse("2024-05-10"))
        );

        when(transactionRepository.findByCustomerIdAndDateBetween(eq(1L), any(), any()))
                .thenReturn(transactions);

        ResponseEntity<?> response = rewardPointService.getRewardSummaryForCustomer(1L, "2024-05-16");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);

        Map<String, Object> result = (Map<String, Object>) response.getBody();

        int expectedPoints = rewardPointService.calculatePoints(120.0) + rewardPointService.calculatePoints(80.0);
        assertEquals(expectedPoints, result.get("totalRewards"));

        Map<String, Integer> rewardsPerMonth = (Map<String, Integer>) result.get("rewardsPerMonth");
        assertNotNull(rewardsPerMonth);
        assertFalse(rewardsPerMonth.isEmpty());
    }

}