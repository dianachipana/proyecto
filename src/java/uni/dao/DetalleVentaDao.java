package uni.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import uni.database.Conexion;
import uni.modelo.DetalleVenta;

public class DetalleVentaDao {
    //Metodo utilizado para insertar un Detalle de Venta a nuestra Base de datos
    //Obtenemos la conexion de Venta debido a que la clase Venta es la que inicia
    //la transaccion

    public static synchronized boolean insertarDetalleVenta(DetalleVenta varDetalle, Connection cn) {
        CallableStatement cl = null;
        boolean rpta = false;
        try {
            //Nombre del procedimiento almacenado y como espera tres parametros
            //le ponemos 3 interrogantes
            String call = "{CALL sp_detalleventa(?,?,?,?)}";
            //Preparamos la sentecia
            cl = cn.prepareCall(call);
            //Codigo de la venta
            cl.setInt(1, varDetalle.getIdVenta());
            //Codigo del producto
            cl.setInt(2, varDetalle.getIdProducto());
            //La cantidad
            cl.setDouble(3, varDetalle.getCantidad());
            //El descuento
            cl.setDouble(4, varDetalle.getDescuento());
            //Ejecutamos la sentencia y si nos devuelve el valor de 1 es porque
            //registro de forma correcta los datos
            rpta = cl.executeUpdate() == 1;
            Conexion.cerrarCall(cl);
        } catch (SQLException e) {
            Conexion.cerrarCall(cl);
        } catch (Exception e) {
            Conexion.cerrarCall(cl);
        }
        return rpta;
    }
}
