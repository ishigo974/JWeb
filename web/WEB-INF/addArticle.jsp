<%--
  Created by IntelliJ IDEA.
  User: lopes_n
  Date: 12/23/15
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Add an Article"/>
</jsp:include>
<body>
<%@ include file="beginFoundation.jsp" %>

<jsp:include page="userPannel.jsp" />

<form method="post" action="/admin/articles/add" enctype="multipart/form-data">

    <fieldset>
        <legend>Add an Article</legend>

        <input type="text" id="title" name="title" value="<c:out value="${article.title}"/>" size="20"
               maxlength="60" placeholder="Title"/>
        <br/>

        <input type="text" id="price" name="price" value="<c:out value="${article.price}"/>" size="20"
               maxlength="60" placeholder="Price"/>
        <br/>

        <input type="file" id="image" name="image" />
        <br/>

        <textarea name="content" id="content" rows="5" placeholder="Content">
            <c:out value="${article.content}"/>
        </textarea>
        <br/>

        <input type="submit" value="Save" class="button expand right"/>
    </fieldset>
</form>

<%@ include file="endFoundation.jsp" %>
</body>
</html>