package uni.dao;

import java.sql.*;
import java.util.ArrayList;
import uni.database.Conexion;
import uni.modelo.Producto;

public class ProductoDao {
    //Metodo utilizado para insertar un Producto a nuestra Base de datos

    public static synchronized boolean insertarProducto(Producto varproducto) {
        Connection cn = null;
        PreparedStatement pstm;
        boolean rpta = false;
        int CodProducto;
        try {
            
            //Obtenemos la conexion
            cn = Conexion.getConexion();
            //Decimos que vamos a crear una transaccion
            cn.setAutoCommit(false);
            //genero el autonumerico del codigo del producto
            String sqlProd = "Select Valor from Control where parametro='producto'";
            Statement stm1 = cn.createStatement();
            ResultSet rset = stm1.executeQuery(sqlProd);
            rset.next();
            CodProducto = rset.getInt("Valor");
            rset.close();
            //System.out.println("Id venta :" + CodProducto);
            sqlProd = "update Control set Valor=Valor+1 where parametro='producto'";
            stm1 = cn.createStatement();
            stm1.executeUpdate(sqlProd);
            //Nombre del comando y como espera tres parametros
            //le ponemos 3 interrogantes
            String sql = "insert into producto(idProducto,nombre,precio,stock) values(?,?,?,10)";
            //Preparamos la sentecia
            pstm = cn.prepareStatement(sql);
            //El primer parametro del comando es el codigo
            pstm.setInt(1, CodProducto);
            //El siguiente parametro del parametro del comando es el nombre
            pstm.setString(2, varproducto.getNombre());
            //Y por ultimo el precio
            pstm.setDouble(3, varproducto.getPrecio());
            //Ejecutamos la sentencia y si nos devuelve el valor de 1 es porque
            //registro de forma correcta los datos
            rpta = pstm.executeUpdate() == 1;
            if (rpta) {
                //Confirmamos la transaccion
                cn.commit();
            } else {
                //Negamos la transaccion
                Conexion.deshacerCambios(cn);
            }
            pstm.close();
            Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            Conexion.deshacerCambios(cn);
            Conexion.cerrarConexion(cn);
        } catch (Exception e) {
            Conexion.deshacerCambios(cn);
            Conexion.cerrarConexion(cn);
        }
        return rpta;
    }

    public static synchronized boolean actualizarProducto(Producto varproducto) {
        Connection cn = null;
        PreparedStatement pstm;
        boolean rpta = false;
        try {
            //Nombre del comando  y como espera tres parametros
            //le ponemos 3 interrogantes
            String sql = "update producto set nombre=?,precio=? where idProducto=?";
            //Obtenemos la conexion
            cn = Conexion.getConexion();
            //Decimos que vamos a crear una transaccion
            cn.setAutoCommit(false);
            //Preparamos la sentecia
            pstm = cn.prepareStatement(sql);
            //El siguiente parametro del parametro del comando es el nombre
            pstm.setString(1, varproducto.getNombre());
            //Y por ultimo el precio
            pstm.setDouble(2, varproducto.getPrecio());
            //El primer parametro del comando es el codigo
            pstm.setInt(3, varproducto.getIdProducto());
            //Ejecutamos la sentencia y si nos devuelve el valor de 1 es porque
            //registro de forma correcta los datos
            rpta = pstm.executeUpdate() == 1;
            if (rpta) {
                //Confirmamos la transaccion
                cn.commit();
            } else {
                //Negamos la transaccion
                Conexion.deshacerCambios(cn);
            }
            pstm.close();
            Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            Conexion.deshacerCambios(cn);
            Conexion.cerrarConexion(cn);
        } catch (Exception e) {
            Conexion.deshacerCambios(cn);
            Conexion.cerrarConexion(cn);
        }
        return rpta;
    }
    //Metodo utilizado para obtener todos los productos de nuestra base de datos

    public static synchronized ArrayList<Producto> obtenerProducto() {
        //El array que contendra todos nuestros productos
        ArrayList<Producto> lista = new ArrayList<>();
        Connection cn = null;
        ResultSet rs;
        try {
            String query = "select idProducto,nombre,precio from producto order by nombre";
            cn = Conexion.getConexion();
            Statement stm = cn.createStatement();
            rs = stm.executeQuery(query);
            //Consultamos si hay datos para recorrerlo
            //e insertarlo en nuestro array
            while (rs.next()) {
                Producto p = new Producto();
                //Obtenemos los valores de la consulta y creamos
                //nuestro objeto producto
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                //Lo adicionamos a nuestra lista
                lista.add(p);
            }
            rs.close();
            stm.close();
            // Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            //Conexion.cerrarConexion(cn);
        } finally {
            try {
                Conexion.cerrarConexion(cn);
            } catch (Exception e) {
            }
        }
        return lista;
    }

    //Metodo utilizado para obtener todos los productos de nuestra base de datos
    public static synchronized Producto obtenerProducto(int codigo) throws SQLException {
        Producto p = new Producto();
        Connection cn = null;
        PreparedStatement pstm;
        ResultSet rs;
        try {
            String query = "select idProducto,nombre,precio from producto where idProducto=?";
            cn = Conexion.getConexion();
            pstm = cn.prepareStatement(query);
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();
            if (rs.next()) {
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
            }
            rs.close();
            pstm.close();
            //Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            throw e;// Conexion.cerrarConexion(cn);
        } finally {
            Conexion.cerrarConexion(cn);
        }
        return p;
    }
}
