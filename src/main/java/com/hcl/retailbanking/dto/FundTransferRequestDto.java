package com.hcl.retailbanking.dto;

import java.io.Serializable;

/**
 * @author Vasavi
 *
 */
public class FundTransferRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long fromAccount;
	private Long toAccount;
	private Double amount;
	private String benefactorName;
	private String transactionType;

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public String getBenefactorName() {
		return benefactorName;
	}

	public void setBenefactorName(String benefactorName) {
		this.benefactorName = benefactorName;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
}
