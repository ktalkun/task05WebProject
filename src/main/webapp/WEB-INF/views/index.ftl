<#import "/spring.ftl" as spring />
<#include "tags/head.ftl"/>
<#include "tags/header.ftl"/>
<#include "tags/footer.ftl"/>

<html>
<@head>
    <#--TODO: user resource bundle-->
    <title>Главная</title>
</@head>
<body>
<@header/>
<div id="promo">
    <img id="promo-background" src="<@spring.url "/resources/img/promo.png"/>">
    <img id="promo-title" src="<@spring.url "/resources/img/logo.png"/>">
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
            <img src="<@spring.url "/resources/img/main/work-example-1.png"/>">
            <img src="<@spring.url "/resources/img/main/work-example-2.png"/>">
            <img src="<@spring.url "/resources/img/main/work-example-3.png"/>">
            <img src="<@spring.url "/resources/img/main/work-example-4.png"/>">
            <img src="<@spring.url "/resources/img/main/work-example-5.png"/>">
            <img src="<@spring.url "/resources/img/main/work-example-6.png"/>">
        </div>
    </section>
    <section id="contact-us-section">
        <form action="<@spring.url "/index.html"/>" accept-charset="utf-8">
        <#--TODO: user resource bundle-->
        <h3>Связаться с нами</h3>
        <input required type="text" name="name" placeholder="Имя*"
               pattern="[A-Za-zA-Яа-я\s]{5,30}" class="uui-form-element large">
        <input required type="email" name="email" placeholder="Почта*" class="uui-form-element large">
        <input required type="text" name="title" placeholder="Тема*"
               pattern="[A-Za-zA-Яа-я\s]{10,120}" class="uui-form-element large">
        <textarea required rows="5" name="description"
                  placeholder="Комментарий*" class="uui-form-element"></textarea>
        <button class="uui-button large dark-gray">Отправить</button>
        </form>
    </section>
</main>
<@footer></@footer>
</body>
</html>