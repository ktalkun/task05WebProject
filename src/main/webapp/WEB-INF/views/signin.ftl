<#import "/spring.ftl" as spring />
<#include "tags/head.ftl"/>
<#include "tags/header.ftl"/>
<#include "tags/footer.ftl"/>

<html>
<@head>
    <#--TODO: user resource bundle-->
    <title>Регистриция</title>
</@head>
<body>
<@header/>
<div id="promo">
    <img id="promo-background" src="<@spring.url "/resources/img/promo.png"/>">
    <img id="promo-title" src="<@spring.url "/resources/img/logo.png"/>">
</div>
<main>
    <section id="login-form-section">
        <form action="<@spring.url "/signin.html"/>" method="POST">
            <#--TODO: user resource bundle-->
            <h1>Регистрация</h1>
            <input required type="text" id="login" name="surname"
                   placeholder="Фамилия*"
                   pattern="[A-Za-zA-Яа-я]{5,30}">
            <input required type="text" id="login" name="name" placeholder="Имя*"
                   pattern="[A-Za-zA-Яа-я]{5,30}">
            <input required type="text" id="login" name="patronymic"
                   placeholder="Отчество*"
                   pattern="[A-Za-zA-Яа-я]{5,30}">
            <input required type="email" name="email" placeholder="Почта*">
            <input required type="text" name="phone"
                   placeholder="Телефон* +375_________"
                   pattern="[0-9]{2}[0-9]{3}[0-9]{2}[0-9]{2}">
            <input required type="text" id="login" name="login" placeholder="Логин*"
                   pattern="[A-Za-z]{5,20}">
            <#--TODO: user resource bundle-->
            <input required type="password" id="password" name="password"
                   placeholder="Пароль*"
                   pattern="(?=.*[0-9])(?=.*[!@#$%^&*\/])[a-zA-Z0-9!@#$%^&\/*]{6,30}">
            <input required type="password" id="repeatPassword" name="repeatPassword"
                   placeholder="Повторить пароль**"
                   pattern="(?=.*[0-9])(?=.*[!@#$%^&*\/])[a-zA-Z0-9!@#$%^&\/*]{6,30}">
            <button type="submit">Зарегистрироваться</button>
        </form>
    </section>
</main>
<@footer></@footer>
</body>
</html>