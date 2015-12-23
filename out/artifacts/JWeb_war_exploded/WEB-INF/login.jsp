<%--
  Created by IntelliJ IDEA.
  User: menigo_m
  Date: 16/12/15
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Login"/>
</jsp:include>
<body>
<%@ include file="beginFoundation.jsp"%>

<jsp:include page="userPannel.jsp" />

<form method="post" action="login">
    <c:if test="${!empty form.result}">
        <span style="top:10px;" class="${empty form.errors ? 'success' : 'alert'} button expand disabled">${form.result}</span>
    </c:if>
    <fieldset>
        <legend>Login</legend>

        <input type="email" id="email" name="email" value="<c:out value="${user.email}"/>" size="20"
               maxlength="60" placeholder="Email"/>
        <c:if test="${!empty form.errors['email']}">
            <small class="error">${form.errors['email']}</small>
        </c:if>
        <br/>

        <input type="password" id="password" name="password" size="20" maxlength="20" placeholder="Password" class="input_field"/>
        <c:if test="${!empty form.errors['password']}">
            <small class="error">${form.errors['password']}</small>
        </c:if>
        <br/>

        <input type="submit" value="Login" class="button expand right"/>
        <br/>

        ${sessionScope.userSession.email}
    </fieldset>
</form>

<%@ include file="endFoundation.jsp"%>
</body>
</html>
