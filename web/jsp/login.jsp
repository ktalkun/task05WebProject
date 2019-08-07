<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

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
        <form action="login.jsp" method="POST">
            <%--    TODO: user resource bundle--%>
            <h1>Войти</h1>
            <%--    TODO: user resource bundle--%>
            <input type="text" id="login" name="login" placeholder="Имя*">
            <%--    TODO: user resource bundle--%>
            <input type="password" id="password" name="password"
                   placeholder="Пароль*">
            <button type="submit">Войти</button>
        </form>
    </section>
</main>
<u:footer></u:footer>
</body>
</html>