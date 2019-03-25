package udpserverclient;

import java.io.*;
import java.net.Socket;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import event.EditProfileEvent;
import event.Event;
import event.HitEvent;
import event.JoinEvent;
import model.BlackJackGame;

public class Responder implements Runnable {

	private Socket clientSocket;
	private int id;
	private BlackJackGame bjg;
	public Responder(Socket clientSocket, int id, BlackJackGame bjg) {
		this.clientSocket = clientSocket;
		this.id = id;
		this.bjg = bjg;
	}
	@Override
	public void run() {

		try {
			while(true) {
				printTime();
				ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());
				Event event = (Event) inFromClient.readObject();

				event = makeResponse(event);

				event.execute(bjg);
				System.out.println("SERVER EXECUTE");
				ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
				outToClient.writeObject(event);  

			}

		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private Event makeResponse(Event event) {
		Event e = event;
		e.set(bjg);
		return e;
	}

	private void printTime() {
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		Format format = new SimpleDateFormat("HH:mm");
		System.out.println("Request processed: @ " + format.format(date));

	}

}
