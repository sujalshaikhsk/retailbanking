package com.hcl.retailbanking.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.hcl.retailbanking.dto.AccountSummaryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.retailbanking.dto.FundTransferRequestDto;
import com.hcl.retailbanking.dto.FundTransferResponseDto;
import com.hcl.retailbanking.entity.Account;
import com.hcl.retailbanking.entity.Transaction;
import com.hcl.retailbanking.exception.CommonException;
import com.hcl.retailbanking.exception.MessageCode;
import com.hcl.retailbanking.repository.AccountRepository;
import com.hcl.retailbanking.repository.TransactionRepository;

/**
 * The class TransactionServiceImpl.
 * @author Vasavi
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	/**
	 * The fundTransferRepository.
	 */
	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * The accountRepository.
	 */
	@Autowired
	AccountRepository accountRepository;

	/**
	 * 
	 * @description this method is used to do fund transfer in the application
	 * @param fundTransferRequestDTO the fundTransferRequestDTO which contains
	 *                               fromAccount,toAccount,amount,transactionType
	 *                               and benefactorName
	 * @return fundTransferResponseDto
	 */
	@Override
	public FundTransferResponseDto fundTransfer(FundTransferRequestDto fundTransferRequestDTO) {
		logger.info("Inside fundTransfer method");
		Optional<Account> fromAccount = accountRepository.findById(fundTransferRequestDTO.getFromAccount());
		Optional<Account> toAccount = accountRepository.findById(fundTransferRequestDTO.getToAccount());
		
        //checking source and destination accounts existed or not
		if (!fromAccount.isPresent() || !toAccount.isPresent()) {
			throw new CommonException(MessageCode.ACCOUNT_INVALID);
		}
		//checking from source account to destination account we can not send  negative and zero balance
		if (fundTransferRequestDTO.getAmount() <= 0) {
			throw new CommonException(MessageCode.AMMOUNT_INVALID);
		}
		//checking from source account to source account we can not do  fund transactions
		if (fundTransferRequestDTO.getFromAccount().equals(fundTransferRequestDTO.getToAccount())) {
			throw new CommonException(MessageCode.SAME_ACCOUNT_INVALID);
		}
		//after fund transactions checking the transaction type
		if (!fundTransferRequestDTO.getTransactionType().toUpperCase().equals("CREDIT")) {
			Optional<Account> temp = fromAccount;
			fromAccount = toAccount;
			toAccount = temp;
		}
		//source account should be maintain with the minimal balance 
		if (!fromAccountBalanceVerification(fromAccount.get(), fundTransferRequestDTO)) {
			throw new CommonException(MessageCode.insufficentFunds(fromAccount.get().getBalance()));
		}

		fromAccount.get().setBalance(fromAccount.get().getBalance() - fundTransferRequestDTO.getAmount());
		toAccount.get().setBalance(toAccount.get().getBalance() + fundTransferRequestDTO.getAmount());
		//updating source account after transactions
		Transaction transaction = new Transaction(0, fundTransferRequestDTO.getFromAccount(),
				fundTransferRequestDTO.getToAccount(), fundTransferRequestDTO.getAmount(),
				fundTransferRequestDTO.getTransactionType(), LocalDate.now(),
				fundTransferRequestDTO.getBenefactorName());
		transactionRepository.save(transaction);
		accountRepository.save(fromAccount.get());
		accountRepository.save(toAccount.get());
		return new FundTransferResponseDto("Successfully Transfered", fundTransferRequestDTO.getFromAccount(),
				fundTransferRequestDTO.getToAccount(), fundTransferRequestDTO.getAmount());
	}

	private boolean fromAccountBalanceVerification(Account account, FundTransferRequestDto fundTransferRequestDTO) {
		if ((account.getBalance() - fundTransferRequestDTO.getAmount()) < 1000) {
			return false;
		}
		return true;

	}

	/**
	 * @description this method is used to do view account Summary in the application
	 * accountSummary() method will have account details and last five transaction
	 * @param userId
	 * @return accountSummaryDto
	 */
	public AccountSummaryDto accountSummary(Integer userId){
		AccountSummaryDto accountSummaryDto=new AccountSummaryDto();
		Account account=accountRepository.findByUserId(userId);
		if(account!=null) {
			accountSummaryDto.setAccount(account);
			List<Transaction> transactionList = transactionRepository.getLastFiveTransactions(account.getAccountNumber());
			if(transactionList!=null && !transactionList.isEmpty())
				accountSummaryDto.setTransactions(transactionList);
			else
				accountSummaryDto.setTransactions(Collections.emptyList());
		}
		return accountSummaryDto;
	}
}
