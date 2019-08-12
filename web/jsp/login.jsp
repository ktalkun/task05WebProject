<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<u:head>
    <%--    TODO: user resource bundle--%>
    <title>Войти</title>
</u:head>
<body>
<u:header/>
<div id="promo">
    <img id="promo-background" src="img/promo.png">
    <img id="promo-title" src="img/logo.png">
</div>
<main>
    <section id="login-form-section">
        <form action="<c:url value="/login.jsp"/>" method="POST" accept-charset="UTF-8">
            <c:if test="${message != null}">
                <div id="message">${message}</div>
            </c:if>
            <%--    TODO: user resource bundle--%>
            <h1>Войти</h1>
            <%--    TODO: user resource bundle--%>
            <input required type="text" id="login" name="login" placeholder="Имя*"
                   pattern="[A-Za-z]{5,20}">
            <%--    TODO: user resource bundle--%>
            <input required type="password" id="password" name="password"
                   placeholder="Пароль*"
                   pattern="(?=.*[0-9])(?=.*[!@#$%^&*\/])[a-zA-Z0-9!@#$%^&\/*]{6,30}">
            <input hidden name="isSent" value="sent">
            <button type="submit">Войти</button>
            <a href="<c:url value="/signin.jsp"/> ">Регистрация</a>
        </form>
    </section>
</main>
<u:footer></u:footer>
</body>
</html>