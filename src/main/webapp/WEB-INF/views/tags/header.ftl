<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#import "/spring.ftl" as spring />
<#include "menu.ftl"/>
<#macro header>
    <header>
        <div class="uui-header">
            <nav>
                <a href="<@spring.url "/"/>" class="brand-logo">
                <span class="logo">
                    <img src="<@spring.url "/resources/img/short-logo.svg"/>" alt=""/>
                </span>
                    Barber Shop
                </a>
                <ul class="uui-header-tools nav navbar-nav">
                    <li>
                        <ul>
                            <li><a href="<@spring.url "/index.html"/>">Главная</a></li>
                            <li><a href="<@spring.url "/service.html"/>">Услуги</a></li>
                            <li><a href="<@spring.url "/barber.html"/>">Барберы</a></li>
                            <li><a href="<@spring.url "/staff.html"/>">Сотрудники</a></li>
                            <li><a href="<@spring.url "/about.html"/>">О нас</a></li>
                        </ul>
                    </li>
                    <@security.authorize access="isAuthenticated()">
                    <li class="user-details">
                        <@security.authentication var="name" property='principal.name'></@security.authentication>
                        <@security.authentication var="surname" property='principal.surname'></@security.authentication>
                        <div>
                            <span>${name} ${surname}</span>
                        </div>
                    </li>
                    <li class="dropdown uui-profile-menu">
                            <a href="#" class="dropdown-toggle"
                               data-toggle="dropdown">
                                <div class="profile-photo">
                                    <@security.authentication var="imagePath" property='principal.imagePath'></@security.authentication>
                                    <img src="<@spring.url "/${imagePath}"/>" alt=""/>
                                </div>
                            </a>
                            <div class="dropdown-menu" role="menu">
                                <span class="menu-arrow"></span>
                                <@menu>
                                <li>
                                    <a href="<@spring.url "/logout.html"/>" class="logout"><i class="fa fa-sign-out"></i>Выход</a>
                                </li>
                                </@menu>
                            </div>
                    </li>
                    </@security.authorize>
                    <@security.authorize access="! isAuthenticated()">
                        <li class="uui-header-button"><a id="log-ref" href="<@spring.url "/login.html"/>" class="uui-button">Log in</a></li>
                    </@security.authorize>
                </ul>
            </nav>
        </div>
    </header>
</#macro>
