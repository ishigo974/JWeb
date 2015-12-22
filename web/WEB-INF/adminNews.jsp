<%--
  Created by IntelliJ IDEA.
  User: lopes_n
  Date: 12/22/15
  Time: 4:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<%@ include file="beginFoundation.jsp" %>

<jsp:include page="adminPannel.jsp" />

<a href="/admin/news/add">
    <div class="row large-12">
        <div class="button midnight large-push-5 large-2 columns">Add a News</div>
    </div>
</a>

<table class="large-8 large-push-2 small-12 columns" style="padding: 0px; border: 0;">
    <tr>
        <td class="large-4 columns text-center button midnight" style="margin:0" data-equalizer-watch>
            Title
        </td>
        <td class="large-4 columns text-center button midnight" style="margin:0" data-equalizer-watch>
            Edit
        </td>
        <td class="large-4 columns text-center button midnight" style="margin:0" data-equalizer-watch>
            Delete
        </td>
    </tr>
    <c:forEach var="n" items="${news}">
        <tr>
            <td class="small-3 columns text-center"><c:out value="${n.title}"/></td>
            <td class="small-3 columns text-center"><a href="/admin/news?id=<c:out value="${n.id}"/>">Edit</a></td>
            <td class="small-3 columns text-center"><a href="/admin/news/delete?id=<c:out value="${n.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>