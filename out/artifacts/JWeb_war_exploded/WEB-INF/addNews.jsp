<%--
  Created by IntelliJ IDEA.
  User: lopes_n
  Date: 12/22/15
  Time: 7:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a News</title>
</head>
<body>
<%@ include file="beginFoundation.jsp" %>

<form method="post" action="/admin/news/add">

    <fieldset>
        <legend>Add a News</legend>

        <input type="text" id="title" name="title" value="<c:out value="${simpleNew.title}"/>" size="20"
               maxlength="60" placeholder="Title"/>
        <br/>

        <input type="textarea" id="content" name="content" value="<c:out value="${simpleNew.content}"/>"
               maxlength="150" placeholder="Content"/>
        <br/>

        <input type="submit" value="Save" class="button expand right"/>
    </fieldset>
</form>

<%@ include file="endFoundation.jsp" %>
</body>
</html>