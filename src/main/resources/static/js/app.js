

//Menu Incidencia alta
let botonMenuAltaIncidencia = document.getElementById("botonMenuAltaIncidencia");
botonMenuAltaIncidencia.addEventListener("click", () => {
    mostrarSeccion("formIncidencia")
    let fila = document.getElementById("incidenciaCreada");
    fila.innerHTML = "";
    cargarUsersSelect()
})

//Crear incicencia form
let botonCrearIncidenciaForm = document.getElementById("botonCrearIncidenciaForm");
botonCrearIncidenciaForm.addEventListener("click", () => handlerCrearIncidenciaClick());

//Lista all incidencias
let botonMenuListaIncidencias = document.getElementById("botonMenuListaIncidencias");
botonMenuListaIncidencias.addEventListener("click", () => mostrarSeccion("tablaIncidencias-container"));

//Buscar menu
let botonMenuBuscarIncidencia = document.getElementById("botonMenuBuscarIncidencia");
botonMenuBuscarIncidencia.addEventListener("click", () => {
    mostrarSeccion("seccionBuscarIncidencia")
    resetTablaBusqueda();
});

//Buscar incidencia form
let botonBuscarIncidenciaForm = document.getElementById("botonBuscarIncidenciaForm");
botonBuscarIncidenciaForm.addEventListener("click", () => {
    handlerGetIncidenciaById()
});


//UPDATE INCIDENCIA
let botonUpdateIncidenciaTable = document.getElementById("tablaIncidencias");
botonUpdateIncidenciaTable.addEventListener("click", (e) => {

    const btnEditar = e.target.closest(".acciones__item--edit");
    const btnEliminar = e.target.closest(".acciones__item--delete");
    const btnGuardar = e.target.closest(".acciones__item--save");
    const btnCancelar = e.target.closest(".acciones__item--cancel");


    if (btnEditar) {
        const cont = e.target.closest(".acciones");
        const id = cont.dataset.id;
        const tipo = cont.dataset.tipo;

        const tdAccions = btnEditar.closest("td.col-accions");
        tdAccions.replaceWith(crearDesplegableModificar(id, tipo));
        loadEditableIncidencia(id);
        return
    }

    if (btnGuardar) {
        const cont = e.target.closest(".acciones");
        let input = cont.closest("tr").querySelector(".input-nombre");
        const descripcion = input.value;

        input = cont.closest("tr").querySelector("select");
        const estado = input.value;

        const id = cont.dataset.id;


        handlerUpdateIncidenciaClick(id, descripcion, estado);
        return
    }

    if (btnCancelar) {
        return
    }

    if (btnEliminar) {
        return
    }




});


function loadEditableIncidencia(id,) {
    let descripcion = document.getElementById(`descripcionModificar${id}`);
    let estat = document.getElementById(`estadoModificar${id}`)
    descripcion.innerHTML = `<td><input type="text" class ="input-nombre"></input></td>`
    let estatValor = estat.textContent;
    estat.outerHTML = crearDesplegableEstado(estatValor);
}


//Menu users

//Alta
let botonMenuAltaUser = document.getElementById("botonMenuAltaUser");
botonMenuAltaUser.addEventListener("click", () => {
    mostrarSeccion("formUser")
    let fila = document.getElementById("usuarioCreado");
    fila.innerHTML = "";
});


//Crear user form
let botonCrearUserForm = document.getElementById("botonCrearUserForm");
botonCrearUserForm.addEventListener("click", () => handlerCrearUserClick());

//Lista
let botonMenuListaUsers = document.getElementById("botonMenuListaUsers");
botonMenuListaUsers.addEventListener("click", () => mostrarSeccion("tablaUsers-container"));



//codi per desplegable de tables
document.addEventListener("click", (e) => {
    const toggle = e.target.closest(".acciones__toggle");
    const item = e.target.closest(".acciones__item");

    if (item) {
        //closest busca el primer element que cumpleixi el selector
        const dd = item.closest(".acciones");
        //elimina open de la class
        dd?.classList.remove("open");
        return;
    }

    if (toggle) {
        const dd = toggle.closest(".acciones");

        document.querySelectorAll(".acciones.open").forEach((x) => {
            if (x !== dd) x.classList.remove("open");
        });

        dd.classList.toggle("open");
        return;
    }

    document.querySelectorAll(".acciones.open").forEach((x) => x.classList.remove("open"));
});



