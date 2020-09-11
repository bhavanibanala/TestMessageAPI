package com.hackathon.messageservice.data;

public class SMSRequest {
	
	private String to;
	private Integer validity;
	private String priority;
	private String notifyURL;
	private String body;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Integer getValidity() {
		return validity;
	}
	public void setValidity(Integer validity) {
		this.validity = validity;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getNotifyURL() {
		return notifyURL;
	}
	public void setNotifyURL(String notifyURL) {
		this.notifyURL = notifyURL;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public SMSRequest(String to, Integer validity, String priority, String notifyURL, String body) {
		super();
		this.to = to;
		this.validity = validity;
		this.priority = priority;
		this.notifyURL = notifyURL;
		this.body = body;
	}
	
	
	public SMSRequest() {
		super();
	}
	@Override
	public String toString() {
		return "SMSRequest [to=" + to + ", validity=" + validity + ", priority=" + priority + ", notifyURL=" + notifyURL
				+ ", body=" + body + "]";
	}
}
