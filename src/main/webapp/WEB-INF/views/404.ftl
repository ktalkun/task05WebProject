<#import "/spring.ftl" as spring />
<#include "tags/head.ftl"/>
<#include "tags/header.ftl"/>
<#include "tags/footer.ftl"/>

<html>
<@head>
<#--TODO: user resource bundle-->
    <title>Error 404</title>
</@head>
<body>
<@header/>
<main id="error-page">
    <img src="<@spring.url "/resources/img/404.jpg"/>">
</main>
<@footer></@footer>
</body>
</html>