package Ventanas;

import Elementos.Elementos;
import controlador.AlumnoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Consultas extends Elementos implements ActionListener {

    JRadioButton radioTodos, radioNombre, radioApePat, radioApeMat, radioSemestre, radioCarrera;

    JButton btnBorrar, btnCancelar, btnLogo;

    JTextField cajaNombre, cajaApePat, cajApeMat;

    JComboBox<String> comboSemestre, comboCarrera;

    AlumnoDAO aDAO;

    JTable tabla;

    public Consultas() {

        aDAO = new AlumnoDAO();

        getContentPane().setLayout(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Consultas Alumnos");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(null);

        JLabel txtTitulo =  new JLabel("CONSULTAS ALUMNOS");
        txtTitulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        txtTitulo.setBounds(20, 25, 250,30);

        panelSuperior.setBackground(Color.decode("#3c64f8"));
        panelSuperior.add(txtTitulo);
        asignarPosicion(panelSuperior, 0, 0, 600, 70);

        radioTodos = new JRadioButton("Todos");
        radioTodos.addActionListener(this);
        asignarPosicion(radioTodos, 10, 100, 100,20);

        JLabel txtCriterios = new JLabel("Selecciona criterio de busqueda:");
        asignarPosicion(txtCriterios, 30, 80, 180, 20);

        ImageIcon icono = new ImageIcon("./img/search.png");
        Image imagenAjustada = icono.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon iconoAjustado = new ImageIcon(imagenAjustada);

        btnLogo = new JButton(iconoAjustado);
        btnLogo.addActionListener(this);
        asignarPosicion(btnLogo, 380, 140, 90, 35);

        btnBorrar = new JButton("Borrar");
        btnBorrar.addActionListener(this);
        asignarPosicion(btnBorrar, 380, 200, 80, 20);

        radioNombre = new JRadioButton("Nombre");
        radioNombre.addActionListener(this);
        asignarPosicion(radioNombre, 20, 130, 80,20);

        cajaNombre = new JTextField();
        cajaNombre.setEnabled(false);
        asignarPosicion(cajaNombre, 160, 130, 200, 20);

        radioApePat = new JRadioButton("Apellido Paterno");
        radioApePat.addActionListener(this);
        asignarPosicion(radioApePat, 20, 160, 120,20);

        cajaApePat = new JTextField();
        cajaApePat.setEnabled(false);
        asignarPosicion(cajaApePat, 160, 160, 200, 20);

        radioApeMat = new JRadioButton("Apellido Materno");
        radioApeMat.addActionListener(this);
        asignarPosicion(radioApeMat, 20, 190, 120,20);

        cajApeMat = new JTextField();
        cajApeMat.setEnabled(false);
        asignarPosicion(cajApeMat, 160, 190, 200,20);

        radioSemestre = new JRadioButton("Semestre");
        radioSemestre.addActionListener(this);
        asignarPosicion(radioSemestre, 20, 220, 80,20);

        comboSemestre = new JComboBox<>();

        for(int i = 0; i < 10; i++)
            comboSemestre.addItem(String.valueOf(i+1));

        comboSemestre.setEnabled(false);
        asignarPosicion(comboSemestre, 160, 220, 150, 20);

        radioCarrera = new JRadioButton("Carrera");
        radioCarrera.addActionListener(this);

        asignarPosicion(radioCarrera, 20, 250, 100,20);

        comboCarrera = new JComboBox<>();
        comboCarrera.addItem("ISC");
        comboCarrera.addItem("IM");
        comboCarrera.addItem("LA");
        comboCarrera.addItem("IIA");
        comboCarrera.addItem("LCP");
        comboCarrera.setEnabled(false);
        asignarPosicion(comboCarrera, 160, 250, 150, 20);

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

    public void asignarPosicion(JComponent componente, int x, int y, int w, int h){

        componente.setBounds(x, y, w, h);

        add(componente);

    }


    public void todosElegidos(boolean op){

        radioNombre.setSelected(op);
        radioNombre.setEnabled(!op);
        cajaNombre.setEnabled(op);
        radioApePat.setSelected(op);
        radioApePat.setEnabled(!op);
        cajaApePat.setEnabled(op);
        radioApeMat.setSelected(op);
        radioApeMat.setEnabled(!op);
        cajApeMat.setEnabled(op);
        radioSemestre.setSelected(op);
        radioSemestre.setEnabled(!op);
        comboSemestre.setEnabled(op);
        radioCarrera.setSelected(op);
        radioCarrera.setEnabled(!op);
        comboCarrera.setEnabled(op);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnLogo){

            String consulta="WHERE ";

            if(radioNombre.isSelected())
                consulta+="Nombre = '"+cajaNombre.getText()+"' ";

            if(radioTodos.isSelected())
                consulta+="AND ";


            if(radioApeMat.isSelected())
                consulta+="SegundoApe = '"+cajApeMat.getText()+"' ";

            if(radioTodos.isSelected())
                consulta+="AND ";

            if(radioApePat.isSelected())
                consulta+="Primer_ape = '"+cajaApePat+"' ";

            if(radioTodos.isSelected())
                consulta+="AND ";

            if(radioCarrera.isSelected())
                consulta+="Carrera = '"+comboCarrera.getSelectedItem()+"' ";

            if(radioTodos.isSelected())
                consulta+="AND ";

            if(radioSemestre.isSelected())
                consulta+="Semestre = '"+comboSemestre.getSelectedItem()+"' ";


            actualizarTablaConsulta(tabla,consulta);


        }

        if(e.getSource()==btnBorrar){
            restablecer(radioTodos,cajaApePat,cajaNombre,cajApeMat);
            todosElegidos(false);
        }

        if(e.getSource()==btnCancelar)
            this.hide();


        Object componente = e.getSource();

        if (componente == radioTodos){

            if(radioTodos.isSelected()){

                todosElegidos(true);
            }else{

                todosElegidos(false);

            }

        }

        if(radioNombre.isSelected()){

            cajaNombre.setEnabled(true);

        }else{

            cajaNombre.setEnabled(false);

        }

        if(radioApePat.isSelected()){

            cajaApePat.setEnabled(true);

        }else{

            cajaApePat.setEnabled(false);

        }

        if(radioApeMat.isSelected()){

            cajApeMat.setEnabled(true);

        }else{

            cajApeMat.setEnabled(false);

        }

        if(radioSemestre.isSelected()){

            comboSemestre.setEnabled(true);

        }else{

            comboSemestre.setEnabled(false);

        }

        if(radioCarrera.isSelected()){

            comboCarrera.setEnabled(true);

        }else{

            comboCarrera.setEnabled(false);

        }

    }
}
