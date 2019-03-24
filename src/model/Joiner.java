package model;

import java.util.concurrent.TimeUnit;

public class Joiner implements Requestable {
	private BlackJackGame bjg;
	
	public Joiner(BlackJackGame bjg) {
		this.bjg = bjg;
	}
	
	public Joiner() {
		
	}
	
	@Override
	public void request() {
		
		System.out.println("JOINER CLASS");
	}

}
