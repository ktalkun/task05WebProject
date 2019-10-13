<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<u:head>
    <%--    TODO: user resource bundle--%>
    <title>Message</title>
</u:head>
<body>
<u:header/>
<div id="promo">
    <img id="promo-background" src="img/promo.png">
    <img id="promo-title" src="img/logo.png">
</div>
<main>
    <section id="login-form-section">
        <c:if test="${message != null}">
            <div id="message-panel">
                <h1>Message</h1>
                <span>${message}</span>
                <a href="<c:url value="${redirectUrl}"/> ">Back</a>
            </div>
        </c:if>
    </section>
</main>
<u:footer></u:footer>
</body>
</html>