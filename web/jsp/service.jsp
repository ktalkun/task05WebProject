<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<html>
<u:head>
    <%--    TODO: user resource bundle--%>
    <title>Услуги</title>
</u:head>
<body>
<u:header/>
<div id="promo">
    <img id="promo-background" src="img/promo.png">
    <h1 id="promo-title">Услуги</h1>
</div>
<main>
    <section class="main-service">
        <form action="service.jsp" name="traditional-haircut">
            <input hidden name="name" value="traditional-haircut">
            <div class="main-service-title">
                <h2>
                    Традиционная<br/>
                    стрижка
                </h2>
                <h3> 18 byn </h3>
            </div>
            <p>
                Эта классическая традиционная услуга идеально подходит,<br/>
                если вы хотите, чтобы ваши волосы были подстрижены
                правильно.<br/>
                Наши парикмахеры будут рады помочь вам с этим.
            </p>
            <%--    TODO: user resource bundle--%>
            <button>Записаться</button>
        </form>
        <img src="img/service/service-1.png">
    </section>
    <section class="main-service">
        <form action="service.jsp" name="beard-shave">
            <input hidden name="name" value="beard-shave">
            <div class="main-service-title">
                <h2>
                    Бритьё<br/>
                    бороды
                </h2>
                <h3> 16 byn </h3>
            </div>
            <p>
                Бритьё бороды - одна из наших самых популярных услуг.<br/>
                Это необходимость для всех мужчин,<br/>
                которые хотят иметь бороду, которая выглядит потрясающе.
            </p>
            <%--    TODO: user resource bundle--%>
            <button>Записаться</button>
        </form>
        <img src="img/service/service-2.png">
    </section>
    <section class="main-service">
        <form action="service.jsp" name="mustache-trimming">
            <input hidden name="name" value="mustache-trimming">
            <div class="main-service-title">
                <h2>
                    Подравнивание<br/>
                    усов
                </h2>
                <h3> 15 byn </h3>
            </div>
            <p>
                Усы - это классический выбор мужчин,<br/>
                и они никогда не выйдут из моды надолго.<br/>
                С нами вы можете сохранить свои усы ухоженными.
            </p>
            <%--    TODO: user resource bundle--%>
            <button>Записаться</button>
        </form>
        <img src="img/service/service-3.png">
    </section>
    <section class="main-service">
        <form action="service.jsp" name="beard-trim">
            <input hidden name="name" value="beard-trim">
            <div class="main-service-title">
                <h2>
                    Подравнивание<br/>
                    Бороды
                </h2>
                <h3> 15 byn </h3>
            </div>
            <p>
                Бритьё бороды - одна из наших самых популярных услуг.<br/>
                Это необходимость для всех мужчин,<br/>
                которые хотят иметь бороду, которая выглядит потрясающе.
            </p>
            <%--    TODO: user resource bundle--%>
            <button>Записаться</button>
        </form>
        <img src="img/service/service-4.png">
    </section>
    <section id="additional-service">
        <form action="service.jsp" name="hair-draw">
            <input hidden name="name" value="hair-draw">
            <img src="img/service/service-5.png">
            <div>
                <h3>Рисунок на волосах</h3>
                <h4>25 byn</h4>
            </div>
            <button>Записаться</button>
        </form>
        <form action="service.jsp" name="afro-braids">
            <input hidden name="name" value="afro-braids">
            <img src="img/service/service-6.png">
            <div>
                <h3>Рисунок на волосах</h3>
                <h4>27 byn</h4>
            </div>
            <button>Записаться</button>
        </form>
        <form action="service.jsp" name="beard-paint">
            <input hidden name="name" value="beard-paint">
            <img src="img/service/service-7.png">
            <div>
                <h3>Покраска бороды</h3>
                <h4>15 byn</h4>
            </div>
            <button>Записаться</button>
        </form>
        <form action="service.jsp" name="part-edge">
            <input hidden name="name" value="part-edge">
            <img src="img/service/service-8.png">
            <div>
                <h3>Пробор/Окантовка</h3>
                <h4>7 byn</h4>
            </div>
            <button>Записаться</button>
        </form>
        <form action="service.jsp" name="styling">
            <input hidden name="name" value="styling">
            <img src="img/service/service-9.png">
            <div>
                <h3>Пробор/Окантовка</h3>
                <h4>8 byn</h4>
            </div>
            <button>Записаться</button>
        </form>
        <form action="service.jsp" name="beard-tint">
            <input hidden name="name" value="beard-tint">
            <img src="img/service/service-10.png">
            <div>
                <h3>Тонирование бороды</h3>
                <h4>10 byn</h4>
            </div>
            <button>Записаться</button>
        </form>
    </section>
</main>
<u:footer></u:footer>
</body>
</html>