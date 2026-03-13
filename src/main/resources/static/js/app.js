
let botonAltaIncidencia = document.getElementById("altaIncidencia");
botonAltaIncidencia.addEventListener("click", () => mostrarSeccion("formIncidencia"));

let botonAltaUser = document.getElementById("altaUser");
botonAltaUser.addEventListener("click", () => mostrarSeccion("formUser"));

let botonListaIncidencias = document.getElementById("listaIncidencias");
botonListaIncidencias.addEventListener("click", () => mostrarSeccion("tablaIncidencias-container"));

let botonListaUsers = document.getElementById("listaUsers");
botonListaUsers.addEventListener("click", () => mostrarSeccion("tablaUsers-container"));



function mostrarSeccion(seccion) {
    const secciones = document.querySelectorAll('.seccion');
    secciones.forEach(seccion => {
        seccion.style.display = 'none';
    });

    if (seccion === "tablaIncidencias-container") {
        cargar();
    }
    let seccionMostrar = document.getElementById(seccion);
    seccionMostrar.style.display = "block";
}


async function cargar() {
    const datos = await getAllIncidencias();
    mostrarEnTabla(datos);
}


function mostrarEnTabla(datos) {

    let incidencias = datos.results;
    let tabla = document.getElementById("tabla");


    for (let incidencia of incidencias) {
        let fila = document.createElement("tr");
        fila.innerHTML = `<td>${incidencia.id}</td>`
        tabla.appendChild(fila);
    }


}