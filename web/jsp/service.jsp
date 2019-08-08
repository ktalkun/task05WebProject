<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>

<c:url value="/book.jsp" var="formHandler"/>
<html>
<u:head>
    <%--    TODO: user resource bundle--%>
    <title>Услуги</title>
</u:head>
<body>
<u:header/>
<div id="promo">
    <img id="promo-background" src="img/promo.png">
    <h1 id="promo-title">Услуги</h1>
</div>
<main>
    <c:forEach items="${mainOffers}" var="mainOffer">
        <section class="main-service">
            <form action="${formHandler}" name="offer-${mainOffer.id}">
                <input hidden name="name" value="${mainOffer.id}">
                <div class="main-service-title">
                    <h2>${mainOffer.name}</h2>
                    <h3><fmt:formatNumber value="${mainOffer.price}" maxFractionDigits="0"/> byn</h3>
                </div>
                <p>${mainOffer.description}</p>
                    <%--    TODO: user resource bundle--%>
                <button>Записаться</button>
            </form>
            <img src="<c:url value="${mainOffer.imagePath}"/> ">
        </section>
    </c:forEach>
    <section id="additional-service">
        <c:forEach items="${additionalOffers}" var="additionalOffer">
            <form action="{formHandler}" name="offer-${additionalOffer.id}">
                <input hidden name="name" value="${additionalOffer.id}">
                <img src="<c:url value="${additionalOffer.imagePath}"/>">
                <div>
                    <h3>${additionalOffer.name}</h3>
                    <h4><fmt:formatNumber value="${additionalOffer.price}" maxFractionDigits="0"/> byn</h4>
                </div>
                <button>Записаться</button>
            </form>
        </c:forEach>
    </section>
</main>
<u:footer></u:footer>
</body>
</html>