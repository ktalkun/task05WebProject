<#import "/spring.ftl" as spring />
<#macro menu>
    <#if authorizedUser??>
    <ul id="additional-menu">
        <#list profileMenu as item>
            <li>
                <a href="<@spring.url "${item.url}"/>">
                    <i class="${item.icon}"></i>${item.name}
                </a>
            </li>
        </#list>
        <#nested>
    </ul>
    </#if>
</#macro>