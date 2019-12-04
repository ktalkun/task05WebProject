<#import "/spring.ftl" as spring />
<#include "tags/head.ftl"/>
<#include "tags/header.ftl"/>
<#include "tags/footer.ftl"/>

<html>
<@head>
    <#--TODO: user resource bundle-->
    <title>Регистрация услуги</title>
    <!-- EPAM UUI Styles Select -->
    <link rel="stylesheet" href="<@spring.url "/resources/uui/css/lib/components/bootstrap-select.min.css"/>"/>
    <script src="<@spring.url "/resources/uui/js/lib/components/bootstrap-select.min.js"/>"></script>
    <script src="<@spring.url "/resources/uui/js/uui-dropdown.min.js"/>"></script>
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
            <select required name="offer" class="selectpicker uui-form-element large">
                <#list offers as offer>
                    <option <#if RequestParameters.offerId?? && RequestParameters.offerId == "${offer.id}">selected</#if> value="${offer.id}">${offer.name}</option>
                </#list>
            </select>
            <select required name="employee" class="selectpicker uui-form-element large">
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
<@footer>
    <script>
        $('.selectpicker').uui_dropdown();
    </script>
</@footer>
</body>
</html>