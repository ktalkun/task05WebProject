<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<html>
<u:head>
    <%--    TODO: user resource bundle--%>
    <title>Запись</title>
</u:head>
<body>
<u:header/>
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
<u:footer></u:footer>
</body>
</html>