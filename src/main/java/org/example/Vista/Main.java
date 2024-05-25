package org.example.Vista;

import org.example.Modelo.Contacto;
import org.example.Servicio.Agenda;

import java.time.LocalDate;

public class Main {
        public static void main(String[] args) {
                Agenda agenda = new Agenda();

                // Agregar contactos
                agenda.agregarContacto("Ana", 123456789, "ana@gmail.com", LocalDate.of(1990, 5, 10));
                agenda.agregarContacto("Beatriz", 987654321, "beatriz@gmail.com", LocalDate.of(1988, 8, 15));
                agenda.agregarContacto("Carlos", 456789123, "carlos@gmail.com", LocalDate.of(1992, 2, 20));
                agenda.agregarContacto("Diana", 789123456, "diana@gmail.com", LocalDate.of(1995, 11, 25));
                agenda.agregarContacto("Elena", 654321987, "Elena@gmail.com", LocalDate.of(1993, 6, 30));


                // Mostrar contactos
                System.out.println("Contactos en la agenda:");
                agenda.mostrarContactos();

                // Buscar un contacto
                System.out.println("\nBuscando el contacto:");
                Contacto contacto = agenda.buscarContacto("Carlos");
                if (contacto != null) {
                        System.out.println("Nombre: " + contacto.getNombre() +
                                ", Teléfono: " + contacto.getTelefono() +
                                ", Correo: " + contacto.getCorreoElectronico() +
                                ", Fecha de Nacimiento: " + contacto.getFechaNacimiento());
                } else {
                        System.out.println("Contacto no encontrado.");
                }

                // Eliminar un contacto
                System.out.println("\nEliminando el contacto.");
                agenda.eliminarContacto("Ana");


                // Mostrar contactos después de la eliminación
                System.out.println("Contactos en la agenda después de eliminarlos:");
                agenda.mostrarContactos();
        }
}