
const API_INCIDENCIAS = 'http://localhost:8081/incidencias';
const API_USERS = 'http://localhost:8081/usuarios';


//Incidencias
async function getAllIncidencias() {
    try {
        const res = await fetch(API_INCIDENCIAS);
        if (!res.ok) {
            throw new Error(`Error HTTP ${res.status}`);
        }

        return await res.json();

    } catch (error) {
        console.error("Error:", error);
    }
}


async function getIncidenciasById(id) {
    try {
        const res = await fetch(`${API_INCIDENCIAS}/${id}`);
        if (!res.ok) {
            throw new Error(`Error HTTP ${res.status}`);
        }

        return await res.json();

    } catch (error) {
        console.error("Error:", error);
    }
}


async function crearIncidenciaForm(descripcion, usuario) {
    try {
        const res = await fetch(API_INCIDENCIAS, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({descripcion, usuario })
        });
        if (!res.ok) {
            throw new Error(`Error HTTP ${res.status}`);
        }

        return await res.json();

    } catch (error) {
        console.error("Error:", error);
    }
}






//Users
async function getAllUsers() {
    try {
        const res = await fetch(API_USERS);
        if (!res.ok) {
            throw new Error(`Error HTTP ${res.status}`);
        }

        return await res.json();

    } catch (error) {
        console.error("Error:", error);
    }
}