package org.example.Persistencia;


import org.example.Servicio.Agenda;

import java.io.*;

public class Persistencia {

    public static void guardarAgenda(Agenda agenda, String archivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(agenda);
        }
    }

    public static Agenda cargarAgenda(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (Agenda) ois.readObject();
        }
    }
}
