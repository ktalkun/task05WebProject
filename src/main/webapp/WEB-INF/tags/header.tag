<%@tag language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<header>
    <img src="<c:url value = "/resources/img/short-logo.png"/>">
    <%--    TODO: user resource bundle--%>
    <div id="nav-bar">
        <ul>
            <li><a href="<c:url value = "/index.html"/>">Главная</a></li>
            <li><a href="<c:url value = "/service.html"/>">Услуги</a></li>
            <li><a href="<c:url value = "/barber.html"/>">Барберы</a></li>
            <li><a href="<c:url value = "/staff.html"/>">Сотрудники</a></li>
            <li><a href="<c:url value = "/about.html"/>">О нас</a></li>
        </ul>
        <c:choose>
            <c:when test="${authorizedUser != null}">
                <div id="user-header-nav">
                    <div>
                        <span>${authorizedUser.name} ${authorizedUser.surname}</span>
                        <img src="<c:url value = "/${authorizedUser.imagePath}"/>">
                        <i class="fal fa-angle-down"></i>
                    </div>
                    <u:menu>
                        <li>
                            <a href="<c:url value = "/logout.html"/>">Выход</a>
                        </li>
                    </u:menu>
                </div>
            </c:when>
            <c:otherwise>
                <a id="log-ref" href="<c:url value = "/login.html"/>">Log in</a>
            </c:otherwise>
        </c:choose>
    </div>
</header>