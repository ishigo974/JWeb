<%--
  Created by IntelliJ IDEA.
  User: lopes_n
  Date: 12/22/15
  Time: 7:34 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Add a news"/>
</jsp:include>
<body>
<%@ include file="beginFoundation.jsp" %>

<jsp:include page="userPannel.jsp" />

<form method="post" action="/admin/news/add">

    <fieldset>
        <legend>Add a News</legend>

        <input type="text" id="title" name="title" value="<c:out value="${simpleNew.title}"/>" size="20"
               maxlength="60" placeholder="Title"/>
        <br/>

        <textarea name="content" id="content" rows="5" placeholder="Content">
            <c:out value="${simpleNew.content}"/>
        </textarea>
        <br/>

        <input type="submit" value="Save" class="button expand right"/>
    </fieldset>
</form>

<%@ include file="endFoundation.jsp" %>
</body>
</html>