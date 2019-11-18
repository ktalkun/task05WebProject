<#import "/spring.ftl" as spring />
<#macro footer>
<footer>
    <div id="footer-logo">
        <img src="<@spring.url "/resources/img/short-logo.png"/>">
    </div>
<#--TODO: make autoupdate-->
    <p id="copyright">Copyright CityBarber, 2019</p>
    <#nested>
    <script src="<@spring.url "/resources/js/view.js"/>"></script>
</footer>
</#macro>