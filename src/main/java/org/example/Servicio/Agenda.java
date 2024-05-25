package org.example.Servicio;

import org.example.Modelo.Contacto;
import org.example.Modelo.NodoContacto;

<<<<<<< HEAD
import java.time.LocalDate;

public class Agenda {
=======
import java.io.Serializable;
import java.time.LocalDate;

public class Agenda implements Serializable {
    private static final long serialVersionUID = 1L;
>>>>>>> 199b9fb (Initial commit)
    private NodoContacto raiz;

    public Agenda() {
        this.raiz = null;
    }

    public void agregarContacto(String nombre, long telefono, String correoElectronico, LocalDate fechaNacimiento) {
        Contacto nuevoContacto = new Contacto(nombre, telefono, correoElectronico, fechaNacimiento);
        if (this.raiz == null) {
            this.raiz = new NodoContacto(nuevoContacto);
        } else {
            this.insertar(this.raiz, nuevoContacto);
        }
    }

    private void insertar(NodoContacto padre, Contacto contacto) {
        if (contacto.getNombre().compareTo(padre.getContacto().getNombre()) < 0) {
            if (padre.getIzdo() == null) {
                padre.setIzdo(new NodoContacto(contacto));
            } else {
                insertar(padre.getIzdo(), contacto);
            }
        } else if (contacto.getNombre().compareTo(padre.getContacto().getNombre()) > 0) {
            if (padre.getDcho() == null) {
                padre.setDcho(new NodoContacto(contacto));
            } else {
                insertar(padre.getDcho(), contacto);
            }
<<<<<<< HEAD
        }
    }

    public Contacto buscarContacto(String nombre) {
        return buscar(this.raiz, nombre);
    }

    private Contacto buscar(NodoContacto nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        if (nombre.equals(nodo.getContacto().getNombre())) {
            return nodo.getContacto();
        } else if (nombre.compareTo(nodo.getContacto().getNombre()) < 0) {
            return buscar(nodo.getIzdo(), nombre);
        } else {
            return buscar(nodo.getDcho(), nombre);
        }
    }

    public void eliminarContacto(String nombre) {
=======
        } else {
            throw new IllegalArgumentException("El contacto con el nombre '" + contacto.getNombre() + "' ya existe.");
        }
    }

    public Contacto buscarContacto(String criterio) {
        if (criterio == null || criterio.isEmpty()) {
            throw new IllegalArgumentException("El criterio no puede estar vacío.");
        }
        return buscar(this.raiz, criterio.toLowerCase());
    }

    private Contacto buscar(NodoContacto nodo, String criterio) {
        if (nodo == null) {
            return null;
        }
        Contacto contacto = nodo.getContacto();
        if (contacto.getNombre().toLowerCase().equals(criterio) ||
                String.valueOf(contacto.getTelefono()).equals(criterio) ||
                contacto.getCorreoElectronico().toLowerCase().equals(criterio)) {
            return contacto;
        }
        Contacto encontrado = buscar(nodo.getIzdo(), criterio);
        if (encontrado == null) {
            encontrado = buscar(nodo.getDcho(), criterio);
        }
        return encontrado;
    }

    public void eliminarContacto(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
>>>>>>> 199b9fb (Initial commit)
        this.raiz = eliminar(this.raiz, nombre);
    }

    private NodoContacto eliminar(NodoContacto nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        if (nombre.compareTo(nodo.getContacto().getNombre()) < 0) {
            nodo.setIzdo(eliminar(nodo.getIzdo(), nombre));
        } else if (nombre.compareTo(nodo.getContacto().getNombre()) > 0) {
            nodo.setDcho(eliminar(nodo.getDcho(), nombre));
        } else {
            if (nodo.getIzdo() == null) {
                return nodo.getDcho();
            } else if (nodo.getDcho() == null) {
                return nodo.getIzdo();
            }
            NodoContacto temp = minValorNodo(nodo.getDcho());
            nodo.getContacto().setTelefono(temp.getContacto().getTelefono());
            nodo.getContacto().setNombre(temp.getContacto().getNombre());
            nodo.getContacto().setCorreoElectronico(temp.getContacto().getCorreoElectronico());
            nodo.getContacto().setFechaNacimiento(temp.getContacto().getFechaNacimiento());
            nodo.setDcho(eliminar(nodo.getDcho(), temp.getContacto().getNombre()));
        }
        return nodo;
    }

    private NodoContacto minValorNodo(NodoContacto nodo) {
        NodoContacto actual = nodo;
        while (actual.getIzdo() != null) {
            actual = actual.getIzdo();
        }
        return actual;
    }

<<<<<<< HEAD
    public void mostrarContactos() {
        inOrden(this.raiz);
    }

    private void inOrden(NodoContacto nodo) {
        if (nodo != null) {
            inOrden(nodo.getIzdo());
            System.out.println("Nombre: " + nodo.getContacto().getNombre() +
                    ", Teléfono: " + nodo.getContacto().getTelefono() +
                    ", Correo: " + nodo.getContacto().getCorreoElectronico() +
                    ", Fecha de Nacimiento: " + nodo.getContacto().getFechaNacimiento());
            inOrden(nodo.getDcho());
        }
    }
}
=======
    public String obtenerContactosString() {
        StringBuilder stringBuilder = new StringBuilder();
        obtenerContactosString(this.raiz, stringBuilder);
        return stringBuilder.toString();
    }

    private void obtenerContactosString(NodoContacto nodo, StringBuilder stringBuilder) {
        if (nodo != null) {
            obtenerContactosString(nodo.getIzdo(), stringBuilder);
            stringBuilder.append("Nombre: ").append(nodo.getContacto().getNombre())
                    .append(", Teléfono: ").append(nodo.getContacto().getTelefono())
                    .append(", Correo: ").append(nodo.getContacto().getCorreoElectronico())
                    .append(", Fecha de Nacimiento: ").append(nodo.getContacto().getFechaNacimiento())
                    .append("\n");
            obtenerContactosString(nodo.getDcho(), stringBuilder);
        }
    }

    public void mostrarContactos() {
    }
}
>>>>>>> 199b9fb (Initial commit)
