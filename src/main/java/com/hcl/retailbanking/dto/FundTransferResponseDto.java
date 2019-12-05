package com.hcl.retailbanking.dto;

import java.io.Serializable;

/**
 * @author Vasavi
 *
 */
public class FundTransferResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	private Long fromAccount;
	private Long toAccount;
	private Double amount;

	public FundTransferResponseDto(String message, Long fromAccount, Long toAccount, Double amount) {
		this.message = message;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Long getToAccount() {
		return toAccount;
	}

	public void setToAccount(Long toAccount) {
		this.toAccount = toAccount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
