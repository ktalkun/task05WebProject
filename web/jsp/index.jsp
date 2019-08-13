<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<html>
<u:head>
    <%--    TODO: user resource bundle--%>
    <title>Главная</title>
</u:head>
<body>
<u:header/>
<div id="promo">
    <img id="promo-background" src="<c:url value = "/img/promo.png"/>">
    <img id="promo-title" src="<c:url value = "/img/logo.png"/>">
</div>
<main>
    <section id="work-example-section">
        <div id="work-example-description">
            <p>Welcome to the</p>
            <h2>City Barber</h2>
            <p>
                Мы абсолютно уверены, что учитывая весь этот хаос повседневное
                жизни,
                должно быть какое-то место, куда мужчина может прийти, приятно
                поболтать с другими мужчинами,
                выпить стакан хорошего виски, а также позаботиться о волосах на
                лице
                и голове.
            </p>
        </div>
        <div id="work-example-preview">
            <img src="<c:url value = "/img/main/work-example-1.png"/>">
            <img src="<c:url value = "/img/main/work-example-2.png"/>">
            <img src="<c:url value = "/img/main/work-example-3.png"/>">
            <img src="<c:url value = "/img/main/work-example-4.png"/>">
            <img src="<c:url value = "/img/main/work-example-5.png"/>">
            <img src="<c:url value = "/img/main/work-example-6.png"/>">
        </div>
    </section>
    <section id="contact-us-section">
        <form action=" <c:url value="/index.jsp"/> ">
            <%--    TODO: user resource bundle--%>
            <h3>Связаться с нами</h3>
            <input required type="text" name="name" placeholder="Имя*"
                   pattern="[A-Za-zA-Яа-я\s]{5,30}">
            <input required type="email" name="email" placeholder="Почта*">
            <input required type="text" name="title" placeholder="Тема*"
                   pattern="[A-Za-zA-Яа-я\s]{10,120}">
            <textarea required rows="5" name="description"
                      placeholder="Комментарий*"></textarea>
            <input hidden name="isSent" value="sent">
            <button>Отправить</button>
        </form>
    </section>
</main>
<u:footer></u:footer>
</body>
</html>