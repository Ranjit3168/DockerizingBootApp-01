package com.bpl.error;

public class TouriestNotFoundException extends RuntimeException {

	public TouriestNotFoundException() {
		super();
	}
	
	public TouriestNotFoundException(String msg) {
		super(msg);
	}
}
