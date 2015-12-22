<%--
  Created by IntelliJ IDEA.
  User: menigo_m
  Date: 08/12/15
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JWeb</title>
</head>
<body>
<%@ include file="beginFoundation.jsp"%>

<c:if test="${!empty form.result}">
    <span style="top:10px;" class="${empty form.errors ? 'success' : 'alert'} button expand disabled">${form.result}</span>
</c:if>

<h1>Home page</h1>

<c:forEach var="news" items="${articles}">
    <h2><c:out value="${news.title}"/></h2>

    <p><c:out value="${news.content}"/></p>
</c:forEach>

<br/>
<a href="login" class="button success">Login</a>
<a href="signup" class="button">Sign up</a>
<a href="admin" class="button alert">Admin</a>
${sessionScope.userSession.email}
<%@ include file="endFoundation.jsp"%>
</body>
</html>
