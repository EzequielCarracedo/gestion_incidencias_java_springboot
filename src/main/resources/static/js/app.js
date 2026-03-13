
let botonMenuAltaIncidencia = document.getElementById("botonMenuAltaIncidencia");
botonMenuAltaIncidencia.addEventListener("click", () => mostrarSeccion("formIncidencia"));

let botonMenuAltaUser = document.getElementById("botonMenuAltaUser");
botonMenuAltaUser.addEventListener("click", () => mostrarSeccion("formUser"));

let botonMenuListaIncidencias = document.getElementById("botonMenuListaIncidencias");
botonMenuListaIncidencias.addEventListener("click", () => mostrarSeccion("tablaIncidencias-container"));

let botonMenuListaUsers = document.getElementById("botonMenuListaUsers");
botonMenuListaUsers.addEventListener("click", () => mostrarSeccion("tablaUsers-container"));

let botonMenuBuscarIncidencia = document.getElementById("botonMenuBuscarIncidencia");
botonMenuBuscarIncidencia.addEventListener("click", () => mostrarSeccion("seccionBuscarIncidencia"));


let buscarIncidencia = document.getElementById("botonBuscarIncidencia");
buscarIncidencia.addEventListener("click", () => buscarIncidenciaId());



//Crear incicencia 
let crearIncidenciaElement = document.getElementById("crearIncidencia");
crearIncidenciaElement.addEventListener("click", () => crearIncidenciaForm());

//Selector usuarios
let selectUsers = document.getElementById("selectUsers");
selectUsers.addEventListener("click", () => cargarUsersSelect());


function mostrarSeccion(seccion) {
    const secciones = document.querySelectorAll('.seccion');
    secciones.forEach(seccion => {
        seccion.style.display = 'none';
    });

    if (seccion === "tablaIncidencias-container") {
        cargarIncidencias();
    }
    else if (seccion === "tablaUsers-container") {
        cargarUsers();
    }

    let seccionMostrar = document.getElementById(seccion);
    seccionMostrar.style.display = "block";
}


async function cargarIncidencias() {
    const datos = await getAllIncidencias();
    mostrarEnTablaIncidencias(datos);
}


function mostrarEnTablaIncidencias(datos) {

    let incidencias = datos;
    let tabla = document.getElementById("tablaIncidencias");
    tabla.innerHTML = "";
    if (!Array.isArray(incidencias)) {
        let fila = document.createElement("tr");
        fila.innerHTML = `<td>${incidencias.id}</td><td>${incidencias.descripcion}</td><td>${incidencias.estado}</td><td>${incidencias.user.id}</td><td>${incidencias.user.nom}</td><td>${incidencias.user.email}</td>`
        tabla.appendChild(fila);
    }
    else {
        for (let incidencia of incidencias) {
            let fila = document.createElement("tr");
            fila.innerHTML = `<td>${incidencia.id}</td><td>${incidencia.descripcion}</td><td>${incidencia.estado}</td><td>${incidencia.user.id}</td><td>${incidencia.user.nom}</td><td>${incidencia.user.email}</td>`
            tabla.appendChild(fila);
        }
    }

}


async function buscarIncidenciaId() {
    let input = document.getElementById("idIncidencia");
    let id = input.value;
    const incidencia = await getIncidenciasById(id);

    let seccionMostrar = document.getElementById("tablaIncidencias-container");
    seccionMostrar.style.display = "block";

    let seccionOcultar = document.getElementById("seccionBuscarIncidencia");
    seccionOcultar.style.display = "none";

    mostrarEnTablaIncidencias(incidencia);
}

async function crearIncidenciaForm() {
    let input = document.getElementById("inputDescripcio");
    let descripcion = input.value;

    let inputUser = document.getElementById("selectUsers");
    let user = inputUser.options[inputUser.selectedIndex].text;
    let fila = document.createElement("div");
    fila.innerHTML = "ERROR";
    const res = await crearIncidenciaElement(descripcion, {nom: user, email:"gsdfds@gmail.com"});
    if (!res.ok) document.appendChild(fila)
}




//Users

async function cargarUsers() {
    const datos = await getAllUsers();
    mostrarEnTablaUsers(datos);
}

function mostrarEnTablaUsers(datos) {
    let users = datos;
    let tabla = document.getElementById("tablaUsers");
    tabla.innerHTML = "";
    for (let user of users) {
        let fila = document.createElement("tr");
        fila.innerHTML = `<td>${user.id}</td><td>${user.nom}</td><td>${user.email}</td>`
        tabla.appendChild(fila);
    }

}



async function cargarUsersSelect() {
    let selectUsers = document.getElementById("selectUsers");
    let users = await getAllUsers();
    selectUsers.innerHTML = "";

    if (!Array.isArray(users) || users.length === 0) {
        console.warn("No hay usuarios");
        return;
    }
       for (let user of users) {
        let option = document.createElement("option");
        option.innerHTML = user.nom;
        selectUsers.appendChild(option);
    }

}