//VISIBILIDAD SECCIONES
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
    tabla.innerHTML = `<tr><th>ID</th><th>DESCRIPCION</th><th>ESTADO</th><th>USUARIO_ID</th><th>NOMBRE</th><th>EMAIL</th><th class="col-accions">ACCIONS</th></tr>`;
    if (!Array.isArray(incidencias)) {
        let fila = document.createElement("tr");
        fila.innerHTML = `<td id = "idFila">${incidencias.id}</td><td id ="descripcionModificar${incidencias.id}">${incidencias.descripcion}</td><td id = "estadoModificar${incidencias.id}">${incidencias.estado}</td><td>${incidencias.user.id}</td><td>${incidencias.user.nom}</td><td>${incidencias.user.email}</td>`
        fila.appendChild(crearDesplegableAcciones(incidencias.id, "incidencia"))
        tabla.appendChild(fila);
    }
    else {
        for (let incidencia of incidencias) {
            let fila = document.createElement("tr");
            fila.innerHTML = `<td>${incidencia.id}</td><td id ="descripcionModificar${incidencia.id}">${incidencia.descripcion}</td><td id = "estadoModificar${incidencia.id}">${incidencia.estado}</td><td>${incidencia.user.id}</td><td>${incidencia.user.nom}</td><td>${incidencia.user.email}</td>`
            fila.appendChild(crearDesplegableAcciones(incidencia.id, "incidencia"))
            tabla.appendChild(fila);
        }
    }
}


async function handlerGetAllIncidenciasClick() {
    try {
        const datos = await getAllIncidencias();
        loadTablaIncidencias(datos);
    }
    catch (error) {
        alert("ERROR AL CARGAR INCIDENCIAS");
    }

}


async function handlerGetIncidenciaById() {
    let input = document.getElementById("idIncidencia");
    let id = input.value;

    if (id.length === 0) {
        alert("EL ID NO PUEDE ESTAR VACIO");
        return
    }

    try {
        const incidencia = await getIncidenciasById(id);

        if (!incidencia || Object.keys(incidencia).length === 0) {
            alert("INCIDENCIA NO ENCONTRADA");
            return;
        }
        loadTablaBusqueda(incidencia);
    }
    catch (error) {
        alert("INCIDENCIA NO ENCONTRADA");
        return
    }


}

async function handlerCrearIncidenciaClick() {

    let input = document.getElementById("inputDescripcio");
    let descripcion = input.value;

    let inputUser = document.getElementById("selectUsers");
    let idUser = 0;
    if (inputUser.selectedIndex !== -1) {
        idUser = inputUser.value;

    }
    else {
        alert("USUARIO NO SELECCIONADO");
        return;
    }


    if (descripcion.length === 0) {
        alert("DESCRIPCION OBLIGATORIA");
        return;
    }

    let fila = document.getElementById("incidenciaCreada");

    try {
        const res = await crearIncidencia(descripcion, idUser);
        console.log(res);
        fila.innerHTML = `<b>INCIDENCIA CREADA CORRECTAMENTE<br>
            <b><br>
            <b>ID:</b> ${res.id} <br>
            <b>Descripción:</b> ${res.descripcion} <br>
            <b>User ID:</b> ${res.user.id}
        `;

    } catch (error) {
        alert(error);
    }


}




async function handlerUpdateIncidenciaClick(id, descripcion, estado) {

    try {
        const res = await updateIncidencia(id, descripcion, estado);
        fila.innerHTML = `<b>INCIDENCIA MODIFICADA CORRECTAMENTE<br>
            <b><br>
            <b>ID:</b> ${res.id} <br>
            <b>Descripción:</b> ${res.descripcion} <br>
            <b>User ID:</b> ${res.user.id} <br>
            <b> User: </b> ${res.user.nom}
        `;

    } catch (error) {
        alert(error.message);
    }



}

function crearDesplegableEstado(estado) {
    if (estado === "ABIERTA") {
        return `<td><select>
                            <option value ="ABIERTA">ABIERTA</option>
                            <option value = "EN_PROCESO">EN PROCESO</option>
                        </select></td>`
    }
    if (estado === "EN PROCESO") {
        return `<td><select>
                            <option value = "EN_PROCESO">EN PROCESO</option>
                            <option value = "CERRADA">CERRADA</option>
                        </select></td>`
    }
    if (estado === "CERRADA") {
        return `<td><select>
                            <option value = "CERRADA">CERRADA</option>
                        </select></td>`
    }
}






