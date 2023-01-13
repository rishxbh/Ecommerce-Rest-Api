package com.rishabh.website.exceptions;

public class ProductNotExistException extends IllegalArgumentException {

	public ProductNotExistException(String message) {
		super(message);
	}
}
