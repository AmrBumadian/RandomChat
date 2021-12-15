package com.randomchat;

import Model.Channel;
import Model.Exceptions.NotConnectedException;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class MessagingServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user.isConnected()) {
			getMessages(user, response);
		} else {
			sendDisconnected(response);
		}
	}

	private void getMessages(User user, HttpServletResponse response) throws IOException {
		Channel userChannel = user.getChannel();
		var messagesStatus = userChannel.retrieveAllMessages();
		if (messagesStatus.getCode().equals("NO")) {
			response.setStatus(205);
		} else {
			PrintWriter writer = response.getWriter();
			var allMessages = messagesStatus.getMessages();
			for (String message : allMessages) {
				if (!message.equals(""))
					writer.println(message);
			}
			writer.close();
		}
	}

	private void sendDisconnected(HttpServletResponse response) {
		response.setStatus(101);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		User user = (User) request.getSession().getAttribute("user");
		sendMessage(user, message);
	}

	private void sendMessage(User user, String message) {
		try {
			user.getChannel().send(message);
		} catch (NotConnectedException e) {
			System.out.println(user.getID() + ": Not Connected");
		}
	}
}

