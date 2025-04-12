package Ventanas;
import Elementos.Elementos;
import controlador.AlumnoDAO;
import modelo.Alumno;
import modelo.ResultSetTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

public class Altas extends Elementos implements ActionListener {

    JButton btnAgregar, btnBorrar, btnCancelar;

    JTextField cajaNumControl, cajaNombre, cajaApePat, cajApeMat;

    JComboBox<String> comboSemestre, comboCarrera;

    JTable tabla;

    public Altas() {

        getContentPane().setLayout(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Altas Alumnos");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(null);

        JLabel txtTitulo =  new JLabel("ALTAS ALUMNOS");
        txtTitulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        txtTitulo.setBounds(20, 25, 200,30);

        panelSuperior.setBackground(Color.decode("#24c248"));
        panelSuperior.add(txtTitulo);
        asignarPosicion(panelSuperior, 0, 0, 600, 70);

        JLabel txtNumControl = new JLabel("Número de control");
        asignarPosicion(txtNumControl, 30, 100, 120, 20);

        cajaNumControl = new JTextField();
        asignarPosicion(cajaNumControl, 160, 100, 200, 20);

        JLabel txtNombre = new JLabel("Nombre");
        asignarPosicion(txtNombre, 30, 130, 120, 20);

        cajaNombre = new JTextField();
        asignarPosicion(cajaNombre, 160, 130, 200, 20);

        JLabel txtApePat = new JLabel("Apellido Paterno");
        asignarPosicion(txtApePat, 30, 160, 120, 20);

        cajaApePat = new JTextField();
        asignarPosicion(cajaApePat, 160, 160, 200, 20);

        JLabel txtApeMat = new JLabel("Apellido Materno");
        asignarPosicion(txtApeMat, 30, 190, 120, 20);

        cajApeMat = new JTextField();
        asignarPosicion(cajApeMat, 160, 190, 200,20);

        JLabel txtSemestre = new JLabel("Semestre");
        asignarPosicion(txtSemestre, 30, 220,120, 20);

        comboSemestre = new JComboBox<>();
        comboSemestre.addItem("Elegir Semestre...");

        for(int i = 0; i < 10; i++)
            comboSemestre.addItem(String.valueOf(i+1));

        asignarPosicion(comboSemestre, 160, 220, 150, 20);

        JLabel txtCarrera = new JLabel("Carrera");
        asignarPosicion(txtCarrera, 30, 250, 120, 20);

        comboCarrera = new JComboBox<>();
        comboCarrera.addItem("Elegir Carrera...");
        comboCarrera.addItem("ISC");
        comboCarrera.addItem("IM");
        comboCarrera.addItem("LA");
        comboCarrera.addItem("IIA");
        comboCarrera.addItem("LCP");

        asignarPosicion(comboCarrera, 160, 250, 150, 20);

        btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(this);
        asignarPosicion(btnAgregar, 380, 115, 80, 20);

        btnBorrar = new JButton("Borrar");
        btnBorrar.addActionListener(this);
        asignarPosicion(btnBorrar, 380, 175, 80, 20);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        asignarPosicion(btnCancelar, 380, 235, 90, 20);

        String[][] rowData = {{"1","1","1","1","1","1"}};

        String[] columnNames = {"NO DE CONTROL", "NOMBRE", "AP. PATERNO", "AP. MATERNO", "SEMESTRE", "CARRERA"};

        tabla = new JTable(rowData, columnNames);

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBackground(Color.decode("#d2e2f1"));
        scrollPane.setBounds(10, 280, 580, 200);
        add(scrollPane);

        actualizarTabla(tabla);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        Object componente = e.getSource();

        if(componente == btnAgregar){

            Alumno a = new Alumno(cajaNumControl.getText(),cajaNombre.getText(),cajaApePat.getText(),cajApeMat.getText(),(byte)18, Byte.parseByte(String.valueOf(comboSemestre.getSelectedItem())),String.valueOf(comboCarrera.getSelectedItem()));



            if(alumnoDAO.agregarAlumno(a) == true){

                actualizarTabla(tabla);

                JOptionPane.showMessageDialog(this, "Registro agregado correctamente");

                System.out.println("Registro agregado correctamente");

            }else{

                JOptionPane.showMessageDialog(this, "Error en la Insercción");

            }

        }

        if(componente == btnBorrar)
            restablecer(cajaNumControl, cajaNombre, cajaApePat, cajApeMat, comboSemestre, comboCarrera);


        if(componente == btnCancelar)
            hide();


    }
}
