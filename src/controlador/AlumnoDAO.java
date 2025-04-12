package controlador;

import ConexionBD.ConexionBD;
import modelo.Alumno;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//DAO - Data Access Object
public class AlumnoDAO {

    ConexionBD conexionBD = new ConexionBD();

    // ========================= Métodos ABCC ===============

    //====================Altas====================

    public boolean agregarAlumno(Alumno alumno){

        // INSERT INTO alumnos VALUES();

        //INSERT INTO Alumnos VALUES('1','1','1','1',1,1,'1');

        String sql = "INSERT INTO Alumnos VALUES('"+alumno.getNumControl()+"','"+alumno.getNombre()+"','"+alumno.getPrimerAp()+"','"+alumno.getSegundoAp()+"',"+alumno.getEdad()+","+alumno.getSemestre()+",'"+alumno.getCarrera()+"')";

        return conexionBD.ejecutarInstruccionLMD(sql);

    }

    //=============================BAJAS===============================

    public boolean eliminarAlumno(String numControl){

        String sql = "DELETE FROM Alumnos WHERE Num_Control='"+numControl+"'";

        return conexionBD.ejecutarInstruccionLMD(sql);

    }

    //=============================CAMBIOS===============================

    public boolean editarAlumno(Alumno alumno){


        String sql = "UPDATE Alumnos SET Nombre='"+alumno.getNombre()+"', Primer_ape='"+alumno.getPrimerAp()+"', SegundoApe='"+alumno.getSegundoAp()+"', Edad=50, Semestre="+alumno.getSemestre()+", Carrera='"+alumno.getCarrera()+"' WHERE Num_Control = '"+alumno.getNumControl()+"'";

        return conexionBD.ejecutarInstruccionLMD(sql);

    }

    //============================CONSULTAS===============================

    public Alumno mostrarAlumno(String filtro){

        String sql = "SELECT * FROM Alumnos WHERE Num_control='"+filtro+"'";

        ResultSet rs = conexionBD.ejecutarIstruccionSQL(sql);

        Alumno a = null;


        try {

            if(rs.next()) {

                String nc = rs.getString(1);

                String n = rs.getString("Nombre");

                String pa = rs.getString(3);

                String sa = rs.getString("Segundo_Ap");

                byte e = rs.getByte(5);

                byte s = rs.getByte(6);

                String c = rs.getString(7);

                a = new Alumno(nc, n, pa, sa, e, s, c);
            }else{
                System.out.println("No existe");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return a;

    }

    public ArrayList mostrarAlumnos(String filtro){

        ArrayList<Alumno> listaALumnos = new ArrayList<>();

        String sql = "SELECT * FROM Alumnos";

        ResultSet rs = conexionBD.ejecutarIstruccionSQL(sql);

        try {

            rs.next();

            do {

                String nc = rs.getString(1);

                String n =  rs.getString("Nombre");

                String pa = rs.getString(3);

                String sa = rs.getString("SegundoApe");

                byte e = rs.getByte(5);

                byte s = rs.getByte(6);

                String c = rs.getString(7);

                Alumno a = new Alumno(nc, n, pa, sa, e, s, c);

                listaALumnos.add(a);

            }while (rs.next());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaALumnos;

    }

}
