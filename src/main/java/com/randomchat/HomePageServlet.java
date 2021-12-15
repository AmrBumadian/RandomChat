package com.randomchat;

import Model.Interests;
import Model.Matcher;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

public class HomePageServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		Matcher matcher = Matcher.getMatcherInstance();
		getServletContext().setAttribute("matcher", matcher);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Interests interests = parseInterestsThenCreateUser(request);
		HttpSession session = request.getSession();
		createUserThenBindToSession(interests, session);
		redirectToChatType(request, response);
	}

	private Interests parseInterestsThenCreateUser(HttpServletRequest request) {
		String interestsString = Optional.ofNullable(request.getParameter("interests")).orElse("");
		return new Interests(interestsString);
	}

	private void createUserThenBindToSession(Interests interests, HttpSession session) {
		User user = new User(interests, session.getId());
		session.setAttribute("user", user);
		addToMatcher(user);
	}

	private void addToMatcher(User user) {
		Matcher matcher = (Matcher) getServletContext().getAttribute("matcher");
		matcher.add(user);
	}

	private void redirectToChatType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String chatType = Optional.ofNullable(request.getParameter("chat")).orElse("none");
		if (chatType.equalsIgnoreCase("text")) {
			response.sendRedirect("chat/textchat");
		} else if (chatType.equalsIgnoreCase("video")) {
			response.sendRedirect("chat/videochat");
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
