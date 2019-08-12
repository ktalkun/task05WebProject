<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<u:head>
    <%--    TODO: user resource bundle--%>
    <title>Error 404</title>
</u:head>
<body>
<u:header/>
<main id="error-page">
    <img src="<c:url value="/img/404.jpg"/>">
</main>
<u:footer></u:footer>
</body>
</html>