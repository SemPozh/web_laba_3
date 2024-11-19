const nabButton = document.querySelector("#header input[type='button']");
if (window.location.pathname === "/lab3/templates/main.xhtml"){
    nabButton.value = "На стартовую";
} else if (window.location.pathname === "/lab3/templates/start.xhtml"){
    nabButton.value = "На главную";

}