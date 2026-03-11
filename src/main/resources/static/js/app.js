let seccion = document.getElementById("altaIncidencia");
seccion.addEventListener("click", mostrarSeccion);


function mostrarSeccion(seccion) {
    let seccionTotal = document.querySelector(".seccion")
    seccionTotal.style.display = "block";
}
