<%--
  Created by IntelliJ IDEA.
  User: menigo_m
  Date: 09/12/15
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<a>
    <%@ include file="beginFoundation.jsp" %>
    <h1>Administration page</h1>

    <a href="#">
        <div class="button success large-3 columns">All users</div>
    </a>
    <a href="#">
        <div class="button large-3 columns">All news</div>
    </a>
    <a href="#">
        <div class="button warning large-3 columns">All products</div>
    </a>
    <a href="#">
        <div class="button alert large-3 columns">Create user</div>
    </a>

    <table class="large-8 large-push-2 small-12 columns" style="padding: 0px; border: 0;">
        <tr>
            <th class="small-4 columns text-center button midnight" style="margin:0" data-equalizer-watch>
                Username
            </th>
            <th class="small-4 columns text-center button midnight" style="margin:0" data-equalizer-watch>
                Email
            </th>
            <th class="small-4 columns text-center button midnight" style="margin:0" data-equalizer-watch>
                Date de création
            </th>
        </tr>

        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.date_created}"/></td>
            </tr>
        </c:forEach>
    </table>


    <%@ include file="endFoundation.jsp" %>
    </body>
</html>
