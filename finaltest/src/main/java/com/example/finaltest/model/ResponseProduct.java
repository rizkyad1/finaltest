package com.example.finaltest.model;

import java.util.List;

public class ResponseProduct{

	private List<OfferItem> offer;
	private Transaction transaction;

	public List<OfferItem> getOffer(){
		return offer;
	}

	public Transaction getTransaction(){
		return transaction;
	}
}