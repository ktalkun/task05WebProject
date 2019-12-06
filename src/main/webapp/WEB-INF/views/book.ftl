<#import "/spring.ftl" as spring />
<#include "tags/head.ftl"/>
<#include "tags/header.ftl"/>
<#include "tags/footer.ftl"/>

<html>
<@head>
    <#--TODO: user resource bundle-->
    <title>Регистрация услуги</title>
    <!-- EPAM UUI Select -->
    <link rel="stylesheet" href="<@spring.url "/resources/uui/css/lib/components/bootstrap-select.min.css"/>"/>
    <script src="<@spring.url "/resources/uui/js/lib/components/bootstrap-select.min.js"/>"></script>
    <script src="<@spring.url "/resources/uui/js/uui-dropdown.min.js"/>"></script>
    <!-- EPAM UUI TimePicker -->
    <link rel="stylesheet/less" type="text/css" href="<@spring.url "/resources/uui/css/lib/components/timepicker.less"/>"/>
    <script src="<@spring.url "/resources/uui/js/lib/components/bootstrap-timepicker.js"/>"></script>
    <script src="<@spring.url "/resources/uui/js/uui-timepicker.min.js"/>"></script>
`   <!-- EPAM UUI DatePicker -->
    <link rel="stylesheet" href="<@spring.url "/resources/uui/css/lib/components/datepicker3.css"/>"/>
    <script src="<@spring.url "/resources/uui/js/lib/components/bootstrap-datepicker.js"/>"></script>
    <script src="<@spring.url "/resources/uui/js/uui-datepicker.min.js"/>"></script>
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
            <div class="uui-datepicker book-datepicker">
                <input required type="text" name="date" id="datepicker" class="uui-form-element" placeholder="MM/DD/YYYY"/>
            </div>
            <div class="uui-timepicker book-timepicker">
                <input required type="text" name="time" id="timepicker" class="uui-form-element"/>
            </div>
            <button type="submit" class="uui-button transparent large">Зарегистрировать</button>
        </form>
    </section>
</main>
<@footer>
    <script>
        // Enable select
        $('.selectpicker').uui_dropdown();
        // Enable time picker
        $('#timepicker').uui_timepicker();
        // Enable date picker
        $('#datepicker').uui_datepicker({ todayHighlight: true });
    </script>
    </script>
</@footer>
</body>
</html>