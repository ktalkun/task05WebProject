<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<u:head>
    <%--    TODO: user resource bundle--%>
    <title>Регистрация услуги</title>
</u:head>
<body>
<u:header/>
<div id="promo">
    <img id="promo-background" src="img/promo.png">
    <img id="promo-title" src="img/logo.png">
</div>
<main>
    <section id="login-form-section" class="book-panel">
        <form action="<c:url value="/book.jsp"/>" method="POST" accept-charset="UTF-8">
            <select required name="offer">
                <c:forEach items="${offers}" var="offer">
                    <option <c:if test="${param.offerId == offer.id}">selected</c:if> value="${offer.id}">${offer.name}</option>
                </c:forEach>
            </select>
            <select required name="employee">
                <c:forEach items="${employees}" var="employee">
                    <option <c:if test="${param.employeeId == employee.id}">selected</c:if> value="${employee.id}">${employee.name} ${employee.surname}</option>
                </c:forEach>
            </select>
            <input required type="date" name="date">
            <input required type="time" name="time">
            <input hidden name="isSent" value="sent">
            <button type="submit">Зарегистрировать</button>
        </form>
    </section>
</main>
<u:footer></u:footer>
</body>
</html>