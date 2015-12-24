<%--
  Created by IntelliJ IDEA.
  User: lopes_n
  Date: 12/24/15
  Time: 4:24 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="JWeb"/>
</jsp:include>
<body>
<%@ include file="beginFoundation.jsp"%>

<c:if test="${!empty form.result}">
    <span style="top:10px;" class="${empty form.errors ? 'success' : 'alert'} button expand disabled">${form.result}</span>
</c:if>

<jsp:include page="userPannel.jsp" />

<ul class="pricing-table">
    <li class="title"><c:out value="${article.title}"/></li>
    <li class="price">$<c:out value="${article.price}"/></li>
    <li class="description"><c:out value="${article.content}"/></li>
    <li class="bullet-item"><img src="/assets/files/<c:out value="${article.img}"/>"/></li>
    <li class="cta-button"><a class="button" href="#">Buy Now</a></li>
</ul>

<h3>User Reviews</h3>

<c:forEach var="comment" items="${comments}">
<ul class="pricing-table">
    <li class="title"><c:out value="${comment.name}"/></li>
    <li class="description"><c:out value="${comment.content}"/></li>
</ul>
</c:forEach>

<c:if test="${!empty sessionScope.userSession}">
<form method="post" action="/article/view">

    <fieldset>
        <textarea name="content" id="content" rows="5" placeholder="Content">
        </textarea>
        <br/>

        <input type="hidden" name="article" value="<c:out value="${article.id}"/>">

        <input type="submit" value="Save" class="button expand right"/>
    </fieldset>
</form>
</c:if>

<%@ include file="endFoundation.jsp"%>
</body>
</html>
