package com.hcl.retailbanking.controller;

import com.hcl.retailbanking.dto.AccountSummaryDto;
import com.hcl.retailbanking.service.TransactionService;
import com.hcl.retailbanking.util.ApiConstant;
import com.hcl.retailbanking.util.StringConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hcl.retailbanking.dto.FundTransferRequestDto;
import com.hcl.retailbanking.dto.FundTransferResponseDto;
import com.hcl.retailbanking.service.TransactionServiceImpl;

/**
 * @author Vasavi
 *
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/transactions")
public class TransactionController {

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	/**
	 * The transactionServiceImpl.
	 */
	@Autowired
	TransactionService transactionService;

	/**
	 * 
	 * @description this method is used to do fund transfer in the application
	 * @param fundTransferRequestDto the fundTransferRequestDto which contains
	 *                               fromAccount,toAccount,amount,transactionType
	 *                               and benefactorName
	 * @return fundTransferResponseDto
	 */
	@PostMapping("/fundTransfer")
	public ResponseEntity<FundTransferResponseDto> fundTransfer(
			@RequestBody FundTransferRequestDto fundTransferRequestDto) {
		logger.debug("In TransactionController:fundTransfer");
		FundTransferResponseDto fundTransferResponseDto = transactionService.fundTransfer(fundTransferRequestDto);
		return new ResponseEntity<>(fundTransferResponseDto, HttpStatus.OK);
	}

	/**
	 * @description this method is used to view account summary
	 *  accountSummary() method will return account summary as well as last five transactions
	 * @param userId
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/{userId}")
	public ResponseEntity<AccountSummaryDto> accountSummary(@PathVariable("userId") Integer userId) {
		logger.debug("In TransactionController:fundTransfer");
		AccountSummaryDto accountSummaryDto = transactionService.accountSummary(userId);
		return new ResponseEntity<>(accountSummaryDto, HttpStatus.OK);
	}
}
