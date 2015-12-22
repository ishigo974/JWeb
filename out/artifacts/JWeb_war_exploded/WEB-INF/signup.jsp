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
<%@ include file="beginFoundation.jsp" %>

<form method="post" action="signup">
    <c:if test="${!empty form.result}">
        <span style="top:10px;" class="${empty form.errors ? 'success' : 'alert'} button expand disabled">${form.result}</span>
    </c:if>

    <fieldset>
        <legend>Sign up</legend>

        <input type="email" id="email" name="email" value="<c:out value="${user.email}"/>" size="20"
               maxlength="60" placeholder="Email"/>
        <c:if test="${!empty form.errors['email']}">
            <small class="error">${form.errors['email']}</small>
        </c:if>
        <br/>

        <input type="password" id="password" name="password" size="20" maxlength="20" placeholder="Password"/>
        <c:if test="${!empty form.errors['password']}">
            <small class="error">${form.errors['password']}</small>
        </c:if>
        <br/>

        <input type="password" id="confirmation" name="confirmation" size="20" maxlength="20"
               placeholder="Password confirmation"/>
        <c:if test="${!empty form.errors['confirmation']}">
            <small class="error">${form.errors['confirmation']}</small>
        </c:if>
        <br/>

        <input type="text" id="name" name="name" value="<c:out value="${user.name}"/>" size="20" maxlength="20"
               placeholder="Username"/>
        <c:if test="${!empty form.errors['name']}">
            <small class="error">${form.errors['name']}</small>
        </c:if>
        <br/>

        <input type="checkbox" id="news" name="news" value="<c:out value="${user.news}"/>" size="20" maxlength="20"
               placeholder="News Letter"/>
        <br/>

        <input type="submit" value="Sign up" class="button expand right"/>
    </fieldset>
</form>

<%@ include file="endFoundation.jsp" %>
</body>
</html>
