package com.mini.exception;

public class NotAvailableException extends Exception {
	public NotAvailableException(){
		this("NotAvailableException.");
	}
	public NotAvailableException(String message){
		super(message);
	}
}
