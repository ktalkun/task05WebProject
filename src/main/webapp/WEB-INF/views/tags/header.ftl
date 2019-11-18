<#import "/spring.ftl" as spring />
<#include "menu.ftl"/>
<#macro header>
<header>
    <img src="<@spring.url "/resources/img/short-logo.png"/>">
<#--TODO: user resource bundle-->
    <div id="nav-bar">
        <ul>
            <li><a href="<@spring.url "/index.html"/>">Главная</a></li>
            <li><a href="<@spring.url "/service.html"/>">Услуги</a></li>
            <li><a href="<@spring.url "/barber.html"/>">Барберы</a></li>
            <li><a href="<@spring.url "/staff.html"/>">Сотрудники</a></li>
            <li><a href="<@spring.url "/about.html"/>">О нас</a></li>
        </ul>
            <#if authorizedUser??>
                <div id="user-header-nav">
                    <div>
                        <span>${authorizedUser.name} ${authorizedUser.surname}</span>
                        <img src="<@spring.url "/${authorizedUser.imagePath}"/>">
                        <i class="fal fa-angle-down"></i>
                    </div>
                    <@menu>
                        <li>
                            <a href="<@spring.url "/logout.html"/>">Выход</a>
                        </li>
                    </@menu>
                </div>
            <#else>
                <a id="log-ref" href="<@spring.url "/login.html"/>">Log in</a>
            </#if>
    </div>
</header>
</#macro>