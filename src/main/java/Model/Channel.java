package Model;

import Model.Exceptions.NotConnectedException;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

import Utilities.MessageQueueStatus;

public class Channel {
	Channel receiver;
	Queue<String> messages;

	public Channel() {
		messages = new LinkedList<>();
	}

	public void addReceiver(Channel receiver) {
		this.receiver = receiver;
	}

	public void removeReceiver() {
		this.receiver = null;
	}

	public void send(String message) throws NotConnectedException {
		if (receiver == null) throw new NotConnectedException();
		receiver.receive(message);
	}

	public void receive(String message) {
		messages.add(message);
		System.out.println("Message received: " + message);
	}

	public MessageQueueStatus retrieveAllMessages() {
		String code;
		LinkedList<String> allMessages;
		if (messages.size() == 0) {
			code = "NO";
			allMessages = new LinkedList<>();
		} else {
			code = "OK";
			allMessages = new LinkedList<>(messages);
			messages.clear();
		}
		return new MessageQueueStatus(allMessages, code);
	}
}

