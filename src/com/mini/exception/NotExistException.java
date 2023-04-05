package com.mini.exception;

public class NotExistException extends Exception {
	public NotExistException(){
		this("존재하지 않는 값입니다.");
	}
	public NotExistException(String message){
		super(message);
	}
}
