package com.hcl.retailbanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.retailbanking.entity.Account;

import java.util.Optional;

/**
 * @author Vasavi
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUserId(Integer userId);
}
