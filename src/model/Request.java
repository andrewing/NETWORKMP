package model;

public class Request {
	private String request;
	public Request(String request) {
		this.request = request;
	}
	
	public Request(byte[] request) {
		this.request  = new String(request);
	}
	public Request() {
		this.request = null;
	}
	
	public String getRequest() {
		return request;
	}
	
	public void setRequest(String request) {
		this.request = request;
	}
}
