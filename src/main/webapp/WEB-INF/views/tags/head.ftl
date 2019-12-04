<#import "/spring.ftl" as spring />
<#macro head>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="favicon.ico">
    <link rel="stylesheet" href="<@spring.url "/resources/css/fontawesome.min.css"/>">
    <link rel="stylesheet" href="<@spring.url "/resources/css/light.min.css"/>">
    <link rel="stylesheet" href="<@spring.url "/resources/css/brands.min.css"/>">
    <link rel="stylesheet" href="<@spring.url "/resources/css/style.css"/>">
    <!-- jQuery Core -->
    <script src="<@spring.url "/resources/uui/js/lib/jquery-1.12.0.min.js"/>"></script>
    <!-- EPAM UUI Styles Core -->
    <link rel="stylesheet" href="<@spring.url "/resources/uui/css/uui-all.css"/>"/>
    <#nested>
</head>
</#macro>
