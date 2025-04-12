package ConexionBD;
import java.sql.*;


public class ConexionBD {

    private Connection conexion;

    private Statement stm; //preparedStatement ES MEJOR YA QUE EVITA SQL Injection

    private ResultSet rs;


    public ConexionBD() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            //127.0.0.1

            String URL = "jdbc:mysql://localhost:3306/dd_Topicos_2025";

            conexion = DriverManager.getConnection(URL, "root", "1234");

            System.out.println("Casi soy ingeniero inmortal");

        } catch (ClassNotFoundException e) {

            System.out.println("Error en el conector o Driver");

        } catch (SQLException e) {

            //e.printStackTrace();

            System.out.println("Error en la conexion a MySQL");

        }

    }
    //CRUD craete read update delete

    //Metodo para los procesos de ABCC (altas bajas consultas)

    public boolean ejecutarInstruccionLMD(String sql){

        boolean res = false;

        try {
            stm = conexion.createStatement();

            if(stm.executeUpdate(sql) >= 1){
                res = true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Error en la ejecucion de la instruccion SQL");
        }

        return res;

    }

    //Método para consultas

    public ResultSet ejecutarIstruccionSQL(String sql){

        rs = null;



        try {
            stm = conexion.createStatement();

            rs = stm.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println("Error en la ejecucion de la instruccion SQL");
        }

        return rs;

    }

    public static void main(String[] arg){

        System.out.println("Magia magia con INTELLIJ");

        new ConexionBD();

    }

}
