<%@page import="uni.dao.ProductoDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uni.modelo.Producto" %>
<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Obtenemos el id o el codigo del producto que deseamos modificar o actualizar --%>
<%
    Producto p=ProductoDao.obtenerProducto(Integer.parseInt(request.getParameter("id")));
 %>
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
        <%-- En el action del formulario le decimos que llama al Controlador --%>
        <div class="row-fluid">
            <div class="span11">
                <div class="widget-block">
                    <div class="widget-content">
                        <div class="widget-box">
                            <%-- En el action del formulario le decimos que llama al Controlador --%>
                            <br>
                            <form class="form-horizontal white-box validform formulario" id="formulario" name="formulario" method="post" action="Controlador">
                                <input type="hidden" name="accion" value="ModificarProducto" />
                                <input type="hidden" name="id" value="<%= p.getIdProducto()%>" />
                                <fieldset>
                                <legend>&nbsp;&nbsp;&nbsp;&nbsp;ACTUALIZACIÓN DE PRODUCTOS </legend>
                                <div class="control-group"><label class="control-label">PRODUCTO <span class="error">*</span></label>
                                    <div class="controls">
                                        <!--GUARDO EL CODIGO DEL EMPLEADO-->
                                        <input class="required" onKeyUp="this.value = this.value.toUpperCase();" type="text" name="txtNombre" id="txtNombre" value="<%= p.getNombre()%>"/>
                                    </div>
                                </div>
                                <div class="control-group"><label class="control-label">PRECIO <span class="error">*</span></label>
                                    <div class="controls">
                                        <!--GUARDO EL CODIGO DEL EMPLEADO-->
                                        <input class="required" onkeypress="return acceptNumDecimal(event);" style="width: 20%" type="text" name="txtPrecio" id="txtPrecio" value="<%= p.getPrecio()%>"/>
                                    </div>
                                </div>
                                <br>
                                <div class="form-actions">
                                    <input type="submit" value="Actualizar" name="btnRegistrar" />
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

