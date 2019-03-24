package model;

import java.io.Serializable;

public class Ender implements Requestable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1631115892095947768L;
	private BlackJackGame bjg;
	public Ender(BlackJackGame bjg) {
		this.bjg = bjg;
	}
	
	public Ender() {
		
	}
	
	public void request() {
		System.out.println("hello");
	}

}
