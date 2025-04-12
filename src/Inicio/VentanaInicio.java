package Inicio;

import Ventanas.Altas;
import Ventanas.Bajas;
import Ventanas.Consultas;
import Ventanas.Modificaciones;

import controlador.AlumnoDAO;
import modelo.Alumno;
import modelo.ResultSetTableModel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class VentanaInicio extends JFrame implements ActionListener, KeyListener {

    AlumnoDAO aDAO;

    JMenu menuAlumnos, menuAsignaturas;

    JMenuItem altas, bajas, cambios, consultas;

    JInternalFrame IF_Altas;

    public VentanaInicio() {

        aDAO = new AlumnoDAO();

        getContentPane().setLayout(new BorderLayout());;

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("Proyecto ABCC");

        setSize(700, 700);

        setVisible(true);

        setLocationRelativeTo(null);

        //Widgets

        //---------------Menú---------------

        JMenuBar menuBar = new JMenuBar();

        menuAlumnos = new JMenu("Alumnos");

        altas = new JMenuItem("Agregar");
        altas.addActionListener(this);

        bajas = new JMenuItem("Eliminar");
        bajas.addActionListener(this);

        cambios = new JMenuItem("Combios");
        cambios.addActionListener(this);

        consultas = new JMenuItem("Consultas");
        consultas.addActionListener(this);

        menuAlumnos.add(altas);
        menuAlumnos.add(bajas);
        menuAlumnos.add(cambios);
        menuAlumnos.add(consultas);


        menuBar.add(menuAlumnos);


        setJMenuBar(menuBar);



    }

    @Override
    public void actionPerformed(ActionEvent e) {



        if(e.getSource() == altas) {

            SwingUtilities.invokeLater(new Runnable() { //Siemnpre agregar ese codigo

                @Override
                public void run() {

                    new Altas();

                }
            });

        }

        if(e.getSource() == bajas) {

            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {

                    new Bajas();

                }
            });

        }

        if(e.getSource() == cambios) {

            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {

                    new Modificaciones();

                }
            });

        }

        if(e.getSource() == consultas) {

            SwingUtilities.invokeLater(new Runnable() { //Siemnpre agregar ese codigo

                @Override
                public void run() {

                    new Consultas();

                }
            });

        }

    }

    public static void main(String[] args) {

        AlumnoDAO alumnoDAO = new AlumnoDAO();


        //============ Prueba bajas ==========




        //============ Prueba Combios ==========

        Alumno a1 = new Alumno("2","Luke","Skywalker","-", (byte)100, (byte)10, "ISC" );

        if(alumnoDAO.editarAlumno(a1) == true){

            System.out.println("Registro Modificado correctamente");

        }else{

            System.out.println("Error en la Modificacion");

        }

        //============ Prueba Consultas ==========

        ArrayList<Alumno> lista = alumnoDAO.mostrarAlumnos("");

        for(Alumno alumno: lista){

            System.out.println(alumno);

        }

        SwingUtilities.invokeLater(new Runnable() { //Siemnpre agregar ese codigo

            @Override
            public void run() {

                new VentanaInicio();

            }
        });

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void actualizarTabla(JTable tabla){

        final String CONTROLADOR_JDBC = "com.mysql.cj.jdbc.Driver";

        final String URL = "jdbc:mysql://localhost:3306/bd_Topicos_2025";

        final String CONSULTA = "SELECT * FROM Alumnos";

        try {
            ResultSetTableModel modelo = new ResultSetTableModel(CONTROLADOR_JDBC, URL, CONSULTA);

            tabla.setModel(modelo);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);

        }

    }


}
