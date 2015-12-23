<%--
  Created by IntelliJ IDEA.
  User: lopes_n
  Date: 12/22/15
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Edit user"/>
</jsp:include>
<body>
<%@ include file="beginFoundation.jsp" %>

<jsp:include page="userPannel.jsp" />

<form method="post" action="users">
    <c:if test="${!empty form.result}">
        <span style="top:10px;" class="${empty form.errors ? 'success' : 'alert'} button expand disabled">${form.result}</span>
    </c:if>

    <fieldset>
        <legend>Edit</legend>

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

        <input type="checkbox" id="news" name="news" value="checked" size="20" maxlength="20"
               placeholder="News Letter" <c:if test="${user.news == true}">checked</c:if>/>
        <label for="news">News Letter</label>
        <br/>

        <input type="checkbox" id="admin" name="admin" value="admin" size="20" maxlength="20"
               placeholder="Admin" <c:if test="${user.admin == true}">checked</c:if>/>
        <label for="admin">Admin</label>
        <br/>

        <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
        <input type="submit" value="Save" class="button expand right"/>
    </fieldset>
</form>

<%@ include file="endFoundation.jsp" %>
</body>
</html>