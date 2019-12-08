<#import "/spring.ftl" as spring />
<#macro footer>
    <footer>
        <div class="uui-footer">
            <div class="footer-logo-copyright">
                <div class="epam-logo">
                    <img src="<@spring.url "/resources/uui/images/Logo_Epam_Color.svg"/>" alt="" />
                </div>
                <p class="copyright">© 2019 EPAM Systems. All Rights Reserved.</p>
            </div>
            <div class="footer-build">
                <p class="build">Build version v <span>2.0.0.1</span></p>
            </div>
        </div>
        <#nested>
        <script src="<@spring.url "/resources/js/view.js"/>"></script>
    </footer>
</#macro>