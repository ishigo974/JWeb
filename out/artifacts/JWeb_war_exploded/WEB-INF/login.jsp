<%--
  Created by IntelliJ IDEA.
  User: menigo_m
  Date: 16/12/15
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@ include file="beginFoundation.jsp"%>

<form method="post" action="login">
    <c:if test="${!empty form.result}">
        <span style="top:10px;" class="${empty form.errors ? 'success' : 'alert'} button expand disabled">${form.result}</span>
    </c:if>
    <fieldset>
        <legend>Login</legend>

        <input type="email" id="email" name="email" value="<c:out value="${user.email}"/>" size="20"
               maxlength="60" placeholder="Email"/>
        <%--<span class="error">${form.errors['email']}</span>--%>
        <br/>

        <input type="password" id="password" name="password" size="20" maxlength="20" placeholder="Password"/>
        <%--<span class="error">${form.errors['password']}</span>--%>
        <br/>

        <input type="submit" value="Login" class="button expand right"/>
        <br/>

        ${sessionScope.userSession.email}
    </fieldset>
</form>

<%@ include file="endFoundation.jsp"%>
</body>
</html>
