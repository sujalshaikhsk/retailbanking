package com.hcl.retailbanking.exception;

public class MessageCode {

	private int code;
	private String message;

	public MessageCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public static MessageCode RECORD_NOT_FOUND = new MessageCode(101, "Record not found");
	public static MessageCode ACCOUNT_INVALID = new MessageCode(102, "Inavalid account");
	public static MessageCode AMMOUNT_INVALID = new MessageCode(103, "Inavalid amount and amount should be greaterthan zero");
	public static MessageCode SAME_ACCOUNT_INVALID = new MessageCode(104, "You can not transfer to the same account");

	public static MessageCode insufficentFunds(Double amount) {
		return new MessageCode(105,
				"Insufficiemt funds because you need to maintain minimal balance should be 1000. You are allowed to transfer "
						+ (amount - 1000));
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static MessageCode getRecordNotFound() {
		return RECORD_NOT_FOUND;
	}

	public static void setRecordNotFound(MessageCode recordNotFound) {
		RECORD_NOT_FOUND = recordNotFound;
	}

	public static MessageCode getAccountInvalid() {
		return ACCOUNT_INVALID;
	}

	public static void setAccountInvalid(MessageCode accountInvalid) {
		ACCOUNT_INVALID = accountInvalid;
	}

	public static MessageCode getAmmountInvalid() {
		return AMMOUNT_INVALID;
	}

	public static void setAmmountInvalid(MessageCode ammountInvalid) {
		AMMOUNT_INVALID = ammountInvalid;
	}

	public static MessageCode getSameAccountInvalid() {
		return SAME_ACCOUNT_INVALID;
	}

	public static void setSameAccountInvalid(MessageCode sameAccountInvalid) {
		SAME_ACCOUNT_INVALID = sameAccountInvalid;
	}
}