package com.randomchat;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

public class VideoChatServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Cookie[] requestedVideoBefore = request.getCookies();
		boolean found = false;
		for (Cookie c : requestedVideoBefore) {
			if (c.getName().equalsIgnoreCase("requestedVideoBefore")) {
				found = true;
				break;
			}
		}
		if (found) {
			writer.println("we told you before idiot, video is not implemented yet");
		}
		else {
			response.addCookie(new Cookie("requestedVideoBefore", "true"));
			writer.println("Video Chat is not implemented yet");
		}
		writer.flush();
	}
}
