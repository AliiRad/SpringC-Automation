document.addEventListener("DOMContentLoaded", function () {
    const mobileMenuButton = document.getElementById("mobile-mode");
    const navList = document.querySelector(".nav-list")

    mobileMenuButton.addEventListener("click", function () {
        navList.classList.toggle("show")
    })
} )