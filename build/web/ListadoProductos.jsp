<%-- Importaciones que son necesarias para que se muestre el JSP --%>
<%@page import="uni.modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*" %>
<%@page import="uni.dao.ProductoDao"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%
    //HttpSession sesion = request.getSession();
%>
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
        <script type="text/javascript" src="Recursos/js/ValidaProducto.js"></script>
    </head>
    <body>
        <div class="row-fluid">
            <div class="span11">
                <div class="widget-block">
                    <div class="widget-content">
                        <div class="widget-box">
                            <%-- En el action del formulario le decimos que llama al Controlador --%>
                            <br>
                            <form name="formulario" method="post">
                                <input type="hidden" id="id" name="id" value="">
                                <fieldset>
                                    <legend>&nbsp;&nbsp;&nbsp;&nbsp;LISTADO DE PRODUCTOS </legend>
                                    <table>
                                        <tr>
                                            <th bgcolor="#021E4F" width="5%"><div style="text-align: left; color : #fff;">CÓDIGO</div></th>
                                        <th bgcolor="#021E4F" width="60%"><div style="text-align: left; color : #fff;">NOMBRE</div></th>
                                        <th bgcolor="#021E4F" width="15%"><div style="text-align: left; color : #fff;">PRECIO</div></th>
                                        <th bgcolor="#021E4F" width="10%"><div style="text-align: center; color : #fff;">MODIFICAR</div></th>
                                        <th bgcolor="#021E4F" width="10%"><div style="text-align: center; color : #fff;">CARRITO</div></th>
                                        </tr>
                                        <%-- Lista de todos los productos --%>
                                        <%
                                            ArrayList<Producto> lista = ProductoDao.obtenerProducto();
                                            int cont = 0;
                                            String color = "";
                                            for (Producto p : lista) {
                                                cont++;
                                                if (cont % 2 == 0) {
                                                    color = "#EAEAEA";
                                                } else {
                                                    color = "#ffffff";
                                                }
                                        %>
                                        <tr bgcolor="<%=color%>" onmouseover="this.style.backgroundColor = '#FFFF99';
                                this.style.cursor = 'hand';" onmouseout="this.style.backgroundColor = '<%=color%>';">
                                            <td><%= p.getIdProducto()%></td>
                                            <td><%= p.getNombre()%></td>
                                            <td><%= p.getPrecio()%></td>
                                            <%-- Enlaces a las paginas de actualizar o anadir al carrito --%>
                                            <td align="center"><input name="Submit2" type="button" value="" id="modificar" title="Modificar Producto" onclick="ModificaProducto(<%= p.getIdProducto()%>);"/></td>
                                            <td align="center"><input name="Submit2" type="button" value="" id="shop" title="Añadir a Carrito" onclick="AddCarrito(<%= p.getIdProducto()%>);"/></td>
                                        </tr>
                                        <%
                                            }
                                        %>
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
