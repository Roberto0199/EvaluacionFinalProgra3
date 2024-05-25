package org.example.Vista;

import org.example.Modelo.Contacto;
import org.example.Persistencia.Persistencia;
import org.example.Servicio.Agenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class Main {

        private static Agenda agenda = new Agenda();

        public static void main(String[] args) {
                JFrame frame = new JFrame("Agenda");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 200);
                frame.setLayout(new GridLayout(3, 2));

                JButton agregarButton = new JButton("Agregar contacto");
                agregarButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                agregarContacto(frame);
                        }
                });
                frame.add(agregarButton);

                JButton buscarButton = new JButton("Buscar contacto");
                buscarButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                buscarContacto(frame);
                        }
                });
                frame.add(buscarButton);

                JButton mostrarButton = new JButton("Mostrar todos los contactos");
                mostrarButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                mostrarContactos(frame);
                        }
                });
                frame.add(mostrarButton);

                JButton guardarButton = new JButton("Guardar agenda");
                guardarButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                guardarAgenda();
                        }
                });
                frame.add(guardarButton);

                JButton cargarButton = new JButton("Cargar agenda");
                cargarButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                cargarAgenda(frame);
                        }
                });
                frame.add(cargarButton);

                JButton salirButton = new JButton("Salir");
                salirButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                System.exit(0);
                        }
                });
                frame.add(salirButton);

                frame.setVisible(true);
        }

        private static void agregarContacto(JFrame frame) {
                JTextField nombreField = new JTextField(10);
                JTextField telefonoField = new JTextField(10);
                JTextField correoField = new JTextField(10);
                JTextField fechaField = new JTextField(10);

                JPanel myPanel = new JPanel();
                myPanel.setLayout(new GridLayout(5, 2));
                myPanel.add(new JLabel("Nombre:"));
                myPanel.add(nombreField);
                myPanel.add(new JLabel("Teléfono:"));
                myPanel.add(telefonoField);
                myPanel.add(new JLabel("Correo electrónico:"));
                myPanel.add(correoField);
                myPanel.add(new JLabel("Fecha de nacimiento (yyyy-mm-dd):"));
                myPanel.add(fechaField);

                int result = JOptionPane.showConfirmDialog(frame, myPanel,
                        "Por favor ingrese los datos del contacto", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                        String nombre = nombreField.getText();
                        long telefono = Long.parseLong(telefonoField.getText());
                        String correoElectronico = correoField.getText();
                        LocalDate fechaNacimiento = LocalDate.parse(fechaField.getText());

                        agenda.agregarContacto(nombre, telefono, correoElectronico, fechaNacimiento);
                        JOptionPane.showMessageDialog(frame, "Contacto agregado correctamente.");
                }
        }

        private static void buscarContacto(JFrame frame) {
                JTextField criterioField = new JTextField(10);
                JPanel myPanel = new JPanel();
                myPanel.setLayout(new GridLayout(2, 2));
                myPanel.add(new JLabel("Ingrese el nombre, teléfono o correo del contacto:"));
                myPanel.add(criterioField);

                int result = JOptionPane.showConfirmDialog(frame, myPanel,
                        "Buscar contacto", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                        String criterio = criterioField.getText();
                        Contacto contacto = agenda.buscarContacto(criterio);
                        if (contacto != null) {
                                JOptionPane.showMessageDialog(frame, "Contacto encontrado:\n" +
                                        "Nombre: " + contacto.getNombre() + "\n" +
                                        "Teléfono: " + contacto.getTelefono() + "\n" +
                                        "Correo electrónico: " + contacto.getCorreoElectronico() + "\n" +
                                        "Fecha de nacimiento: " + contacto.getFechaNacimiento());
                        } else {
                                JOptionPane.showMessageDialog(frame, "Contacto no encontrado.");
                        }
                }
        }

        private static void mostrarContactos(JFrame frame) {
                JTextArea textArea = new JTextArea(10, 30);
                JScrollPane scrollPane = new JScrollPane(textArea);

                String contactos = agenda.obtenerContactosString();
                textArea.setText(contactos);

                JOptionPane.showMessageDialog(frame, scrollPane, "Contactos en la agenda", JOptionPane.PLAIN_MESSAGE);
        }

        private static void guardarAgenda() {
                try {
                        Persistencia.guardarAgenda(agenda, "agenda.dat");
                        JOptionPane.showMessageDialog(null, "Agenda guardada correctamente.");
                } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error al guardar la agenda: " + e.getMessage());
                }
        }

        private static void cargarAgenda(JFrame frame) {
                try {
                        Agenda agendaDeserializada = Persistencia.cargarAgenda("agenda.dat");
                        agenda = agendaDeserializada; // Asignar la agenda cargada a la variable estática
                        JOptionPane.showMessageDialog(frame, "Agenda cargada correctamente.");
                        agenda.mostrarContactos(); // Opcional: Mostrar los contactos cargados
                } catch (IOException | ClassNotFoundException e) {
                        JOptionPane.showMessageDialog(frame, "Error al cargar la agenda: " + e.getMessage());
                }
        }
}
