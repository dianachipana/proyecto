package uni.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

    //La url con la cual nos conectariamos a la base de datos
    static String url = "jdbc:oracle:thin:@localhost:1521:XE";
    //El usuario de la base de datos
    static String user = "ventas";
    //La clave del usuario de la base de datos
    static String password = "admin";

    //Metodo para obtener la conexion con la base de datos
    public static synchronized Connection getConexion() {
        //sincronizar si se llama al metodo getconnection otro usuario no lo puede llamar hasta que no termine el proceso
        Connection cn = null;
        try {
            //Cargamos el driver y le decimos que vamos a usar
            //una conexion con oracle
            Class.forName("oracle.jdbc.OracleDriver");
            //Obtenemos la conexion
            cn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            cn = null;
        } finally {
            return cn;
        }
    }

    //Metodo utilizado para cerrar el callablestatemente
    public static synchronized void cerrarCall(CallableStatement cl) {
        try {
            cl.close();
        } catch (SQLException e) {
        }
    }

    //Metodo utilizado para cerrar el resulset de datos
    public static synchronized void cerrarConexion(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
        }
    }

    //Metodo utilizado para cerrar la conexion
    public static synchronized void cerrarConexion(Connection cn) {
        try {
            cn.close();
        } catch (SQLException e) {
        }
    }

    //Metodo utilizado para deshacer los cambios en la base de datos
    public static synchronized void deshacerCambios(Connection cn) {
        try {
            cn.rollback();
        } catch (SQLException e) {
        }
    }

}
