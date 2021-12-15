package com.randomchat;

import Model.Matcher;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

public class TextChatServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		getServletContext().getRequestDispatcher("/textChat.jsp").forward(request, response);
		matchUser(user);
		System.out.println("ID:" + request.getSession().getId().substring(0,5) + " is connected to a user");
	}

	private void matchUser(User user) {
		Matcher matcher = (Matcher) getServletContext().getAttribute("matcher");
		matcher.connectToRandomUser(user);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = Optional.ofNullable(request.getParameter("operation")).orElse("");
		if (operation.equalsIgnoreCase("connect")) {
			doGet(request, response);
		} else if (operation.equalsIgnoreCase("disconnect")) {
			User user = (User) request.getSession().getAttribute("user");
			user.disconnect();
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
