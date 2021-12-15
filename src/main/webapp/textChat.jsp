<%--
  Created by IntelliJ IDEA.
  User: Amr Bumadian
  Date: 10/27/2021
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TextChat</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/messaging.css">
</head>
<body>
<div id="header">
</div>

<div id="messages">
    <div class="message"><span>You:</span>
        <p>this is a test message</p>
    </div>
    <div class="message"><span>Stranger:</span>
        <p>this is another test message</p>
    </div>
</div>
<div id="footer">
    <input id="stop" class="button" type="button" name="stop" value="stop" onclick="disconnect()">
    <textarea id="message"></textarea>
    <input id="send" class="button" type="button" name="send" value="send" onclick="sendMessage()">
</div>
<script src="${pageContext.request.contextPath}/scripts/messageAJAX.js"></script>
<script src="${pageContext.request.contextPath}/scripts/setUp.js"></script>
</body>
</html>
