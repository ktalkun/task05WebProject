<#import "/spring.ftl" as spring />
<#include "tags/head.ftl"/>
<#include "tags/header.ftl"/>
<#include "tags/footer.ftl"/>

<html>
<@head>
    <#--TODO: user resource bundle-->
    <title>Регистрация услуги</title>
</@head>
<body>
<@header/>
<div id="promo">
    <img id="promo-background" src="<@spring.url "/resources/img/promo.png"/>">
    <img id="promo-title" src="<@spring.url "/resources/img/logo.png"/>">
</div>
<main>
    <section id="login-form-section" class="book-panel">
        <form action="<@spring.url "/book.html"/>" method="POST" accept-charset="UTF-8">
            <select required name="offer">
                <#list offers as offer>
                    <option <#if RequestParameters.offerId?? && RequestParameters.offerId == "${offer.id}">selected</#if> value="${offer.id}">${offer.name}</option>
                </#list>
            </select>
            <select required name="employee">
                <#list employees as employee>
                    <option <#if RequestParameters.employeeId?? &&  RequestParameters.employeeId == "${employee.id}">selected</#if> value="${employee.id}">${employee.name} ${employee.surname}</option>
                </#list>
            </select>
            <input required type="date" name="date">
            <input required type="time" name="time">
            <button type="submit">Зарегистрировать</button>
        </form>
    </section>
</main>
<@footer></@footer>
</body>
</html>