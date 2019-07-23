<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--    TODO: user resource bundle--%>
    <title>Запись</title>
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
    <h1 id="promo-title">Запись</h1>
</div>
<main>
    <%--    TODO: user resource bundle--%>
    <h2 id="barber-choice-title">Выберите мастера</h2>
    <section id="barber-choice">
        <form action="calendar.jsp">
            <input hidden name="barber" value="daniilzahar">
            <img src="img/book/staff-1.png">
            <div>
                <p>Даниил Захаров</p>
                <ul>
                    <li class="active-day">Пн</li>
                    <li class="active-day">Вт</li>
                    <li>Ср</li>
                    <li class="active-day">Чт</li>
                    <li class="active-day">Пт</li>
                    <li>Сб</li>
                    <li>Вс</li>
                </ul>
                <div class="barber-social">
                    <a href=""><i class="fab fa-instagram"></i></a>
                    <a href=""><i class="fab fa-facebook-f"></i></a>
                    <a href=""><i class="fab fa-vk"></i></a>
                </div>
                <button>Выбрать</button>
            </div>
        </form>
        <form action="calendar.jsp">
            <input hidden name="barber" value="sergeyjuk">
            <img src="img/book/staff-2.png">
            <div>
                <p>Сергей Жук</p>
                <ul>
                    <li>Пн</li>
                    <li class="active-day">Вт</li>
                    <li class="active-day">Ср</li>
                    <li class="active-day">Чт</li>
                    <li class="active-day">Пт</li>
                    <li>Сб</li>
                    <li>Вс</li>
                </ul>
                <div class="barber-social">
                    <a href=""><i class="fab fa-instagram"></i></a>
                    <a href=""><i class="fab fa-facebook-f"></i></a>
                    <a href=""><i class="fab fa-vk"></i></a>
                </div>
                <button>Выбрать</button>
            </div>
        </form>
        <form action="calendar.jsp">
            <input hidden name="barber" value="anastasykalin">
            <img src="img/book/staff-3.png">
            <div>
                <p>Анастасия Калинина</p>
                <ul>
                    <li class="active-day">Пн</li>
                    <li class="active-day">Вт</li>
                    <li>Ср</li>
                    <li class="active-day">Чт</li>
                    <li class="active-day">Пт</li>
                    <li>Сб</li>
                    <li>Вс</li>
                </ul>
                <div class="barber-social">
                    <a href=""><i class="fab fa-instagram"></i></a>
                    <a href=""><i class="fab fa-facebook-f"></i></a>
                    <a href=""><i class="fab fa-vk"></i></a>
                </div>
                <button>Выбрать</button>
            </div>
        </form>
        <form action="calendar.jsp">
            <input hidden name="barber" value="andreyponama">
            <img src="img/book/staff-4.png">
            <div>
                <p>Андрей Понаморёв</p>
                <ul>
                    <li class="active-day">Пн</li>
                    <li class="active-day">Вт</li>
                    <li>Ср</li>
                    <li class="active-day">Чт</li>
                    <li class="active-day">Пт</li>
                    <li>Сб</li>
                    <li>Вс</li>
                </ul>
                <div class="barber-social">
                    <a href=""><i class="fab fa-instagram"></i></a>
                    <a href=""><i class="fab fa-facebook-f"></i></a>
                    <a href=""><i class="fab fa-vk"></i></a>
                </div>
                <button>Выбрать</button>
            </div>
        </form>
        <form action="calendar.jsp">
            <input hidden name="barber" value="artemezerskiy">
            <img src="img/book/staff-5.png">
            <div>
                <p>Артём Езерский</p>
                <ul>
                    <li>Пн</li>
                    <li class="active-day">Вт</li>
                    <li class="active-day">Ср</li>
                    <li class="active-day">Чт</li>
                    <li class="active-day">Пт</li>
                    <li>Сб</li>
                    <li>Вс</li>
                </ul>
                <div class="barber-social">
                    <a href=""><i class="fab fa-instagram"></i></a>
                    <a href=""><i class="fab fa-facebook-f"></i></a>
                    <a href=""><i class="fab fa-vk"></i></a>
                </div>
                <button>Выбрать</button>
            </div>
        </form>
        <form action="calendar.jsp">
            <input hidden name="barber" value="maryradin">
            <img src="img/book/staff-6.png">
            <div>
                <p>Мария Радина</p>
                <ul>
                    <li class="active-day">Пн</li>
                    <li class="active-day">Вт</li>
                    <li>Ср</li>
                    <li class="active-day">Чт</li>
                    <li class="active-day">Пт</li>
                    <li>Сб</li>
                    <li>Вс</li>
                </ul>
                <div class="barber-social">
                    <a href=""><i class="fab fa-instagram"></i></a>
                    <a href=""><i class="fab fa-facebook-f"></i></a>
                    <a href=""><i class="fab fa-vk"></i></a>
                </div>
                <button>Выбрать</button>
            </div>
        </form>
        <form action="calendar.jsp">
            <input hidden name="barber" value="antonfilatov">
            <img src="img/book/staff-7.png">
            <div>
                <p>Антон Филатов</p>
                <ul>
                    <li>Пн</li>
                    <li class="active-day">Вт</li>
                    <li class="active-day">Ср</li>
                    <li class="active-day">Чт</li>
                    <li class="active-day">Пт</li>
                    <li>Сб</li>
                    <li>Вс</li>
                </ul>
                <div class="barber-social">
                    <a href=""><i class="fab fa-instagram"></i></a>
                    <a href=""><i class="fab fa-facebook-f"></i></a>
                    <a href=""><i class="fab fa-vk"></i></a>
                </div>
                <button>Выбрать</button>
            </div>
        </form>
        <form action="calendar.jsp">
            <input hidden name="barber" value="mihailaugust">
            <img src="img/book/staff-8.png">
            <div>
                <p>Михаил Август</p>
                <ul>
                    <li>Пн</li>
                    <li class="active-day">Вт</li>
                    <li class="active-day">Ср</li>
                    <li class="active-day">Чт</li>
                    <li class="active-day">Пт</li>
                    <li>Сб</li>
                    <li>Вс</li>
                </ul>
                <div class="barber-social">
                    <a href=""><i class="fab fa-instagram"></i></a>
                    <a href=""><i class="fab fa-facebook-f"></i></a>
                    <a href=""><i class="fab fa-vk"></i></a>
                </div>
                <button>Выбрать</button>
            </div>
        </form>
        <form action="calendar.jsp">
            <input hidden name="barber" value="daryanova">
            <img src="img/book/staff-9.png">
            <div>
                <p>Дарья Новацкая</p>
                <ul>
                    <li class="active-day">Пн</li>
                    <li class="active-day">Вт</li>
                    <li>Ср</li>
                    <li class="active-day">Чт</li>
                    <li class="active-day">Пт</li>
                    <li>Сб</li>
                    <li>Вс</li>
                </ul>
                <div class="barber-social">
                    <a href=""><i class="fab fa-instagram"></i></a>
                    <a href=""><i class="fab fa-facebook-f"></i></a>
                    <a href=""><i class="fab fa-vk"></i></a>
                </div>
                <button>Выбрать</button>
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