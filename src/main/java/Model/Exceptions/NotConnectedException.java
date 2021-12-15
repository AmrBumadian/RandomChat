package Model.Exceptions;

public class NotConnectedException extends Exception{
	@Override
	public String getMessage() {
		return "channel not connected to a peer";
	}
}
