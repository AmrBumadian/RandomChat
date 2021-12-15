package Model;

public class User {
	private Interests interests;
	private boolean isConnected;
	String ID;
	User connectedTo;
	Channel channel;

	public User(Interests interests, String sessionID) {
		isConnected = false;
		this.interests = interests;
		ID = sessionID;
		connectedTo = null;
		channel = new Channel();
	}

	public void connect(User connectedTo) {
		this.connectedTo = connectedTo;
		isConnected = true;
		channel.addReceiver(connectedTo.getChannel());
	}

	public void disconnect() {
		channel.removeReceiver();
		isConnected = false;

		connectedTo.isConnected = false;
		connectedTo.channel.removeReceiver();

		connectedTo = null;
		connectedTo.connectedTo = null;
	}

	public String getInterests() {
		return interests.getUnifiedInterestsString();
	}

	public String getID() {
		return ID;
	}

	public Channel getChannel() {
		return channel;
	}

	public boolean isConnected() {
		return isConnected;
	}
}
