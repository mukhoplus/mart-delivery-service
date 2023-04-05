package com.mini.exception;

public class NotAvailableException extends Exception {
	public NotAvailableException(){
		this("이용할 수 있는 마트가 없습니다.");
	}
	public NotAvailableException(String message){
		super(message);
	}
}
