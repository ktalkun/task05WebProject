<%@tag language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<header>
    <img src="<c:url value = "/img/short-logo.png"/>">
    <%--    TODO: user resource bundle--%>
    <div id="nav-bar">
        <ul>
            <li><a href="<c:url value = "/index.jsp"/>">Главная</a></li>
            <li><a href="<c:url value = "/service.jsp"/>">Услуги</a></li>
            <li><a href="<c:url value = "/book.jsp"/>">Запись</a></li>
            <li><a href="<c:url value = "/staff.jsp"/>">Сотрудники</a></li>
            <li><a href="<c:url value = "/about_us.jsp"/>">О нас</a></li>
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
                            <a href="<c:url value = "/logout.jsp"/>">Выход</a>
                        </li>
                    </u:menu>
                </div>
            </c:when>
            <c:otherwise>
                <a id="log-ref" href="<c:url value = "/login.jsp"/>">Log in</a>
            </c:otherwise>
        </c:choose>
    </div>
</header>