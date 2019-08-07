<%@tag language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<footer>
    <div id="footer-logo">
        <img src="<c:url value = "/img/short-logo.png"/>">
    </div>
    <%--    TODO: make autoupdate--%>
    <p id="copyright">Copyright CityBarber, 2019</p>

    <jsp:doBody/>
    <script src="<c:url value="/js/view.js"/>"></script>
</footer>