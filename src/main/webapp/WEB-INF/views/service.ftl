<#import "/spring.ftl" as spring />
<#include "tags/head.ftl"/>
<#include "tags/header.ftl"/>
<#include "tags/footer.ftl"/>

<html>
<@head>
<#--TODO: user resource bundle-->
    <title>Услуги</title>
</@head>
<body>
<@header/>
<div id="promo">
    <img id="promo-background" src="<@spring.url "/resources/img/promo.png"/>">
    <h1 id="promo-title">Услуги</h1>
</div>
<main>
    <#list mainOffers as mainOffer>
        <section class="main-service">
            <form action="<@spring.url "/book.html"/>" name="offer-${mainOffer.id}">
                <input hidden name="offerId" value="${mainOffer.id}">
                <div class="main-service-title">
                    <h2>${mainOffer.name}</h2>
                    <h3>${mainOffer.price?string["0"]} byn</h3>
                </div>
                <p>${mainOffer.description}</p>
                <#--TODO: user resource bundle-->
                <button>Записаться</button>
            </form>
            <img src="<@spring.url "/${mainOffer.imagePath}"/>">
        </section>
    </#list>
    <section id="additional-service">
        <#list additionalOffers as additionalOffer>
            <form action=" <@spring.url "/book.html"/>" name="offer-${additionalOffer.id}">
                <input hidden name="offerId" value="${additionalOffer.id}">
                <img src="<@spring.url "/${additionalOffer.imagePath}"/>">
                <div>
                    <h3>${additionalOffer.name}</h3>
                    <h4>${additionalOffer.price?string["0"]} byn</h4>
                </div>
                <button>Записаться</button>
            </form>
        </#list>
    </section>
</main>
<@footer></@footer>
</body>
</html>