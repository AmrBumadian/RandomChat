package Utilities;

import java.util.LinkedList;

public class MessageQueueStatus {

	private LinkedList<String> messages;
	private String code;

	public MessageQueueStatus(LinkedList<String> messages, String code) {
		this.messages = messages;
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public LinkedList<String> getMessages() {
		return messages;
	}
}
