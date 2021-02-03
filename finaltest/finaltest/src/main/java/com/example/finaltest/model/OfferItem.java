package com.example.finaltest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OfferItem {

	@JsonProperty("price")
	private String price;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private String id;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}