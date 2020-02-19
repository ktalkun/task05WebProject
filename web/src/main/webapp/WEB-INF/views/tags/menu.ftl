<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#import "/spring.ftl" as spring />
<#macro menu>
    <@security.authorize access="isAuthenticated()">
    <ul id="additional-menu" class="profile-links">
        <#list profileMenu as item>
            <li>
                <a href="<@spring.url "${item.url}"/>">
                    <i class="${item.icon}"></i>${item.name}
                </a>
            </li>
        </#list>
        <#nested>
    </ul>
    </@security.authorize>
</#macro>