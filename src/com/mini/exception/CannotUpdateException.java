package com.mini.exception;

public class CannotUpdateException extends Exception {
	public CannotUpdateException(){
		this("재고를 변동시킬 수 없습니다.");
	}
	public CannotUpdateException(String message){
		super(message);
	}
}
