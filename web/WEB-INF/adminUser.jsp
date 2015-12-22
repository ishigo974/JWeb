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
            <td class="large-3 columns text-center button midnight" style="margin:0" data-equalizer-watch>
                Username
            </td>
            <td class="large-3 columns text-center button midnight" style="margin:0" data-equalizer-watch>
                Email
            </td>
            <td class="large-3 columns text-center button midnight" style="margin:0" data-equalizer-watch>
                News Letter
            </td>
            <td class="large-3 columns text-center button midnight" style="margin:0" data-equalizer-watch>
                Edit
            </td>
        </tr>
        <c:forEach var="u" items="${users}">
            <tr>
                <td class="small-3 columns text-center"><c:out value="${u.name}"/></td>
                <td class="small-3 columns text-center"><c:out value="${u.email}"/></td>
                <td class="small-3 columns text-center"><c:out value="${u.news}"/></td>
                <td class="small-3 columns text-center"><a href="/admin/users?id=<c:out value="${u.id}"/>">Edit</a></td>
            </tr>
        </c:forEach>
    </table>


    <%@ include file="endFoundation.jsp" %>
    </body>
</html>