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
<h1>Home page</h1>

<c:forEach var="news" items="${articles}">
    <h2><c:out value="${news.title}"/></h2>
    <p><c:out value="${news.content}"/></p>
</c:forEach>

<br/>
<a href="admin.jsp" class="button">Admin</a>
</body>
</html>
