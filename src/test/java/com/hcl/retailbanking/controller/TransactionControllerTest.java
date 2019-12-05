package com.hcl.retailbanking.controller;

import com.hcl.retailbanking.dto.AccountSummaryDto;
import com.hcl.retailbanking.entity.Account;
import com.hcl.retailbanking.entity.Transaction;
import com.hcl.retailbanking.service.TransactionService;
import com.hcl.retailbanking.util.StringConstant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.Silent.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    AccountSummaryDto accountSummaryDto=new AccountSummaryDto();
    static Account account=new Account();
    static String accountJson = "{ \"account\": { \"accountNumber\": 0, \"storeLocation\": \"Bangalore\", \"storeName\": \"HP\", \"storeRating\": 4.5 }}";

    /**
     * setUp()
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        account.setUserId(123445);
        account.setAccountNumber(1223346L);
        account.setBalance(3000D);
        account.setAccountType(StringConstant.SAVING);
        account.setIfscCode("HCL98989");

        Transaction transaction = new Transaction();
        transaction.setTransactionId(1);
        transaction.setFromAccount(1223455L);

        accountSummaryDto.setTransactions(getMockData());
        accountSummaryDto.setAccount(account);

        mockMvc = MockMvcBuilders.standaloneSetup(transactionController)
                .build();
    }

    public static List<Transaction> getMockData() {
        return Stream.of(new Transaction(), new Transaction()).collect(Collectors.toList());
    }

    /**
     * testGetStoresForPositive()
     *
     * @throws Exception
     */
    @Test
    public void testGetStoresForPositive() throws Exception {
        Integer userId=123456;
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/stores")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Mockito.when(transactionService.accountSummary(userId)).thenReturn(accountSummaryDto);

        Mockito.verify(transactionService).accountSummary(userId);
    }

    /**
     * testGetStoresForNegative()
     *
     * @throws Exception
     */
    @Test
    public void testGetStoresForNegative() throws Exception {

        Integer userId=123456;
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/stores")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Mockito.when(transactionService.accountSummary(userId)).thenReturn(accountSummaryDto);

        //Mockito.verify(transactionService).accountSummary(userId);

        assertNull(transactionService.accountSummary(userId));
    }
}
