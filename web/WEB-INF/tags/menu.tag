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
<%--SELECT--%>
<%--reservations.id,--%>
<%--offers.id AS offer_id,--%>
<%--offers.name AS offer_name,--%>
<%--offers.description AS offer_description,--%>
<%--offers.price AS offer_price,--%>
<%--offers.period AS offer_period,--%>
<%--customers.id AS customer_id,--%>
<%--customers.login AS customer_login,--%>
<%--customers.password AS customer_password,--%>
<%--customers.name AS customer_name,--%>
<%--customers.surname AS customer_surname,--%>
<%--customers.patronymic AS customer_patronymic,--%>
<%--customers.email AS customer_email,--%>
<%--customers.phone AS customer_phone,--%>
<%--customers.image_path AS customer_image_path,--%>
<%--customers.role AS customer_role,--%>
<%--employees.id AS employee_id,--%>
<%--employees.login AS employee_login,--%>
<%--employees.password AS employee_password,--%>
<%--employees.name AS employee_name,--%>
<%--employees.surname AS employee_surname,--%>
<%--employees.patronymic AS employee_patronymic,--%>
<%--employees.email AS employee_email,--%>
<%--employees.phone AS employee_phone,--%>
<%--employees.image_path AS employee_image_path,--%>
<%--employees.role AS employee_role,--%>
<%--employees_info.experience AS employee_experience,--%>
<%--reservations.date--%>
<%--FROM reservations--%>
<%--JOIN offers ON offers.id = reservations.offer_id--%>
<%--JOIN users AS customers ON customers.id = reservations.customer_id--%>
<%--JOIN users AS employees ON employees.id = reservations.employee_id--%>
<%--JOIN employees AS employees_info ON employees.id = employees.id--%>
<%--WHERE reservations.customer_id = 2--%>
<%--GROUP BY offer_id;--%>