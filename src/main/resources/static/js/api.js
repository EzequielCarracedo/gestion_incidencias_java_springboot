
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


async function crearIncidencia(descripcionNova, usuarioId) {

    try {
        const res = await fetch(API_INCIDENCIAS, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ descripcion: descripcionNova, userId: usuarioId })
        });
        const data = await res.json();
        console.log("STATUS:", res.status);
        console.log("DATA:", data);
        if (!res.ok) {
            const msg =
                data.message ||
                data.errors?.[0]?.defaultMessage ||
                "Error creando Incidencia";
            throw new Error(msg);
        }

        return data;

    } catch (error) {
        console.error("Error al crear incidencia:", error);
        throw error;
    }
}

async function updateIncidencia(id, descripcion, estado) {
    try {
        const res = await fetch(`${API_INCIDENCIAS}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'

            },
            body: JSON.stringify({ descripcion, estado })
        });
        if (!res.ok) {
            throw new Error(`Error HTTP ${res.status}, ${res.message}`);
        }

        return await res.json();

    } catch (error) {
        console.error("Error al actualizar:", error);
        throw error;
    }
}


async function deleteIncidencia(id) {
    try {
        const res = await fetch(`${API_INCIDENCIAS}/${id}`, {
            method: 'DELETE'
        });
        if (!res.ok) {
            throw new Error(`Error HTTP ${res.status}, ${res.message}`);
        }

        return true;

    } catch (error) {
        console.error("Error al borrar:", error);
        throw error;
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


async function getUserId(id) {
    try {
        const res = await fetch(`${API_USERS}/${id}`);
        if (!res.ok) {
            throw new Error(`Error HTTP ${res.status}`);
        }

        return await res.json();

    } catch (error) {
        console.error("Error:", error);
    }
}


async function crearUser(usuario) {
    try {
        const res = await fetch(API_USERS, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ nom: usuario.nom, email: usuario.email })
        });

        const data = await res.json();
        console.log("STATUS:", res.status);
        console.log("DATA:", data);

        if (!res.ok) {
            const msg =
                data.message ||
                data.errors?.[0]?.defaultMessage ||
                "Error creando Usuario";
            throw new Error(msg);
        }

        return data;

    } catch (error) {
        console.error("ERROR AL CREAR USUARIO:", error);
        throw error;
    }
}



async function updateUser(id, nom, email) {

    console.log(`comprovacio nom ${nombre}`)
    try {
        const res = await fetch(`${API_USERS}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'

            },
            body: JSON.stringify({ nom, email })
        });
        if (!res.ok) {
            throw new Error(`Error HTTP ${res.status}, ${res.message}`);
        }

        return await res.json();

    } catch (error) {
        console.error("Error al actualizar:", error);
        throw error;
    }
}


async function deleteUser(id) {
    try {
        const res = await fetch(`${API_USERS}/${id}`, {
            method: 'DELETE'
        });
        if (!res.ok) {
            throw new Error(`Error HTTP ${res.status}, ${res.message}`);
        }

        return true;

    } catch (error) {
        console.error("Error al borrar:", error);
        throw error;
    }
}

