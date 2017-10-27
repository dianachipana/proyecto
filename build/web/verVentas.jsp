<%@page import="uni.dao.VentaDao"%>
<%@page import="uni.modelo.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*" %>
<%@page import="uni.dao.ProductoDao"%>
<%@page import="uni.dao.DetalleVentaDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>:::Bienvenido al Sistema de Ventas On-line ABC::: </title>
        <link href="Recursos/css/bootstrap.css" rel="stylesheet">
        <link href="Recursos/css/forms.css" rel="stylesheet">
        <link href="Recursos/css/styles.css" rel="stylesheet">
        <link href="Recursos/css/bootstrap-responsive.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" media="screen" href="Recursos/css/estilospropios.css" />
        <script type="text/javascript" src="Recursos/js/function.js"></script>
        <script type="text/javascript" src="Recursos/js/jquery.js"></script>
        <script type="text/javascript" src="Recursos/js/validar/jquery.validate.js" ></script>
        <script type="text/javascript" src="Recursos/js/validar/reglas.js" ></script>
    </head>
    <body>
        <div class="row-fluid">
            <div class="span11">
                <div class="widget-block">
                    <div class="widget-content">
                        <div class="widget-box">
                            <%-- En el action del formulario le decimos que llama al Controlador --%>
                            <br>
                            <form class="form-horizontal white-box validform formulario" id="formulario" name="formulario" method="post" action="Controlador">
                                <input type="hidden" name="accion" value="RegistrarVenta" />
                                <fieldset>
                                <legend>&nbsp;&nbsp;&nbsp;&nbsp;LISTA DE VENTAS </legend>
                                <table width="100%">
                                    <tr>
                                        <td bgcolor="#021E4F"><div style="text-align: left; color : #fff;">ID VENTA</div></td>
                                        <td bgcolor="#021E4F"><div style="text-align: left; color : #fff;">CLIENTE</div></td>
                                        <td bgcolor="#021E4F"><div style="text-align: left; color : #fff;">PRODUCTO</div></td>
                                        <td bgcolor="#021E4F"><div style="text-align: right; color : #fff;">CANTIDAD</div></td>
                                        <td bgcolor="#021E4F"><div style="text-align: right; color : #fff;">PRECIO</div></td>
                                        <td bgcolor="#021E4F"><div style="text-align: right; color : #fff;">DCTO</div></td>
                                        <td bgcolor="#021E4F"><div style="text-align: right; color : #fff;">SUB TOTAL</div></td>
                                    </tr>
                                    <%-- Los productos que tenemos en la sesion que se llama carrito --%>
                                    <%
                                        ArrayList<DetalleVenta> lista = VentaDao.obtenerVentas();
                                        int cont = 0;
                                        ProductoDao pd = new ProductoDao();
                                        VentaDao vt = new VentaDao();
                                        double total = 0;
                                        String color = "";
                                        if (lista != null) {
                                            for (DetalleVenta p : lista) {
                                                cont++;
                                                if (cont % 2 == 0)
                                                {
                                                    color = "#EAEAEA";
                                                }
                                                else
                                                {
                                                    color = "#ffffff";
                                                }
                                                Producto prod = pd.obtenerProducto(p.getIdProducto());
                                                Venta vent = vt.obtenerVenta(p.getIdVenta());
                                                total = total + p.getSubtotal();
                                                
                                    %>
                                    <tr bgcolor="<%=color%>" onmouseover="this.style.backgroundColor='#FFFF99';this.style.cursor='hand';" onmouseout="this.style.backgroundColor='<%=color%>';">
                                        <td><%= p.getIdVenta()%></td>
                                        <td><div style="text-align: left"><%= vent.getCliente()%></div></td>
                                        <td><div style="text-align: left"><%= p.getProducto()%></div></td>
                                        <td><div style="text-align: right"><%= p.getCantidad()%></div></td>
                                        <td><div style="text-align: right"><%= prod.getPrecio()%></div></td>
                                        <td><div style="text-align: right"><%= p.getDescuento()%></div></td>
                                        <td><div style="text-align: right"><%= p.getSubtotal()%></div></td>
                                    </tr>
                                    <%
                                            }
                                        }
                                        if (cont > 0)
                                        {
                                    %>
                                    <tr >
                                        <td bgcolor="#021E4F" colspan="6"><b><div style="text-align: center; color : #fff;">TOTAL EN VENTAS</div></b></td>
                                        <td bgcolor="#021E4F"><b><div style="text-align: right; color : #fff;"><%=total%></div></b></td>
                                    </tr>
                                    <%
                                        }
                                        else
                                        {
                                    %>
                                    <tr >
                                        <td colspan="7"><center>No se encontró información en este momento</center></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                    <tr >
                                        <td colspan="7" align="center">
                                            <br>
                                            <input type="button" value="Salir" name="btnRegistrar" onclick="Salir();"/><br><br>
                                        </td>
                                    </tr>
                                </table>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

