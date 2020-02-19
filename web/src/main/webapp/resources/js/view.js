var UserHeaderNav = document.querySelector("#user-header-nav");
var AdditonalMenu = document.querySelector("#user-header-nav > #additional-menu");
if (UserHeaderNav) {
    UserHeaderNav.onmouseover = showAdditionalMenu;
    UserHeaderNav.onmouseout = hideAdditionalMenu;
    AdditonalMenu.onmouseout = hideAdditionalMenu;
}

function showAdditionalMenu(event) {
    document.querySelector("#user-header-nav > #additional-menu").classList.add("show")
};

function hideAdditionalMenu(event) {
    if (event.relatedTarget == null || event.relatedTarget.id != "additional-menu") {
        document.querySelector("#user-header-nav > #additional-menu").classList.remove("show")
    }
}
