<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#import "/spring.ftl" as spring />
<#include "../tags/head.ftl"/>
<#include "../tags/header.ftl"/>
<#include "../tags/footer.ftl"/>

<html>
<@head>
<#--TODO: user resource bundle-->
    <title>Профиль</title>
    <link rel="stylesheet"
          href="<@spring.url "/resources/uui/css/lib/components/jasny-bootstrap.min.css"/>"/>
    <script src="<@spring.url "/resources/uui/js/lib/components/jasny-bootstrap.min.js"/>"></script>
</@head>
<body>
<@header/>
<main id="edit-main">
    <aside>
        <img src="<@spring.url "/${authenticatedUser.imagePath}"/>">
        <div id="profile-user-description">
            <form action="<@spring.url "/profile/edit.html"/>" method="POST"
                  enctype="multipart/form-data">


                <div class="uui-file-uploader fileinput fileinput-new"
                     data-provides="fileinput">
                    <span class="btn btn-file uui-button">
                        <span class="fileinput-new">Choose...</span>
                        <span class="fileinput-exists">Change...</span>
                        <input id="avatarImage"
                               name="avatarImage"
                               type="file"
                               accept="image/jpeg,image/png">
                    </span>
                    <span class="fileinput-filename"></span>
                    <a href="#" class="close fileinput-exists"
                       data-dismiss="fileinput">×</a>
                </div>


                <span>Фамилия</span>
                <input required pattern="[A-Za-zA-Яа-я]{5,30}"
                       onChange="editData()" type="text" disabled="disabled"
                       name="surname"
                       value="${authenticatedUser.surname}"
                       class=".uui-form-element">
                <i onclick="editData(this.id)" class="fal fa-pen"
                   id="surname"></i>
                <span>Имя</span>
                <input required pattern="[A-Za-zA-Яа-я]{5,30}" type="text"
                       disabled name="name"
                       value="${authenticatedUser.name}">
                <i onclick="editData(this.id)" class="fal fa-pen" id="name"></i>
                <span>Отчество</span>
                <input required pattern="[A-Za-zA-Яа-я]{5,30}" type="text"
                       disabled name="patronymic"
                       value="${authenticatedUser.patronymic}">
                <i onclick="editData(this.id)" class="fal fa-pen"
                   id="patronymic"></i>
                <span>Телефон</span>
                <input required pattern="[0-9]{2}[0-9]{3}[0-9]{2}[0-9]{2}"
                       type="tel" disabled name="phone"
                       value="${authenticatedUser.phone?c}">
                <i onclick="editData(this.id)" class="fal fa-pen"
                   id="phone"></i>
                <span>Email</span>
                <input required type="email" disabled name="email"
                       value="${authenticatedUser.email}">
                <i onclick="editData(this.id)" class="fal fa-pen"
                   id="email"></i>
                <button type="submit" onclick="submitData()"
                        id="update-profile-button"
                        class="uui-button transparent large ">Update
                </button>
            </form>
        </div>
        <div id="profile-menu">
            <span>Меню</span>
            <@menu/>
        </div>
    </aside>
    <div>
        <@security.authorize access="hasAuthority('CUSTOMER')">
            <h1>Текущие заказы</h1>
            <div id="current-services">
                <#list userReservations as reservation>
                    <section class="current-service">
                        <div>
                            <img src="<@spring.url "/resources/img/service/service-7.png"/>">
                            <div class="current-service-description">
                                <h2>${reservation.offer.name}</h2>
                                <div class="current-service-description-units">
                                    <div>
                                        <span>Дата</span>
                                        <div>
                                            <i class="fal fa-calendar"></i>
                                            <span>${reservation.date?string["dd.MM.yyyy"]}</span>
                                        </div>
                                    </div>
                                    <div>
                                        <span>Время</span>
                                        <div>
                                            <i class="fal fa-clock"></i>
                                            <span>${reservation.date?string["HH:mm"]}</span>
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
                                            <span>${reservation.offer.price?string["0"]} byn</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="current-service-buttons-bar">
                                <div>
                                    <form action="<@spring.url "/profile/edit.html"/>"
                                          method="POST">
                                        <input name="reservation-edit" hidden
                                               value="${reservation.id}">
                                        <button class="uui-button transparent large">
                                            <i class="fal fa-pen"></i></button>
                                    </form>
                                    <form action="<@spring.url "/profile/edit.html"/>"
                                          method="POST">
                                        <input name="reservation-id" hidden
                                               value="${reservation.id}">
                                        <button class="uui-button transparent large">
                                            <i class="fal fa-times"></i>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </section>
                </#list>
            </div>
        </@security.authorize>
    </div>
</main>
<@footer>
    <script src="<@spring.url "/resources/js/profileEdit.js"/>"></script>
    <script>
        $('.fileinput').fileinput();
    </script>
</@footer>
</body>
</html>