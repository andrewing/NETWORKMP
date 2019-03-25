package event;

import java.io.Serializable;

import model.BlackJackGame;

public abstract class Event implements Serializable{
	public abstract void execute(BlackJackGame bjg);
	public abstract void set(BlackJackGame bjg);
	public abstract void statusPrint();
}
