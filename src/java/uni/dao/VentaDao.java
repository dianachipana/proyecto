package uni.dao;

import java.sql.*;
import java.util.ArrayList;
import uni.database.Conexion;
import uni.modelo.DetalleVenta;
import uni.modelo.Producto;
import uni.modelo.Venta;

public class VentaDao {
    //Metodo utilizado para insertar una Venta a nuestra Base de datos

    public static synchronized boolean insertarVenta(Venta varventa, ArrayList<DetalleVenta> detalle) {
        //variables
        Connection cn = null;
        CallableStatement cl = null;
        boolean rpta = false;
        int nroVenta;

        try {
            //Obtenemos la conexion
            cn = Conexion.getConexion();
            //Decimos que vamos a crear una transaccion
            cn.setAutoCommit(false);
            String sql = "Select Valor from Control where parametro='venta'";
            Statement stm1 = cn.createStatement();
            ResultSet rset = stm1.executeQuery(sql);
            rset.next();
            nroVenta = rset.getInt("Valor");
            rset.close();
            //System.out.println("Id venta :" + nroVenta);
            sql = "update Control set Valor=Valor+1 where parametro='venta'";
            stm1 = cn.createStatement();
            stm1.executeUpdate(sql);
            //Nombre del procedimiento almacenado y como espera tres parametros
            //le ponemos 3 interrogantes
            String call = "{CALL sp_venta(?,?)}";

            //Preparamos la sentecia
            cl = cn.prepareCall(call);
            //El siguiente parametro del procedimiento almacenado es el cliente
            cl.setInt(1, nroVenta);
            cl.setString(2, varventa.getCliente());
            //Ejecutamos la sentencia y si nos devuelve el valor de 1 es porque
            //registro de forma correcta los datos
            rpta = cl.executeUpdate() == 1;
            //Codigo que se genero producto de la insercion ---> codigoVenta
            varventa.setIdVenta(nroVenta);

            if (rpta) {
                for (DetalleVenta det : detalle) {
                    //Establecemos al detalle el codigo genero producto de la venta
                    det.setIdVenta(varventa.getIdVenta());
                    //Insertamos el detalle y le pasamos la conexion
                    rpta = DetalleVentaDao.insertarDetalleVenta(det, cn);
                    //Si nos devuelve false salimos del for
                    if (!rpta) {
                        break;
                    }
                }
                if (rpta) {
                    //Confirmamos la transaccion
                    cn.commit();
                } else {
                    //Negamos la transaccion
                    Conexion.deshacerCambios(cn);
                }
            } else {
                //Negamos la transaccion
                Conexion.deshacerCambios(cn);
            }
            stm1.close();
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            Conexion.deshacerCambios(cn);
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (Exception e) {
            Conexion.deshacerCambios(cn);
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        }
        return rpta;
    }

    //Metodo utilizado para obtener todos las ventas de nuestra base de datos
    public static synchronized ArrayList<DetalleVenta> obtenerVentas() {
        //El array que contendra todos nuestros productos
        ArrayList<DetalleVenta> lista = new ArrayList<>();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            String call = "SELECT\n"
                    + "    Venta.idVenta AS idVenta,\n"
                    + "    Venta.cliente AS Cliente, \n"
                    + "    Venta.fecha AS Fecha,\n"
                    + "    DetalleVenta.idProducto AS CodigoProducto, \n"
                    + "    Producto.nombre AS Nombre,\n"
                    + "    Producto.precio AS Precio, \n"
                    + "    DetalleVenta.cantidad AS Cantidad,\n"
                    + "    DetalleVenta.descuento AS Descuento,\n"
                    + "    Producto.precio*DetalleVenta.cantidad AS Parcial,\n"
                    + "    ((Producto.precio*DetalleVenta.cantidad)-DetalleVenta.descuento) AS SubTotal,\n"
                    + "    (SELECT  SUM((DetalleVenta.cantidad * Producto.precio)-DetalleVenta.descuento) AS TotalPagar\n"
                    + "    FROM         \n"
                    + "        DetalleVenta INNER JOIN\n"
                    + "        Producto ON DetalleVenta.idProducto = Producto.idProducto\n"
                    + "    WHERE\n"
                    + "        DetalleVenta.idVenta=Venta.idVenta) AS TotalPagar\n"
                    + "    FROM \n"
                    + "    Venta INNER JOIN DetalleVenta ON Venta.idVenta = DetalleVenta.idVenta\n"
                    + "    INNER JOIN Producto ON DetalleVenta.idProducto = Producto.idProducto\n"
                    + "    ORDER BY\n"
                    + "    CodigoVenta, Nombre";
            cn = Conexion.getConexion();
            cl = cn.prepareCall(call);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            //Consultamos si hay datos para recorrerlo
            //e insertarlo en nuestro array
            while (rs.next()) {
                Venta ven = new Venta();
                Producto pro = new Producto();
                DetalleVenta det = new DetalleVenta();
                ven.setIdVenta(rs.getInt("idVenta"));
                ven.setCliente(rs.getString("Cliente"));
                ven.setFecha(rs.getTimestamp("Fecha"));
                pro.setIdProducto(rs.getInt("idProducto"));
                pro.setNombre(rs.getString("Nombre"));
                pro.setPrecio(rs.getDouble("Precio"));
                det.setIdVenta(rs.getInt("idVenta"));
                det.setIdProducto(rs.getInt("idProducto"));
                det.setCantidad(rs.getDouble("Cantidad"));
                det.setDescuento(rs.getDouble("Descuento"));
                det.setParcial(rs.getDouble("Parcial"));
                det.setSubtotal(rs.getDouble("SubTotal"));
                det.setVenta(ven);
                det.setProducto(pro);
                lista.add(det);
            }
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (Exception e) {
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        }
        return lista;
    }

    public static synchronized Venta obtenerVenta(int codigo) throws SQLException {
        Venta v = new Venta();
        Connection cn = null;
        PreparedStatement pstm;
        ResultSet rs;
        try {
            String query = "select cliente,fecha from venta where IDVENTA=?";
            cn = Conexion.getConexion();
            pstm = cn.prepareStatement(query);
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();
            if (rs.next()) {
                v.setCliente(rs.getString("cliente"));
                v.setFecha(rs.getTimestamp("fecha"));
            }
            rs.close();
            pstm.close();
            //Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            throw e;// Conexion.cerrarConexion(cn);
        } finally {
            Conexion.cerrarConexion(cn);
        }
        return v;
    }
}
