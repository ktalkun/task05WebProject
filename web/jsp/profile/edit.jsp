<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>

<html>
<u:head>
    <%--    TODO: user resource bundle--%>
    <title>Профиль</title>
</u:head>
<body>
<u:header/>
<main id="edit-main">
    <aside>

        <img onclick="uploadAvatar()"
             src="<c:url value = "/${authorizedUser.imagePath}"/>">
        <div id="profile-user-description">
            <form action="<c:url value="/profile/edit.jsp"/>" method="POST" enctype="multipart/form-data">
                <input onchange="choosenAvatar()" hidden id="avatarImage" name="avatarImage" type="file"
                       accept="image/jpeg,image/png">
                <span>Фамилия</span>
                <input required pattern="[A-Za-zA-Яа-я]{5,30}" onChange="editData()" type="text" disabled="disabled" name="surname"
                       value="${authorizedUser.surname}">
                <i onclick="editData(this.id)" class="fal fa-pen"
                   id="surname"></i>
                <span>Имя</span>
                <input required pattern="[A-Za-zA-Яа-я]{5,30}" type="text" disabled name="name"
                       value="${authorizedUser.name}">
                <i onclick="editData(this.id)" class="fal fa-pen" id="name"></i>
                <span>Отчество</span>
                <input required pattern="[A-Za-zA-Яа-я]{5,30}" type="text" disabled name="patronymic"
                       value="${authorizedUser.patronymic}">
                <i onclick="editData(this.id)" class="fal fa-pen"
                   id="patronymic"></i>
                <span>Телефон</span>
                <input required pattern="[0-9]{2}[0-9]{3}[0-9]{2}[0-9]{2}" type="tel" disabled name="phone"
                       value="${authorizedUser.phone}">
                <i onclick="editData(this.id)" class="fal fa-pen"
                   id="phone"></i>
                <span>Email</span>
                <input required type="email" disabled name="email"
                       value="${authorizedUser.email}">
                <i onclick="editData(this.id)" class="fal fa-pen"
                   id="email"></i>
                <button type="submit" onclick="submitData()" id="update-profile-button">Update</button>
            </form>
        </div>
        <div id="profile-menu">
            <span>Меню</span>
            <u:menu/>
        </div>
    </aside>
    <div>
        <h1>Текущие заказы</h1>
        <div id="current-services">
            <c:forEach items="${userReservations}" var="reservation">
                <section class="current-service">
                    <div>
                        <img src="<c:url value="/img/service/service-7.png"/>">
                        <div class="current-service-description">
                            <h2>${reservation.offer.name}</h2>
                            <div class="current-service-description-units">
                                <div>
                                    <span>Дата</span>
                                    <div>
                                        <i class="fal fa-calendar"></i>
                                        <span><fmt:formatDate
                                                pattern="dd.MM.yyyy"
                                                value="${reservation.date}"/></span>
                                    </div>
                                </div>
                                <div>
                                    <span>Время</span>
                                    <div>
                                        <i class="fal fa-clock"></i>
                                        <span><fmt:formatDate pattern="HH:mm"
                                                              value="${reservation.date}"/></span>
                                    </div>
                                </div>
                                <div>
                                    <span>Мастер</span>
                                    <div>
                                        <i class="fal fa-user"></i>
                                        <span>${reservation.employee.name} ${reservation.employee.surname}</span>
                                    </div>
                                </div>
                                <div>
                                    <span>Стоимость</span>
                                    <div>
                                        <i class="fal fa-wallet"></i>
                                        <span>${reservation.offer.price} byn</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="current-service-buttons-bar">
                            <div>
                                <form action="<c:url value = "edit.jsp"/>"
                                      method="POST">
                                    <input name="reservation-edit" hidden
                                           value="${reservation.id}">
                                    <button><i class="fal fa-pen"></i></button>
                                </form>
                                <form action="<c:url value="edit.jsp"/>"
                                      method="POST">
                                    <input name="reservation-delete" hidden
                                           value="${reservation.id}">
                                    <button><i class="fal fa-times"></i>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="diagram-unit">
                            <span>Очередь</span>
                            <div>2</div>
                        </div>
                        <div class="diagram-unit">
                            <span>Осталось примерно</span>
                            <div>2<span class="hours-title">часа</span></div>
                            <div>32<span>минуты</span></div>
                        </div>
                        <div class="diagram-unit">
                            <span>Стоимость</span>
                            <div>${reservation.offer.price} <span>byn</span></div>
                        </div>
                    </div>
                </section>
            </c:forEach>
        </div>
    </div>
</main>
<u:footer>
    <script src="<c:url value="/js/progileEdit.js"/>"></script>
</u:footer>
</body>
</html>