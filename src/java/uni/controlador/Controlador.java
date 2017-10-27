/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uni.dao.ProductoDao;
import uni.dao.VentaDao;
import uni.modelo.DetalleVenta;
import uni.modelo.Producto;
import uni.modelo.Venta;

@WebServlet(name = "Controlador",
        urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    //Un metodo que recibe todas las peticiones a si sea GET y POST
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //La accion se va a guardar en un caja de texto oculto que nos dira que accion
        //debemos hacer
        String accion = request.getParameter("accion");
        try {
            switch (accion) {
                case "RegistrarProducto":
                    registrarProducto(request, response);
                    break;
                case "ModificarProducto":
                    actualizarProducto(request, response);
                    break;
                case "AnadirCarrito":
                    añadirCarrito(request, response);
                    break;
                case "RegistrarVenta":
                    registrarVenta(request, response);
                    break;
                case "BorrarSesion":
                    EliminarSesion(request, response);
                    break;
                case "QuitarCarrito":
                    QuitarCarrito(request, response);
                    break;
                default:
                    break;
            }
        } catch (ServletException | IOException | SQLException e) {
        }

    }
    //Metodo que sirve para registrar un producto 

    private void registrarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Producto p = new Producto();
        //request.getParameter --> Sirve para obtener los valores de las cajas de texto
        //p.setCodigoProducto(Integer.parseInt(request.getParameter("txtCodigo")));
        p.setNombre(request.getParameter("txtNombre").toUpperCase());
        p.setPrecio(Double.parseDouble(request.getParameter("txtPrecio")));
        boolean rpta = ProductoDao.insertarProducto(p);//registra producto
        if (rpta) {
            request.setAttribute("mensaje", "Se registró el producto de manera correcta");
        } else {
            request.setAttribute("error", "No se registró el producto");
        }
        RequestDispatcher rd = request.getRequestDispatcher("registrarProducto.jsp");
        rd.forward(request, response);
    }
    //Metodo que sirve para actualizar un producto

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Producto p = new Producto();
        p.setIdProducto(Integer.parseInt(request.getParameter("id")));
        p.setNombre(request.getParameter("txtNombre").toUpperCase());
        p.setPrecio(Double.parseDouble(request.getParameter("txtPrecio")));
        boolean rpta = ProductoDao.actualizarProducto(p);//actualiza producto
        if (rpta) {
            request.setAttribute("mensaje", "Se actualizo el producto de manera correcta");
        } else {
            request.setAttribute("error", "No se actualizo el producto");
        }
        RequestDispatcher rd = request.getRequestDispatcher("actualizarProducto.jsp");
        rd.forward(request, response);
    }
    //Sirve para añadir un detalle al carrito
    //La informacion del carrito de compras se guarda en una sesion

    private void añadirCarrito(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //Obtenemos la sesion actual
        HttpSession sesion = request.getSession();
        ArrayList<DetalleVenta> carrito;
        //Si no existe la sesion creamos el carrito de compras
        if (sesion.getAttribute("carrito") == null) {
            carrito = new ArrayList<>();
        } else {
            carrito = (ArrayList<DetalleVenta>) sesion.getAttribute("carrito");
        }
        //Obtenemos el producto que deseamos añadir al carrito
        Producto p = ProductoDao.obtenerProducto(Integer.parseInt(request.getParameter("txtCodigo")));
        //Creamos un detalle para el carrtio
        DetalleVenta d = new DetalleVenta();
        //Obtenemos los valores de la caja de texto
        d.setIdProducto(Integer.parseInt(request.getParameter("txtCodigo")));
        d.setProducto(p);
        d.setCantidad(Double.parseDouble(request.getParameter("txtCantidad")));
        //Calculamos el descuento, si es sub detalle es mayor a 50 se le hace
        //un descuento del 5% aca es donde se encuentra la logica del negocio
        double subTotal = p.getPrecio() * d.getCantidad();
        if (subTotal > 100) {
            d.setDescuento(subTotal * (5D / 100D));
        } else {
            d.setDescuento(0);
        }
        //Sirva para saber si tenemos agregado el producto al carrito de compras
        int indice = -1;
        //recorremos todo el carrito de compras
        for (int i = 0; i < carrito.size(); i++) {
            DetalleVenta det = carrito.get(i);
            if (det.getIdProducto() == p.getIdProducto()) {
                //Si el producto ya esta en el carrito, obtengo el indice dentro
                //del arreglo para actualizar al carrito de compras
                indice = i;
                break;
            }
        }
        if (indice == -1) {
            //Si es -1 es porque voy a registrar
            carrito.add(d);
        } else {
            //Si es otro valor es porque el producto esta en el carrito
            //y vamos actualizar la 
            carrito.set(indice, d);
        }
        //Actualizamos la sesion del carrito de compras
        sesion.setAttribute("carrito", carrito);
        //Redireccionamos al formulario de culminar la venta
        response.sendRedirect("registraVenta.jsp");
    }
    //Metodo que sirve para registrar toda la venta en la base de datos

    private void registrarVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //activa la sesion
        HttpSession sesion = request.getSession();
        Venta v = new Venta();
        //asigna valores al objeto v
        v.setCliente(request.getParameter("txtCliente").toUpperCase());
        //leer datos de la sesion cariito
        ArrayList<DetalleVenta> detalle =  (ArrayList<DetalleVenta>) sesion.getAttribute("carrito");
        
        boolean rpta = VentaDao.insertarVenta(v, detalle);//registra la vanta
        //sesion.invalidate();
        sesion.setAttribute("carrito", null);
        if (rpta) {
            request.setAttribute("mensaje", "Se registro la venta de manera correcta");
        } else {
            request.setAttribute("error", "No se registro la venta");
        }
        RequestDispatcher rd = request.getRequestDispatcher("registraVenta.jsp");
        rd.forward(request, response);
    }

    private void EliminarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession lo_session = request.getSession();
        lo_session.invalidate();// Se invalida la sesion (Logout)
        response.sendRedirect("LogUsuario.html");
    }

    private void QuitarCarrito(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession();
        ArrayList<DetalleVenta> carrito;
        int Reg = Integer.parseInt(request.getParameter("Codigo"));
        Reg = Reg - 1;
        //Si no existe la sesion creamos al carrito de compras
        if (sesion.getAttribute("carrito") == null) {
            carrito = new ArrayList<>();
        } else {
            carrito = (ArrayList<DetalleVenta>) sesion.getAttribute("carrito");
        }
        //recorremos todo el carrito de compras
        for (int i = 0; i < carrito.size(); i++) {
            if (i == Reg) {
                carrito.remove(i);
            }
        }
        //Actualizamos la sesion del carrito de compras
        sesion.setAttribute("carrito", carrito);
        //Redireccionamos al formulario de culminar la venta
        response.sendRedirect("registraVenta.jsp");
    }

}