async function handlerDeleteIncidenciaClick() {

}







function resetTablaBusqueda() {

    let tabla = document.getElementById("tablaBusqueda");

    tabla.innerHTML = "";
}


function loadTablaBusqueda(incidencia) {

    let tabla = document.getElementById("tablaBusqueda");

    tabla.innerHTML = `
        <tr>
            <th>ID</th>
            <th>DESCRIPCION</th>
            <th>ESTADO</th>
            <th>ID_USUARIO</th>
            <th>NOMBRE</th>
            <th>EMAIL</th>
        </tr>
    `;

    let fila = document.createElement("tr");

    fila.innerHTML = `
        <td>${incidencia.id}</td>
        <td>${incidencia.descripcion}</td>
        <td>${incidencia.estado}</td>
        <td>${incidencia.user.id}</td>
        <td>${incidencia.user.nom}</td>
        <td>${incidencia.user.email}</td>
    `;

    tabla.appendChild(fila);
}




//USERS

function loadTablaUsers(datos) {

    let users = datos;
    let tabla = document.getElementById("tablaUsers");
    tabla.innerHTML = `<tr><th>ID</th><th>NOMBRE</th><th>EMAIL</th><th class="col-accions">ACCIONS</th></tr>`;
    if (!Array.isArray(users)) {
        let fila = document.createElement("tr");
        fila.innerHTML = `<td>${users.id}</td><td>${users.nom}</td><td>${users.email}</td>`
        fila.appendChild(crearDesplegableAcciones(users.id, "user"))
        tabla.appendChild(fila);
    }
    else {
        for (let user of users) {
            let fila = document.createElement("tr");
            fila.innerHTML = `<td>${user.id}</td><td>${user.nom}</td><td>${user.email}</td>`
            fila.appendChild(crearDesplegableAcciones(users.id, "user"))
            tabla.appendChild(fila);
        }
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

async function handlerCrearUserClick() {

    let input = document.getElementById("nombre");
    let nombre = input.value;

    input = document.getElementById("correo");
    let correo = input.value;


    if (nombre.length === 0) {
        alert("NOMBRE OBLIGATORIO");
        return;
    }
    if (correo.length === 0) {
        alert("EMAIL OBLIGATORIO");
        return;
    }

    let user = {
        nom: nombre,
        email: correo
    }

    let fila = document.getElementById("usuarioCreado");

    try {
        const res = await crearUser(user);
        fila.innerHTML = `<b>USUARIO CREADO CORRECTAMENTE<br>
            <b><br>
            <b>ID:</b> ${res.id} <br>
            <b>NOMBRE:</b> ${res.nom} <br>
            <b>EMAIL:</b> ${res.email}
        `;


    } catch (error) {
        alert(error);
    }


}



async function handlerGetAllUsersClick() {
    try {
        const datos = await getAllUsers();
        loadTablaUsers(datos);
    }
    catch (error) {
        alert("Error al cargar users");
    }

}


//FER
async function handlerGetUserId() {

    const user = await getUserId(id);

    return user;
}






//Drop down menu

function crearDesplegableAcciones(id, tipo) {

    const td = document.createElement("td");
    td.className = "col-accions";

    td.innerHTML = `
    <div class="acciones" data-id="${id}" data-tipo="${tipo}">
      <button type="button" class="acciones__toggle" aria-haspopup="menu">
        Accions
      </button>

      <div class="acciones__menu" role="menu">
        <button type="button"  class="acciones__item acciones__item--edit" role="menuitem">
          Modificar
        </button>

        <div class="acciones__sep" role="separator"></div>

        <button type="button" class="acciones__item acciones__item--delete" role="menuitem">
          Eliminar
        </button>
      </div>
    </div>
  `;

    return td;
}



function crearDesplegableModificar(id, tipo) {

    const td = document.createElement("td");

    td.className = "col-accions";

    td.innerHTML = `
    <div class="acciones" data-id="${id}" data-tipo="${tipo}">
      <button type="button" class="acciones__toggle" aria-haspopup="menu">
        Accions
      </button>

      <div class="acciones__menu" role="menu">
        <button type="button" class="acciones__item acciones__item--save" role="menuitem">
          Guardar
        </button>

        <div class="acciones__sep" role="separator"></div>

        <button  type="button" class="acciones__item acciones__item--cancel" role="menuitem">
          Cancelar
        </button>
      </div>
    </div>
  `;

    return td;
}







