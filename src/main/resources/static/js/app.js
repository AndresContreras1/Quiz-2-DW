// Consume el API del propio backend. Las rutas se copian del controlador,
// no se adivinan.
const URL_PRODUCTOS = "/productos";

// El estado vive aqui; la tabla se regenera completa cada vez que cambia.
let productos = [];

const formularioProducto = document.getElementById("formulario-producto");
const formularioBusqueda = document.getElementById("formulario-busqueda");
const cuerpoTabla = document.getElementById("cuerpo-tabla");
const mensajeRegistro = document.getElementById("mensaje-registro");
const mensajeBusqueda = document.getElementById("mensaje-busqueda");

// --- POST /productos -------------------------------------------------------

formularioProducto.addEventListener("submit", async (evento) => {
    evento.preventDefault();

    // was-validated se anade aqui, no en el HTML: si no, todos los campos
    // salen en rojo antes de que el usuario escriba nada.
    formularioProducto.classList.add("was-validated");
    if (!formularioProducto.checkValidity()) {
        return;
    }

    // Sin id: el POST de creacion no lo lleva, lo genera la base de datos.
    const producto = {
        nombre: document.getElementById("nombre").value.trim(),
        categoria: document.getElementById("categoria").value.trim(),
        precio: document.getElementById("precio").value,
        activo: document.getElementById("activo").checked
    };

    try {
        const respuesta = await fetch(URL_PRODUCTOS, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(producto)
        });

        if (!respuesta.ok) {
            throw new Error("El servidor respondio " + respuesta.status);
        }

        const guardado = await respuesta.json();

        mostrarMensaje(mensajeRegistro, "Producto registrado con id " + guardado.id + ".", true);
        formularioProducto.reset();
        formularioProducto.classList.remove("was-validated");
        document.getElementById("activo").checked = true;
    } catch (error) {
        mostrarMensaje(mensajeRegistro, "No se pudo registrar: " + error.message, false);
    }
});

// --- GET /productos/buscar/{categoria} -------------------------------------

formularioBusqueda.addEventListener("submit", async (evento) => {
    evento.preventDefault();

    const categoria = document.getElementById("categoria-buscada").value.trim();
    if (categoria === "") {
        mensajeBusqueda.textContent = "Escribe una categoria para buscar.";
        return;
    }

    try {
        const respuesta = await fetch(URL_PRODUCTOS + "/buscar/" + encodeURIComponent(categoria));

        if (!respuesta.ok) {
            throw new Error("El servidor respondio " + respuesta.status);
        }

        productos = await respuesta.json();
        renderizarTabla();

        mensajeBusqueda.textContent = productos.length === 0
            ? "No hay productos activos en la categoria " + categoria + "."
            : productos.length + " producto(s) en la categoria " + categoria + ".";
    } catch (error) {
        productos = [];
        renderizarTabla();
        mensajeBusqueda.textContent = "No se pudo consultar: " + error.message;
    }
});

// --- Render ----------------------------------------------------------------

function renderizarTabla() {
    cuerpoTabla.innerHTML = productos
        .map((producto) => `
            <tr>
                <td>${producto.id}</td>
                <td>${producto.nombre}</td>
                <td>${producto.categoria}</td>
                <td class="text-end">${formatearPrecio(producto.precio)}</td>
            </tr>
        `)
        .join("");
}

function formatearPrecio(precio) {
    return Number(precio).toLocaleString("es-CO", {
        style: "currency",
        currency: "COP",
        minimumFractionDigits: 2
    });
}

function mostrarMensaje(elemento, texto, exito) {
    elemento.textContent = texto;
    elemento.className = exito ? "mt-3 mb-0 small text-success" : "mt-3 mb-0 small text-danger";
}
