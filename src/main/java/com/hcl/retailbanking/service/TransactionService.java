package com.hcl.retailbanking.service;

import com.hcl.retailbanking.dto.AccountSummaryDto;
import com.hcl.retailbanking.dto.FundTransferRequestDto;
import com.hcl.retailbanking.dto.FundTransferResponseDto;

/**
 * The interface TransactionService.
 * @author Vasavi
 *
 */
public interface TransactionService {

	AccountSummaryDto accountSummary(Integer userId);

	/**
	 * 
	 * @description this method is used to do fund transfer in the application.
	 * @param fundTransferRequestDTO the fundTransferRequestDTO which contains
	 *                               fromAccount,toAccount,amount,transactionType
	 *                               and benefactorName.
	 * @return fundTransferResponseDto
	 */
	public FundTransferResponseDto fundTransfer(FundTransferRequestDto fundTransferRequestDTO);

}
