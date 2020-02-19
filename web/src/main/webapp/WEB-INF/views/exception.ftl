<#import "/spring.ftl" as spring />
<#include "tags/head.ftl"/>
<#include "tags/header.ftl"/>
<#include "tags/footer.ftl"/>

<html>
<@head>
<#--TODO: user resource bundle-->
    <title>Исключение</title>
</@head>
<body>
<@header/>
<div id="promo">
    <img id="promo-background" src="<@spring.url "/resources/img/promo.png"/>">
    <img id="promo-title" src="<@spring.url "/resources/img/logo.png"/>">
</div>
<main>
    <section id="login-form-section" class="about-section">
        <div>
            <p>Exception message:</p>
            <#if exceptionStackTrace??>
                ${exceptionStackTrace}
            </#if>
        </div>
    </section>
</main>
<@footer></@footer>
</body>
</html>
