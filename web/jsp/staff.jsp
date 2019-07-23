<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--    TODO: user resource bundle--%>
    <title>Сотрудники</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="favicon.ico">
    <link rel="stylesheet" href="css/fontawesome.min.css">
    <link rel="stylesheet" href="css/light.min.css">
    <link rel="stylesheet" href="css/brands.min.css">
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
    <h1 id="promo-title">Сотрудники</h1>
</div>
<main>
    <section id="staff-list">
        <form action="profile.jsp">
            <input hidden name="barber" value="daniilzahar">
            <img src="img/book/staff-1.png">
            <div>
                <div class="staff-list-description">
                    <p>Даниил Захаров</p>
                    <i class="fal fa-star"></i>
                    <span>3.25</span>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <button><i class="fal fa-arrow-up"></i></button>
                    <a href=""><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </form>
        <form action="profile.jsp">
            <input hidden name="barber" value="sergeyjuk">
            <img src="img/book/staff-2.png">
            <div>
                <div class="staff-list-description">
                    <p>Сергей Жук</p>
                    <i class="fal fa-star"></i>
                    <span>3.12</span>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <button><i class="fal fa-arrow-up"></i></button>
                    <a href=""><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </form>
        <form action="profile.jsp">
            <input hidden name="barber" value="anastasykalin">
            <img src="img/book/staff-3.png">
            <div>
                <div class="staff-list-description">
                    <p>Анастасия Калинина</p>
                    <i class="fal fa-star"></i>
                    <span>2.18</span>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <button><i class="fal fa-arrow-up"></i></button>
                    <a href=""><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </form>
        <form action="profile.jsp">
            <input hidden name="barber" value="andreyponama">
            <img src="img/book/staff-4.png">
            <div>
                <div class="staff-list-description">
                    <p>Андрей Понаморёв</p>
                    <i class="fal fa-star"></i>
                    <span>3.12</span>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <button><i class="fal fa-arrow-up"></i></button>
                    <a href=""><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </form>
        <form action="profile.jsp">
            <input hidden name="barber" value="artemezerskiy">
            <img src="img/book/staff-5.png">
            <div>
                <div class="staff-list-description">
                    <p>Артём Езерский</p>
                    <i class="fal fa-star"></i>
                    <span>4.15</span>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <button><i class="fal fa-arrow-up"></i></button>
                    <a href=""><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </form>
        <form action="profile.jsp">
            <input hidden name="barber" value="maryradin">
            <img src="img/book/staff-6.png">
            <div>
                <div class="staff-list-description">
                    <p>Мария Радина</p>
                    <i class="fal fa-star"></i>
                    <span>1.02</span>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <button><i class="fal fa-arrow-up"></i></button>
                    <a href=""><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </form>
        <form action="profile.jsp">
            <input hidden name="barber" value="antonfilatov">
            <img src="img/book/staff-7.png">
            <div>
                <div class="staff-list-description">
                    <p>Антон Филатов</p>
                    <i class="fal fa-star"></i>
                    <span>4.00</span>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <button><i class="fal fa-arrow-up"></i></button>
                    <a href=""><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </form>
        <form action="profile.jsp">
            <input hidden name="barber" value="mihailaugust">
            <img src="img/book/staff-8.png">
            <div>
                <div class="staff-list-description">
                    <p>Михаил Август</p>
                    <i class="fal fa-star"></i>
                    <span>2.05</span>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <button><i class="fal fa-arrow-up"></i></button>
                    <a href=""><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </form>
        <form action="profile.jsp">
            <input hidden name="barber" value="daryanova">
            <img src="img/book/staff-9.png">
            <div>
                <div class="staff-list-description">
                    <p>Дарья Новацкая</p>
                    <i class="fal fa-star"></i>
                    <span>1.39</span>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <button><i class="fal fa-arrow-up"></i></button>
                    <a href=""><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </form>
    </section>
</main>
<footer>
    <div id="footer-logo">
        <img src="img/short-logo.png">
    </div>
    <%--    TODO: make autoupdate--%>
    <p id="copyright">Copyright CityBarber, 2019</p>
</footer>
</body>
</html>