
//Menu
let botonMenuAltaIncidencia = document.getElementById("botonMenuAltaIncidencia");
botonMenuAltaIncidencia.addEventListener("click", () => {
    mostrarSeccion("formIncidencia")
    cargarUsersSelect()
})

let botonMenuAltaUser = document.getElementById("botonMenuAltaUser");
botonMenuAltaUser.addEventListener("click", () => mostrarSeccion("formUser"));

let botonMenuListaIncidencias = document.getElementById("botonMenuListaIncidencias");
botonMenuListaIncidencias.addEventListener("click", () => mostrarSeccion("tablaIncidencias-container"));

let botonMenuListaUsers = document.getElementById("botonMenuListaUsers");
botonMenuListaUsers.addEventListener("click", () => mostrarSeccion("tablaUsers-container"));

let botonMenuBuscarIncidencia = document.getElementById("botonMenuBuscarIncidencia");
botonMenuBuscarIncidencia.addEventListener("click", () => mostrarSeccion("seccionBuscarIncidencia"));



//Secciones

//Buscar
let botonBuscarIncidenciaForm = document.getElementById("botonBuscarIncidenciaForm");
botonBuscarIncidenciaForm.addEventListener("click", () => handlerGetIncidenciaById());

//Crear incicencia 
let botonCrearIncidenciaForm = document.getElementById("botonCrearIncidenciaForm");
botonCrearIncidenciaForm.addEventListener("click", () => handlerCrearIncidenciaClick());

//Selector usuarios
//let selectUsers = document.getElementById("selectUsers");
//selectUsers.addEventListener("click", () => cargarUsersSelect());





function mostrarSeccion(seccion) {
    const secciones = document.querySelectorAll('.seccion');
    secciones.forEach(seccion => {
        seccion.style.display = 'none';
    });

    if (seccion === "tablaIncidencias-container") {
        handlerGetAllIncidenciasClick();
    }
    else if (seccion === "tablaUsers-container") {
        handlerGetAllUsersClick();
    }

    let seccionMostrar = document.getElementById(seccion);
    seccionMostrar.style.display = "flex";
}



//TABLE INCIDENCIAS
function loadTablaIncidencias(datos) {

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


async function handlerGetAllIncidenciasClick() {
    const datos = await getAllIncidencias();
    loadTablaIncidencias(datos);
}


async function handlerGetIncidenciaById() {
    let input = document.getElementById("idIncidencia");
    let id = input.value;
    const incidencia = await getIncidenciasById(id);

    let seccionMostrar = document.getElementById("tablaIncidencias-container");
    seccionMostrar.style.display = "block";

    let seccionOcultar = document.getElementById("seccionBuscarIncidencia");
    seccionOcultar.style.display = "none";

    loadTablaIncidencias(incidencia);
}

async function handlerCrearIncidenciaClick() {
    let input = document.getElementById("inputDescripcio");
    let descripcion = input.value;

    let inputUser = document.getElementById("selectUsers");
    let user = null;
    if (inputUser.selectedIndex !== -1) {
        let idBuscar = inputUser.value;
        user = {
            id: idBuscar
        }
        //user = await handlerGetUserId(id);
    }
    else {
        alert("USUARIO NO SELECCIONADO");
        return;
    }

    let fila = document.createElement("div");

    if (descripcion.length === 0) {
        alert("DESCRIPCION OBLIGATORIA");
        return;

    }
    const res = await crearIncidencia(descripcion, user);

    if (res.ok) {
        fila.innerHTML = JSON.stringify(res);
        console.log("ok");
    }

    let contSeccions = document.getElementById("contSeccions");
    contSeccions.appendChild(fila);

}

async function handlerGetUserId(id) {

    const user = await getUserId(id);

    return user;
}


async function handlerUpdateIncidenciaClick() {

}

async function handlerDeleteIncidenciaClick() {

}











//Users

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
        option.value = user.id;
        option.innerHTML = `${user.id} - ${user.nom}`
        selectUsers.appendChild(option);
    }

}





async function handlerGetAllUsersClick() {
    const datos = await getAllUsers();
    mostrarEnTablaUsers(datos);
}







