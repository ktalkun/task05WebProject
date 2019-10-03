<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<u:head>
    <%--    TODO: user resource bundle--%>
    <title>О нас</title>
</u:head>
<body>
<u:header/>
<div id="promo">
    <img id="promo-background" src="img/promo.png">
    <img id="promo-title" src="img/logo.png">
</div>
<main>
    <section id="login-form-section" class="about-section">
        <div>
            <h1>Контактная информация</h1>
            <div class="about-unit">Адрес: ул. Городской Вал д. 10</div>
            <div class="about-unit">Телефон (velcom): +375(29)3151822</div>
            <div class="about-unit">Телефон (мтс): +375(33)3181022</div>
            <div class="about-unit">График работы: ПН-ПТ 9:00-21:00; СБ-ВС
                12:00-18:00
            </div>
        </div>
        <iframe src="https://www.google.com/maps/embed?pb=!1m23!1m12!1m3!1d4701.480159614772!2d27.546895429819884!3d53.9008236203958!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m8!3e6!4m0!4m5!1s0x46dbcfe99d7651b9%3A0x20b6e544f152807f!2z0YPQuy4g0JPQvtGA0L7QtNGB0LrQvtC5INCS0LDQuyAxMCwg0JzQuNC90YHQug!3m2!1d53.9008237!2d27.5512728!5e0!3m2!1sru!2sby!4v1565644612568!5m2!1sru!2sby"
                frameborder="0" style="border:0"
                allowfullscreen></iframe>
    </section>
</main>
<u:footer></u:footer>
</body>
</html>
