package Model;

import java.util.*;

public class Matcher {
	static Matcher matcher;
	Map<String, LinkedList<User>> livingInterests;
	LinkedList<User> allUsers;
	Map<String, LinkedList<User>> queue;

	private Matcher() {
		livingInterests = new HashMap<>();
		allUsers = new LinkedList<>();
		queue = new HashMap<>();
	}

	public static Matcher getMatcherInstance() {
		return (matcher != null) ? matcher : (matcher = new Matcher());
	}

	public void add(User user) {
		allUsers.add(user);
		addToMappedList(livingInterests.get(user.getInterests()), user);
	}

	private void addToMappedList(LinkedList<User> list, User user) {
		if (list == null) {
			list = new LinkedList<>();
			livingInterests.put(user.getInterests(), list);
			queue.put(user.getInterests(), new LinkedList<>());
		}
		list.add(user);
	}

	public void remove(String interests, User user) {
		allUsers.remove(user);
		livingInterests.get(interests).remove(user);
	}

	public void connectToRandomUser(User user) {
		String userInterests = user.getInterests();
		queue.get(userInterests).add(user);
		LinkedList<User> usersWithSameInterests = livingInterests.get(userInterests);
		connect(user, usersWithSameInterests, queue.get(userInterests));
	}

	public synchronized void connect(User user, LinkedList<User> usersList, LinkedList<User> queue) {
		boolean connected = false;
		if (user.isConnected()) return;
		while (!connected) {
			for (User other : usersList) {
				if (other != user && !other.isConnected()) {
					connected = true;
					user.connect(other);
					other.connect(user);
					break;
				}
			}
			usersList.addAll(queue);
			queue.clear();
		}
	}
}
