<%@tag language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty authorizedUser}">
    <ul id="additional-menu">
        <c:forEach items="${profileMenu}" var="item">
            <li><a href="<c:url value = "${item.url}"/>"><i class="${item.icon}"></i>${item.name}</a></li>
        </c:forEach>
        <jsp:doBody/>
    </ul>
</c:if>
