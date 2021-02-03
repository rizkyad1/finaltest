package com.example.finaltest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction{

	@JsonProperty("transaction_id")
	private String transactionId;

	@JsonProperty("status_code")
	private String statusCode;

	@JsonProperty("status_desc")
	private String statusDesc;

	@JsonProperty("channel")
	private String channel;

	public String getTransactionId(){
		return transactionId;
	}

	public String getStatusCode(){
		return statusCode;
	}

	public String getStatusDesc(){
		return statusDesc;
	}

	public String getChannel(){
		return channel;
	}
}