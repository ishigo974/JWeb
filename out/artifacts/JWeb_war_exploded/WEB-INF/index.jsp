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
<h1>Home page</h1>

<c:forEach var="news" items="${articles}">
    <h2><c:out value="${news.title}"/></h2>

    <p><c:out value="${news.content}"/></p>
</c:forEach>

<br/>
<<<<<<< HEAD
<a href="login" class="button success">Login</a>
<a href="signup" class="button">Sign up</a>
<a href="admin" class="button alert">Admin</a>

<%@ include file="endFoundation.jsp"%>
=======
<a href="login">Login</a>
<a href="signup">Sign up</a>
<a href="admin">Admin</a>
${sessionScope.userSession.email}
>>>>>>> ad8e26e76922b398f6594fedd9024f00cb2fb9cd
</body>
</html>
