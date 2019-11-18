<#import "/spring.ftl" as spring />
<#include "tags/head.ftl"/>
<#include "tags/header.ftl"/>
<#include "tags/footer.ftl"/>

<html>
<@head>
    <#--TODO: user resource bundle-->
    <title>Message</title>
</@head>
<body>
<@header/>
<div id="promo">
    <img id="promo-background" src="<@spring.url "/resources/img/promo.png"/>">
    <img id="promo-title" src="<@spring.url "/resources/img/logo.png"/>">
</div>
<main>
    <section id="login-form-section">
        <#if message??>
            <div id="message-panel">
                <h1>Message</h1>
                <span>${message}</span>
                <a href="<@spring.url "${redirectUrl}"/>">Back</a>
            </div>
        </#if>
    </section>
</main>
<@footer></@footer>
</body>
</html>