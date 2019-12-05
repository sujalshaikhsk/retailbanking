package com.hcl.retailbanking.service;

import com.hcl.retailbanking.entity.Account;
import com.hcl.retailbanking.repository.AccountRepository;
import com.hcl.retailbanking.repository.TransactionRepository;
import com.hcl.retailbanking.util.StringConstant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.Silent.class)
public class StoreServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

//    @Mock
//    private TransactionService transactionService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    static Account account=new Account();

    /**
     * setMockStores()
     */
    @Before
    public void setMockStores() {
        account.setUserId(123445);
        account.setAccountNumber(1223346L);
        account.setBalance(3000D);
        account.setAccountType(StringConstant.SAVING);
        account.setIfscCode("HCL98989");
    }

    public static Account getMockData() {
        return account;
    }

    /**
     * testGetStoresForPositive()
     */
    @Test
    public void testGetStoresForPositive() {
        Integer userId=123456;
        Mockito.when(accountRepository.findByUserId(userId)).thenReturn(getMockData());
        assertNotNull(accountRepository.findByUserId(userId));

    }

    @Test
    public void testGetStoresForNegative() {
        Integer userId=123456999;
        Mockito.when(accountRepository.findByUserId(userId)).thenReturn(null);
        assertNull(accountRepository.findByUserId(userId));
    }
}
