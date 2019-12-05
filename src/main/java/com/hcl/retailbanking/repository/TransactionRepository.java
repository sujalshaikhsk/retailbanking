package com.hcl.retailbanking.repository;

import com.hcl.retailbanking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.retailbanking.entity.Transaction;

import java.util.List;

/**
 * @author Vasavi
 *
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findTop5ByFromAccountOrderByTransactionDateDesc(Long accountNumber);

    @Query("select t from Transaction t where t.fromAccount =:accountNumber order by t.transactionDate desc")
    List<Transaction> getLastFiveTransactions(@Param("accountNumber") Long accountNumber);
}
