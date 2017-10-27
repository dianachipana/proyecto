<%@page import="uni.dao.ProductoDao"%>
<%@page import="uni.modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Obtenemos el id o el codigo del producto que deseamos añadir al carrito --%>
<%
   Producto p=ProductoDao.obtenerProducto(Integer.parseInt(request.getParameter("id")));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>:::Bienvenido al Sistema de Ventas On-line ABC::: Ingrese su Usuario y Clave</title>
        <link href="Recursos/css/bootstrap.css" rel="stylesheet">
        <link href="Recursos/css/forms.css" rel="stylesheet">
        <link href="Recursos/css/styles.css" rel="stylesheet">
        <link href="Recursos/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="Recursos/css/icons-sprite.css" rel="stylesheet">
        <link href="Recursos/css/themes.css" id="themes"  rel="stylesheet">
        <link href="Recursos/css/estilospropios.css" rel="stylesheet">
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
                                <input type="hidden" name="accion" value="AnadirCarrito" />
                                <fieldset>
                                <legend>&nbsp;&nbsp;&nbsp;&nbsp;AGREGAR A CARRITO </legend>
                                <div class="control-group"><label class="control-label">CODIGO <span class="error">*</span></label>
                                    <div class="controls">
                                        <!--GUARDO EL CODIGO DEL EMPLEADO-->
                                        <input class="required" onKeyUp="this.value = this.value.toUpperCase();" type="text" name="txtCodigo" id="txtCodigo" value="<%= p.getIdProducto()%>" readonly/>
                                    </div>
                                </div>
                                <div class="control-group"><label class="control-label">PRODUCTO <span class="error">*</span></label>
                                    <div class="controls">
                                        <!--GUARDO EL CODIGO DEL EMPLEADO-->
                                        <input class="required" onKeyUp="this.value = this.value.toUpperCase();" type="text" name="txtNombre" id="txtNombre" value="<%= p.getNombre()%>" readonly />
                                    </div>
                                </div>
                                <div class="control-group"><label class="control-label">PRECIO <span class="error">*</span></label>
                                    <div class="controls">
                                        <!--GUARDO EL CODIGO DEL EMPLEADO-->
                                        <input class="required" onKeyUp="this.value = this.value.toUpperCase();" style="width: 20%" type="text" name="txtPrecio" id="txtPrecio" value="<%= p.getPrecio()%>" readonly/>
                                    </div>
                                </div>
                                <div class="control-group"><label class="control-label">CANTIDAD A PEDIR <span class="error">*</span></label>
                                    <div class="controls">
                                        <!--GUARDO EL CODIGO DEL EMPLEADO-->
                                        <input class="required" onkeypress="return acceptNum(event);" style="width: 20%" type="text" name="txtCantidad" id="txtCantidad"/>
                                    </div>
                                </div>
                                <br>
                                <div class="form-actions">
                                    <input type="submit" value="Añadir" name="btnRegistrar" />
                                    <input type="button" value="Salir" name="btnRegistrar" onclick="Salir();"/>
                                </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

