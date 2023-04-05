package com.mini.exception;

public class DuplicateException extends Exception {
	public DuplicateException(){
		this("이미 존재하는 값입니다.");
	}
	public DuplicateException(String message){
		super(message);
	}
}
