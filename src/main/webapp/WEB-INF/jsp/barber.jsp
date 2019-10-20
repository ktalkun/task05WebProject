<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<c:url value="/book.html" var="formHandler"/>
<html>
<u:head>
    <%--    TODO: user resource bundle--%>
    <title>Барберы</title>
</u:head>
<body>
<u:header/>
<div id="promo">
    <img id="promo-background" src="resources/img/promo.png">
    <h1 id="promo-title">Запись</h1>
</div>
<main>
    <%--    TODO: user resource bundle--%>
    <h2 id="barber-choice-title">Выберите мастера</h2>
    <section id="barber-choice">
        <c:forEach items="${employees}" var="employee">
            <form action="${formHandler}">
                <input hidden name="employeeId" value="${employee.id}">
                <img src="<c:url value="${employee.imagePath}"/>">
                <div>
                    <p>${employee.name} ${employee.surname}</p>
                    <ul>
                        <li <c:if test="${employee.workWeek[0] == 1}">class="active-day"</c:if> >Пн</li>
                        <li <c:if test="${employee.workWeek[1] == 1}">class="active-day"</c:if> >Вт</li>
                        <li <c:if test="${employee.workWeek[2] == 1}">class="active-day"</c:if> >Ср</li>
                        <li <c:if test="${employee.workWeek[3] == 1}">class="active-day"</c:if> >Чт</li>
                        <li <c:if test="${employee.workWeek[4] == 1}">class="active-day"</c:if> >Пт</li>
                        <li <c:if test="${employee.workWeek[5] == 1}">class="active-day"</c:if> >Сб</li>
                        <li <c:if test="${employee.workWeek[6] == 1}">class="active-day"</c:if> >Вс</li>
                    </ul>
                    <div class="barber-social">
                        <a href="${employee.socialRef.get("im")}"><i class="fab fa-instagram"></i></a>
                        <a href="${employee.socialRef.get("fb")}"><i class="fab fa-facebook-f"></i></a>
                        <a href="${employee.socialRef.get("vk")}"><i class="fab fa-vk"></i></a>
                    </div>
                    <button>Выбрать</button>
                </div>
            </form>
        </c:forEach>
    </section>
    <ul id="pagination">
        <c:forEach begin="1" end="${pageNumber}" var="i">
            <c:choose>
                <c:when test="${currentPage == i}">
                    <li id="current-page">${i}</li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value="?page=${i}"/>">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
</main>
<u:footer></u:footer>
</body>
</html>