package Ventanas;

import ConexionBD.ConexionBD;
import Elementos.Elementos;
import modelo.Alumno;
import modelo.ResultSetTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bajas extends Elementos implements ActionListener {

    JButton btnEliminar, btnBorrar, btnCancelar, btnLogo;

    JTextField cajaNumControl, cajaNombre, cajaApePat, cajApeMat;

    JComboBox<String> comboSemestre, comboCarrera;

    JTable tabla;

    public Bajas() {

        getContentPane().setLayout(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Bajas Alumnos");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(null);

        JLabel txtTitulo =  new JLabel("BAJAS ALUMNOS");
        txtTitulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        txtTitulo.setBounds(20, 25, 200,30);

        panelSuperior.setBackground(Color.decode("#cf2008"));
        panelSuperior.add(txtTitulo);
        asignarPosicion(panelSuperior, 0, 0, 600, 70);

        JLabel txtNumControl = new JLabel("Número de control");
        asignarPosicion(txtNumControl, 30, 90, 120, 20);

        cajaNumControl = new JTextField();
        asignarPosicion(cajaNumControl, 160, 90, 100, 20);

        ImageIcon icono = new ImageIcon("./img/search.png");
        Image imagenAjustada = icono.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon iconoAjustado = new ImageIcon(imagenAjustada);

        btnLogo = new JButton(iconoAjustado);
        btnLogo.addActionListener(this);
        asignarPosicion(btnLogo, 270, 80, 90, 35);

        btnBorrar = new JButton("Borrar");
        btnBorrar.addActionListener(this);
        asignarPosicion(btnBorrar, 380, 90, 80, 20);

        JLabel txtNombre = new JLabel("Nombre");
        asignarPosicion(txtNombre, 30, 130, 120, 20);

        cajaNombre = new JTextField();
        cajaNombre.setEnabled(false);
        asignarPosicion(cajaNombre, 160, 130, 200, 20);

        JLabel txtApePat = new JLabel("Apellido Paterno");
        asignarPosicion(txtApePat, 30, 160, 120, 20);
        cajaApePat = new JTextField();
        cajaApePat.setEnabled(false);
        asignarPosicion(cajaApePat, 160, 160, 200, 20);

        JLabel txtApeMat = new JLabel("Apellido Materno");
        asignarPosicion(txtApeMat, 30, 190, 120, 20);
        cajApeMat = new JTextField();
        cajApeMat.setEnabled(false);
        asignarPosicion(cajApeMat, 160, 190, 200,20);

        JLabel txtSemestre = new JLabel("Semestre");
        asignarPosicion(txtSemestre, 30, 220,120, 20);
        comboSemestre = new JComboBox<>();


        for(int i = 0; i < 10; i++)
            comboSemestre.addItem(String.valueOf(i+1));

        comboSemestre.setEnabled(false);
        asignarPosicion(comboSemestre, 160, 220, 150, 20);

        JLabel txtCarrera = new JLabel("Carrera");
        asignarPosicion(txtCarrera, 30, 250, 120, 20);

        comboCarrera = new JComboBox<>();
        comboCarrera.addItem("ISC");
        comboCarrera.addItem("IM");
        comboCarrera.addItem("LA");
        comboCarrera.addItem("IIA");
        comboCarrera.addItem("LCP");
        comboCarrera.setEnabled(false);
        asignarPosicion(comboCarrera, 160, 250, 150, 20);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        asignarPosicion(btnEliminar, 380, 175, 90, 20);

        btnCancelar = new JButton("Cancelar");
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

        if(componente == btnLogo)
            obtnerDatos();


        if(componente == btnCancelar)
            hide();


        if(componente == btnEliminar){


            if(alumnoDAO.eliminarAlumno(cajaNumControl.getText()) == true){

                actualizarTabla(tabla);

                System.out.println("Registro Modificado correctamente");

                JOptionPane.showMessageDialog(this, "Registro eliminado con exito");

            }else{
                JOptionPane.showMessageDialog(this, "Alumno no encontrado\n Verifique los datos");

            }

        }

        if(componente == btnBorrar)
            restablecer(cajaNumControl,cajaNombre, cajaApePat, cajApeMat, comboCarrera, comboSemestre);


    }

    public void obtnerDatos() {

        ConexionBD conexionBD = new ConexionBD();

        String sql = "SELECT * FROM Alumnos WHERE Num_Control='"+cajaNumControl.getText()+"'";

        ResultSet rs = conexionBD.ejecutarIstruccionSQL(sql);

        try {

            rs.next();
            cajaNombre.setText(rs.getString("Nombre"));
            cajaApePat.setText(rs.getString(3));
            cajApeMat.setText(rs.getString(4));
            comboSemestre.setSelectedItem(String.valueOf(rs.getByte(6)));
            comboCarrera.setSelectedItem(rs.getString(7));

            actualizarTabla(tabla);
        } catch (SQLException e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(this,"El registro no fue encontrado");

            restablecer(cajaNombre, cajaApePat, cajApeMat, comboCarrera, comboSemestre);

        }

    }

}
