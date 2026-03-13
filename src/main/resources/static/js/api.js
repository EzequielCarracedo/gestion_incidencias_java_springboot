
const API_INCIDENCIAS = 'http://localhost:8081/incidencias';
const API_USERS = 'http://localhost:8081/usuarios';

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