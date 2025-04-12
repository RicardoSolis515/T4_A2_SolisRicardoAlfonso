package Ventanas;

import ConexionBD.ConexionBD;
import Elementos.Elementos;
import modelo.Alumno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modificaciones extends Elementos implements ActionListener {


    JButton btnGuardar, btnBorrar, btnCancelar, btnLogo;

    JTextField cajaNumControl, cajaNombre, cajaApePat, cajApeMat;

    JComboBox<String> comboSemestre, comboCarrera;

    JTable tabla;

    public Modificaciones() {

        getContentPane().setLayout(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Modificaciones Alumnos");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(null);

        JLabel txtTitulo =  new JLabel("MODIFICACIONES ALUMNOS");
        txtTitulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        txtTitulo.setBounds(20, 25, 300,30);

        panelSuperior.setBackground(Color.decode("#fb9623"));
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
        asignarPosicion(btnBorrar, 380, 90, 80, 20);

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

        for(int i = 0; i < 10; i++)
            comboSemestre.addItem(String.valueOf(i+1));

        asignarPosicion(comboSemestre, 160, 220, 150, 20);

        JLabel txtCarrera = new JLabel("Carrera");
        asignarPosicion(txtCarrera, 30, 250, 120, 20);

        comboCarrera = new JComboBox<>();
        comboCarrera.addItem("ISC");
        comboCarrera.addItem("IM");
        comboCarrera.addItem("LA");
        comboCarrera.addItem("IIA");
        comboCarrera.addItem("LCP");
        asignarPosicion(comboCarrera, 160, 250, 150, 20);

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(this);
        asignarPosicion(btnGuardar, 380, 175, 140, 20);

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

    public void asignarPosicion(JComponent componente, int x, int y, int w, int h){

        componente.setBounds(x, y, w, h);

        add(componente);

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

            // byte e = rs.getByte(5);

            comboSemestre.setSelectedItem(String.valueOf(rs.getByte(6)));

            comboCarrera.setSelectedItem(rs.getString(7));

        } catch (SQLException e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(this,"El registro no fue encontrado");

            restablecer(cajaNombre, cajaApePat, cajApeMat, comboCarrera, comboSemestre);

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object componente = e.getSource();

        if(componente == btnLogo){

            obtnerDatos();

        }

        if(componente == btnCancelar){
            hide();
        }

        if(componente == btnGuardar){

            Alumno a1 = new Alumno(cajaNumControl.getText(),cajaNombre.getText(),cajaApePat.getText(),cajApeMat.getText(),(byte)0, Byte.parseByte(String.valueOf(comboSemestre.getSelectedItem())),String.valueOf(comboCarrera.getSelectedItem()));

            if(alumnoDAO.editarAlumno(a1) == true){

                actualizarTabla(tabla);

                System.out.println("Registro Modificado correctamente");

                JOptionPane.showMessageDialog(this, "Se ha actualizado el registro correctamente");

            }else{

                System.out.println("Error en la Modificacion");

                JOptionPane.showMessageDialog(this, "Ocurrio un error en la actualización del registro");

            }

        }

    }
}

