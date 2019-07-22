<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
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
    <img id="promo-logo" src="img/logo.png">
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
            <img src="img/main/work-example-1.png">
            <img src="img/main/work-example-2.png">
            <img src="img/main/work-example-3.png">
            <img src="img/main/work-example-4.png">
            <img src="img/main/work-example-5.png">
            <img src="img/main/work-example-6.png">
        </div>
    </section>
    <section id="contact-us-section">
        <form>
            <%--    TODO: user resource bundle--%>
            <h3>Связаться с нами</h3>
            <input type="text" placeholder="Имя*">
            <input type="email" placeholder="Почта*">
            <input type="text" placeholder="Тема*">
            <textarea rows="5" placeholder="Комментарий*"></textarea>
            <button>Отправить</button>
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