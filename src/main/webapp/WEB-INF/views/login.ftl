<#import "/spring.ftl" as spring />
<#include "tags/head.ftl"/>
<#include "tags/header.ftl"/>
<#include "tags/footer.ftl"/>

<html>
<@head>
<#--TODO: user resource bundle-->
    <title>Войти</title>
</@head>
<body>
<@header/>
<div id="promo">
    <img id="promo-background" src="<@spring.url "/resources/img/promo.png"/>">
    <img id="promo-title" src="<@spring.url "/resources/img/logo.png"/>">
</div>
<main>
    <section id="login-form-section">
        <form action="<@spring.url "/login.html"/>" method="POST" accept-charset="UTF-8">
        <#--TODO: user resource bundle-->
            <h1>Войти</h1>
        <#--TODO: user resource bundle-->
            <input required type="text" id="login" name="login"
                   placeholder="Имя*"
                   pattern="[A-Za-z]{5,20}">
        <#--TODO: user resource bundle-->
            <input required type="password" id="password" name="password"
                   placeholder="Пароль*"
                   pattern="(?=.*[0-9])(?=.*[!@#$%^&*\/])[a-zA-Z0-9!@#$%^&\/*]{6,30}">
            <button type="submit">Войти</button>
            <a href="<@spring.url "/signin.html"/>">Регистрация</a>
        </form>
    </section>
</main>
<@footer></@footer>
</body>
</html>