package com.mini.exception;

public class DuplicateException extends Exception {
	public DuplicateException(){
		this("DuplicateException.");
	}
	public DuplicateException(String message){
		super(message);
	}
}
