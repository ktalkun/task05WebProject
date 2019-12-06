<#import "/spring.ftl" as spring />
<#include "tags/head.ftl"/>
<#include "tags/header.ftl"/>
<#include "tags/footer.ftl"/>
<html>
<@head>
<#--TODO: user resource bundle-->
    <title>Сотрудники</title>
    <script src="<@spring.url "/resources/uui/js/uui-rating.min.js"/>"></script>
</@head>
<body>
<@header/>
<div id="promo">
    <img id="promo-background" src="<@spring.url "/resources/img/promo.png"/>">
    <h1 id="promo-title">Сотрудники</h1>
</div>
<main>
    <section id="staff-list">
        <div action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="daniilzahar">
            <img src="<@spring.url "/resources/upload/avatars/13.jpg"/>">
            <div>
                <div class="staff-list-description">
                    <p>Даниил Захаров</p>
                    <div class="uui-rating medium-gray"></div>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <a href="#" class="uui-button large transparent"><i class="fal fa-arrow-up"></i></a>
                    <a href="#" class="uui-button large transparent"><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </div>
        <div action="<@spring.url "/profile.html"/>">
            <input hidden name="barber" value="sergeyjuk">
            <img src="<@spring.url "/resources/upload/avatars/11.jpg"/>">
            <div>
                <div class="staff-list-description">
                    <p>Сергей Жук</p>
                    <div class="uui-rating medium-gray"></div>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <a href="#" class="uui-button large transparent"><i class="fal fa-arrow-up"></i></a>
                    <a href="#" class="uui-button large transparent"><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </div>
        <div action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="anastasykalin">
            <img src="<@spring.url "/resources/upload/avatars/73.jpg"/>">
            <div>
                <div class="staff-list-description">
                    <p>Анастасия Калинина</p>
                    <div class="uui-rating medium-gray"></div>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <a href="#" class="uui-button large transparent"><i class="fal fa-arrow-up"></i></a>
                    <a href="#" class="uui-button large transparent"><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </div>
        <div action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="andreyponama">
            <img src="<@spring.url "/resources/upload/avatars/20.jpg"/>">
            <div>
                <div class="staff-list-description">
                    <p>Андрей Понаморёв</p>
                    <div class="uui-rating medium-gray"></div>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <a href="#" class="uui-button large transparent"><i class="fal fa-arrow-up"></i></a>
                    <a href="#" class="uui-button large transparent"><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </div>
        <div action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="artemezerskiy">
            <img src="<@spring.url "/resources/upload/avatars/5.jpg"/>">
            <div>
                <div class="staff-list-description">
                    <p>Артём Езерский</p>
                    <div class="uui-rating medium-gray"></div>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <a href="#" class="uui-button large transparent"><i class="fal fa-arrow-up"></i></a>
                    <a href="#" class="uui-button large transparent"><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </div>
        <div action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="maryradin">
            <img src="<@spring.url "/resources/upload/avatars/70.jpg"/>">
            <div>
                <div class="staff-list-description">
                    <p>Мария Радина</p>
                    <div class="uui-rating medium-gray"></div>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <a href="#" class="uui-button large transparent"><i class="fal fa-arrow-up"></i></a>
                    <a href="#" class="uui-button large transparent"><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </div>
        <div action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="antonfilatov">
            <img src="<@spring.url "/resources/upload/avatars/7.jpg"/>">
            <div>
                <div class="staff-list-description">
                    <p>Антон Филатов</p>
                    <div class="uui-rating medium-gray"></div>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <a href="#" class="uui-button large transparent"><i class="fal fa-arrow-up"></i></a>
                    <a href="#" class="uui-button large transparent"><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </div>
        <div action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="mihailaugust">
            <img src="<@spring.url "/resources/upload/avatars/5.jpg"/>">
            <div>
                <div class="staff-list-description">
                    <p>Михаил Август</p>
                    <div class="uui-rating medium-gray"></div>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <a href="#" class="uui-button large transparent"><i class="fal fa-arrow-up"></i></a>
                    <a href="#" class="uui-button large transparent"><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </div>
        <div action="<@spring.url "/profile.jsp"/>">
            <input hidden name="barber" value="daryanova">
            <img src="<@spring.url "/resources/upload/avatars/66.jpg"/>">
            <div>
                <div class="staff-list-description">
                    <p>Дарья Новацкая</p>
                    <div class="uui-rating medium-gray"></div>
                </div>
                <blockquote>
                    "Я взял своего 3-летнего сына на его первую мужскую стрижку,
                    и теперь я рад, что у него такой фантастический вид.
                    Рекомендую любого из парикмахеров этого барбершопа, т.к.
                    верю, что моему сыну никогда не позволят покинуть кресло
                    без действительно красивой стрижки."
                </blockquote>
                <div class="staff-nav-bar">
                    <a href="#" class="uui-button large transparent"><i class="fal fa-arrow-up"></i></a>
                    <a href="#" class="uui-button large transparent"><i class="fal fa-comment-alt"></i></a>
                </div>
            </div>
        </div>
    </section>
</main>
<@footer>
    <script>
        $('.uui-rating').uui_rating();
    </script>
</@footer>
</body>
</html>
