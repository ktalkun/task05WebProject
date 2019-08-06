document.querySelector("#user-header-nav").onmouseover = showAdditionalMenu;

function showAdditionalMenu(event) {
    document.querySelector("#user-header-nav > #additional-menu").classList.add("show")
};

document.querySelector("#user-header-nav").onmouseout
    = document.querySelector("#user-header-nav > #additional-menu").onmouseout = hideAdditionalMenu;

function hideAdditionalMenu(event) {
    if (event.relatedTarget.id != "additional-menu") {
        document.querySelector("#user-header-nav > #additional-menu").classList.remove("show")
    }
}
