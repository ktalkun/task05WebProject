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
        <img src="<c:url value = "/${authorizedUser.imagePath}"/>">
        <div id="profile-user-description">
            <span>Фамилия</span>
            <input type="text" disabled value="${authorizedUser.surname}">
            <i class="fal fa-pen"></i>
            <span>Имя</span>
            <input type="text" disabled value="${authorizedUser.name}">
            <i class="fal fa-pen"></i>
            <span>Отчество</span>
            <input type="text" disabled value="${authorizedUser.patronymic}">
            <i class="fal fa-pen"></i>
            <span>Телефон</span>
            <input type="tel" disabled value="${authorizedUser.phone}">
            <i class="fal fa-pen"></i>
            <span>Email</span>
            <input type="email" disabled value="${authorizedUser.email}">
            <i class="fal fa-pen"></i>
        </div>
        <div id="profile-menu">
            <span>Меню</span>
            <u:menu/>
        </div>
    </aside>
    <div>
        <h1>Текущие заказы</h1>
        <div id="current-services">
            <section class="current-service">
                <div>
                    <img src="<c:url value="/img/service/service-7.png"/>">
                    <div class="current-service-description">
                        <h2>Подравнивание усов</h2>
                        <div class="current-service-description-units">
                            <div>
                                <span>Дата</span>
                                <div>
                                    <i class="fal fa-calendar"></i>
                                    <span>12.05.2019</span>
                                </div>
                            </div>
                            <div>
                                <span>Время</span>
                                <div>
                                    <i class="fal fa-clock"></i>
                                    <span>14:25</span>
                                </div>
                            </div>
                            <div>
                                <span>Мастер</span>
                                <div>
                                    <i class="fal fa-user"></i>
                                    <span>Даниил Захаров</span>
                                </div>
                            </div>
                            <div>
                                <span>Стоимость</span>
                                <div>
                                    <i class="fal fa-wallet"></i>
                                    <span>15 byn</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="current-service-buttons-bar">
                        <div>
                            <form>
                                <input hidden value="edit-1">
                                <button><i class="fal fa-pen"></i></button>
                            </form>
                            <form>
                                <input hidden value="close-1">
                                <button><i class="fal fa-times"></i></button>
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
                        <div>18<span>byn</span></div>
                    </div>
                </div>
            </section>
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
                            <div>18<span>byn</span></div>
                        </div>
                    </div>
                </section>
            </c:forEach>
        </div>
    </div>
</main>
<u:footer></u:footer>
</body>
</html>