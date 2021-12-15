package listeners;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;

public class FirstRequestListener implements HttpSessionListener {
	static private ArrayList<HttpSession> notConnected = new ArrayList<>();
	static private int livingCount = 0;
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("new session created: " + se.getSession().getId());
		notConnected.add(se.getSession());
		++livingCount;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session terminated: " + se.getSession().getId());
		System.out.println("User disconnected");
		--livingCount;
	}
}
