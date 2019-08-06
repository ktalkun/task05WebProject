<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--    TODO: user resource bundle--%>
    <title>Главная</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="favicon.ico">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <img src="img/short-logo.png">
    <%--    TODO: user resource bundle--%>
    <div id="nav-bar">
        <ul>
            <li><a href="index.jsp">Главная</a></li>
            <li><a href="service.jsp">Услуги</a></li>
            <li><a href="book.jsp">Запись</a></li>
            <li><a href="staff.jsp">Сотрудники</a></li>
            <li><a href="about_us.jsp">О нас</a></li>
        </ul>
        <a id="log-ref">Log in</a>
    </div>
</header>
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
<footer>
    <div id="footer-logo" >
        <img src="img/short-logo.png">
    </div>
    <%--    TODO: make autoupdate--%>
    <p id="copyright">Copyright CityBarber, 2019</p>
</footer>
</body>
</html>