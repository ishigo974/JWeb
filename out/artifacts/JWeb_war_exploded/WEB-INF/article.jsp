<%--
  Created by IntelliJ IDEA.
  User: lopes_n
  Date: 12/23/15
  Time: 5:31 PM
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

<h1>Products</h1>

<c:forEach var="article" items="${articles}">
    <ul class="pricing-table">
        <a href="/article/view?id=<c:out value="${article.id}"/>"><li class="title"><c:out value="${article.title}"/></li></a>
        <li class="price">$<c:out value="${article.price}"/></li>
        <li class="description"><c:out value="${article.content}"/></li>
        <li class="bullet-item"><img src="/assets/files/<c:out value="${article.img}"/>"/></li>
        <c:if test="${!empty sessionScope.userSession}">
            <li class="cta-button"><a class="button" href="/article/add?id=<c:out value="${article.id}"/>">Buy Now</a></li>
        </c:if>
    </ul>
</c:forEach>

<%@ include file="endFoundation.jsp"%>
</body>
</html>
