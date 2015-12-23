<%--
  Created by IntelliJ IDEA.
  User: lopes_n
  Date: 12/22/15
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Edit news"/>
</jsp:include>
<body>
<%@ include file="beginFoundation.jsp" %>

<form method="post" action="news">

    <fieldset>
        <legend>Edit</legend>

        <input type="text" id="title" name="title" value="<c:out value="${simpleNew.title}"/>" size="20"
               maxlength="60" placeholder="Title"/>
        <br/>

        <textarea name="content" id="content" rows="5" placeholder="Content">
            <c:out value="${simpleNew.content}"/>
        </textarea>
        <br/>

        <input type="hidden" name="id" value="<c:out value="${simpleNew.id}"/>">
        <input type="submit" value="Save" class="button expand right"/>
    </fieldset>
</form>

<%@ include file="endFoundation.jsp" %>
</body>
</html>