package com.mini.exception;

public class CannotUpdateException extends Exception {
	public CannotUpdateException(){
		this("CannotUpdateException.");
	}
	public CannotUpdateException(String message){
		super(message);
	}
}
