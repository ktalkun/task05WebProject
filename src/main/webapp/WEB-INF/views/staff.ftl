<#import "/spring.ftl" as spring />
<#include "tags/head.ftl"/>
<#include "tags/header.ftl"/>
<#include "tags/footer.ftl"/>

<html>
<@head>
<#--TODO: user resource bundle-->
    <title>Сотрудники</title>
</@head>
<body>
<@header/>
<div id="promo">
    <img id="promo-background" src="<@spring.url "/resources/img/promo.png"/>">
    <h1 id="promo-title">Сотрудники</h1>
</div>
<main>
    <section id="staff-list">
        <form action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="daniilzahar">
            <img src="<@spring.url "/resources/img/book/staff-1.png"/>">
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
        <form action="<@spring.url "/profile.html"/>">
            <input hidden name="barber" value="sergeyjuk">
            <img src="<@spring.url "/resources/img/book/staff-2.png"/>">
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
        <form action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="anastasykalin">
            <img src="<@spring.url "/resources/img/book/staff-3.png"/>">
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
        <form action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="andreyponama">
            <img src="<@spring.url "resources/img/book/staff-4.png"/>">
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
        <form action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="artemezerskiy">
            <img src="<@spring.url "/resources/img/book/staff-5.png"/>">
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
        <form action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="maryradin">
            <img src="<@spring.url "/resources/img/book/staff-6.png"/>">
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
        <form action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="antonfilatov">
            <img src="<@spring.url "/resources/img/book/staff-7.png"/>">
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
        <form action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="mihailaugust">
            <img src="<@spring.url "/resources/img/book/staff-8.png"/>">
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
        <form action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="daryanova">
            <img src="<@spring.url "/resources/img/book/staff-9.png"/>">
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
<@footer></@footer>
</body>
</html>