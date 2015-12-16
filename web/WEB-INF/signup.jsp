<%--
  Created by IntelliJ IDEA.
  User: menigo_m
  Date: 16/12/15
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up page</title>
</head>
<body>
<%@ include file="beginFoundation.jsp"%>

<form method="post" action="signup">
    <fieldset>
        <legend>Sign up</legend>

        <input type="email" id="email" name="email" value="<c:out value="${user.email}"/>" size="20"
               maxlength="60" placeholder="Email"/>
        <%--<span class="error">${form.errors['email']}</span>--%>
        <br/>

        <input type="password" id="password" name="password" size="20" maxlength="20" placeholder="Password"/>
        <%--<span class="error">${form.errors['password']}</span>--%>
        <br/>

        <input type="password" id="confirmation" name="confirmation" size="20" maxlength="20"
               placeholder="Password confirmation"/>
        <%--<span class="error">${form.errors['confirmation']}</span>--%>
        <br/>

        <input type="text" id="name" name="name" value="<c:out value="${user.name}"/>" size="20" maxlength="20"
               placeholder="Username"/>
        <%--<span class="error">${form.errors['name'] ? }</span>--%>
        <br/>

        <input type="submit" value="Sign up"/>
        <br/>

        <p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>
    </fieldset>
</form>

<%@ include file="endFoundation.jsp"%>
</body>
</html>
