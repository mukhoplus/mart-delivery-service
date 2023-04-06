package com.mini.exception;

public class NotExistException extends Exception {
	public NotExistException(){
		this("NotExistException.");
	}
	public NotExistException(String message){
		super(message);
	}
}
