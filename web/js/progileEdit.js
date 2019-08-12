var initialFieldValues = new Map();
initialFieldValues.set("surname", document.querySelector("input[name=surname]").value);
initialFieldValues.set("name", document.querySelector("input[name=name]").value);
initialFieldValues.set("patronymic", document.querySelector("input[name=patronymic]").value);
initialFieldValues.set("phone", document.querySelector("input[name=phone]").value);
initialFieldValues.set("email", document.querySelector("input[name=email]").value);

var isUpdate = false;
var updateProfileButton = document.getElementById("update-profile-button");

function uploadAvatar() {
    document.querySelector("input[name=avatarImage]").click();

}

function choosenAvatar() {
    if (document.querySelector("input[name=avatarImage]").value != "") {
        isUpdate = true;
        updateProfileButton.style.display = "block";
    }
}

var previousElementId = null;

function editData(elementId) {
    document.querySelector("input[name=\"" + elementId + "\"]").disabled = false;
    if (previousElementId) {
        var previousElement = document.querySelector("input[name=\"" + previousElementId + "\"]");
        previousElement.disabled = true;
        if (previousElement.value != initialFieldValues.get(previousElementId)) {
            isUpdate = true;
            updateProfileButton.style.display = "block";
        }
    }
    if (previousElementId != elementId) {
        previousElementId = elementId;
    } else {
        previousElementId = null;
    }
}

function submitData() {
    document.querySelector("input[name=name]").disabled =
        document.querySelector("input[name=surname]").disabled =
            document.querySelector("input[name=patronymic]").disabled =
                document.querySelector("input[name=phone]").disabled =
                    document.querySelector("input[name=email]").disabled = false;
}