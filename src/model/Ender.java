package model;

import java.util.concurrent.TimeUnit;

public class Ender implements Requestable{

	private BlackJackGame bjg;
	public Ender(BlackJackGame bjg) {
		this.bjg = bjg;
	}
	
	public Ender() {
		
	}
	
	public void request() {

		System.out.println("ENDER CLASS");
	}

}
