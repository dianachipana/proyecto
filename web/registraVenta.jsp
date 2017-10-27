<%@page import="java.util.ArrayList"%>
<%@page import="uni.modelo.*" %>
<%@page import="java.util.*" %>
<%
 HttpSession sesion = request.getSession();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <script type="text/javascript" src="Recursos/js/ValidaCarrito.js" ></script>
    </head>
    <body>
        <c:set var ="VarError" value="${requestScope.error}" />
        <c:set var ="VarOK" value="${requestScope.mensaje}" />
        <c:if test="${not empty VarError}">
        <div class='mensajeERROR'>
            <div id='marginImgMsj'><img src='Recursos/img/msjERROR.png'></div>
            <div id='marginMsjs'>${requestScope.error}</div>
        </div>
        <br>
        </c:if>
        <c:if test="${not empty VarOK}">
        <div class='mensajeOK'>
            <div id='marginImgMsj'><img src='Recursos/img/msjOK.png'></div>
            <div id='marginMsjs'>${requestScope.mensaje}</div>
        </div>
        <br>
        </c:if>
        <div class="row-fluid">
            <div class="span11">
                <div class="widget-block">
                    <div class="widget-content">
                        <div class="widget-box">
                            <%-- En el action del formulario le decimos que llama al Controlador --%>
                            <br>
                            <form class="form-horizontal white-box validform formulario" id="formulario" name="formulario" method="post" action="Controlador">
                                <input type="hidden" name="accion" value="RegistrarVenta" />
                                <input type="hidden" name="Codigo" value="" />
                                <fieldset>
                                <legend>&nbsp;&nbsp;&nbsp;&nbsp;CARRITO DE COMPRAS </legend>
                                <table width="100%">
                                    <tr>
                                        <td>CLIENTE</td>
                                        <td colspan="5"><input class="required" type="text" name="txtCliente" value="<%=sesion.getAttribute("usuarioNombre")%> <%=sesion.getAttribute("usuarioApellido")%>" /><br><br></td>
                                    </tr>
                                    <tr>
                                        <td bgcolor="#021E4F"><div style="text-align: left; color : #fff;">NOMBRE</div></td>
                                        <td bgcolor="#021E4F"><div style="text-align: right; color : #fff;">PRECIO</div></td>
                                        <td bgcolor="#021E4F"><div style="text-align: right; color : #fff;">CANTIDAD</div></td>
                                        <td bgcolor="#021E4F"><div style="text-align: right; color : #fff;">DESCUENTO</div></td>
                                        <td bgcolor="#021E4F"><div style="text-align: right; color : #fff;">SUB TOTAL</div></td>
                                        <td bgcolor="#021E4F"><div style="text-align: center; color : #fff;">ELIMINAR</div></td>
                                    </tr>
                                    <%-- Los productos que tenemos en la sesion que se llama carrito --%>
                                    <%
                                        ArrayList<DetalleVenta> lista = (ArrayList<DetalleVenta>) session.getAttribute("carrito");
                                        int cont = 0;
                                        String color = "";
                                        double total = 0;
                                        double t1 = 0;
                                        if (lista != null) {
                                            for (DetalleVenta d : lista) {
                                                cont++;
                                                if (cont % 2 == 0)
                                                {
                                                    color = "#EAEAEA";
                                                }
                                                else
                                                {
                                                    color = "#ffffff";
                                                }
                                                t1 = (d.getProducto().getPrecio() * d.getCantidad()) - d.getDescuento();
                                                total = total + t1;
                                    %>
                                    <tr bgcolor="<%=color%>" onmouseover="this.style.backgroundColor='#FFFF99';this.style.cursor='hand';" onmouseout="this.style.backgroundColor='<%=color%>';">
                                        <td><%= d.getProducto()%></td>
                                        <td><div style="text-align: right"><%= d.getProducto().getPrecio()%></div></td>
                                        <td><div style="text-align: right"><%= d.getCantidad()%></div></td>
                                        <td><div style="text-align: right"><%= d.getDescuento()%></div></td>
                                        <td><div style="text-align: right"><%= t1%></div></td>
                                        <td><center><input name="Submit2" type="button" value="" id="eliminar" title="Quitar Producto" onclick="QuitarCarrito(<%=cont%>);"/></center></td>
                                    </tr>
                                    <%
                                            }
                                        }
                                        if (cont > 0)
                                        {
                                    %>
                                    <tr >
                                        <td bgcolor="#021E4F" colspan="4"><b><div style="text-align: center; color : #fff;">TOTAL</div></b></td>
                                        <td bgcolor="#021E4F"><b><div style="text-align: right; color : #fff;"><%=total%></div></b></td>
                                        <td bgcolor="#021E4F"></td>
                                    </tr>
                                    <%
                                        }
                                        else
                                        {
                                    %>
                                    <tr >
                                        <td colspan="6"><center>No se agregaron productos al carrito</center></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                    <tr >
                                        <td colspan="6" align="center">
                                            <br><input type="submit" <% if (cont == 0){%>disabled<% }%> value="Registrar Venta" name="btnVenta" />
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

