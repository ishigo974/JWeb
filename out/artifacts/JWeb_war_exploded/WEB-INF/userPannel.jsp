<%--
  Created by IntelliJ IDEA.
  User: lopes_n
  Date: 12/23/15
  Time: 5:59 PM
  To change this template use File | Settings | File Templates.
--%>
<nav class="top-bar" data-topbar role="navigation">
    <ul class="title-area">
        <li class="name">
            <h1><a href="/">Home</a></h1>
        </li>
        <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
        </li>
    </ul>

    <section class="top-bar-section">
        <!-- Right Nav Section -->
        <ul class="right show-for-large-up">
            <c:if test="${!empty sessionScope.userSession}">
                <li><a href="/article/add">${sessionScope.userCart.size()}<img style="height: 40px;" src="/assets/cart.png"></a></li>
            </c:if>
            <li class="active"><a href="/article">Products</a></li>
            <li class="has-dropdown not-click">
                <a href="#">Menu</a>
                <ul class="dropdown">
                    <c:choose>
                        <c:when test="${empty sessionScope.userSession}">
                            <li><a href="/login">Login</a></li>
                            <li class="active"><a href="/signup">Sign up</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/logout">Logout</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${!empty sessionScope.userSession && sessionScope.userSession.admin}">
                        <li class="active"><a href="/admin">Admin</a></li>
                    </c:if>

                </ul>
            </li>
        </ul>
    </section>
</nav>