<%-- Importaciones que son necesarias para que se muestre el JSP --%>
<%@include file ="DeterminarSesion.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>:::Bienvenido al Sistema de Ventas On-line ABC::: Ingrese su Usuario y Clave</title>
        <link href="Recursos/css/bootstrap.css" rel="stylesheet">
        <link href="Recursos/css/forms.css" rel="stylesheet">
        <link href="Recursos/css/styles.css" rel="stylesheet">
        <link href="Recursos/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="Recursos/css/themes.css" id="themes"  rel="stylesheet">
        <link href="Recursos/css/estilospropios.css" rel="stylesheet">
        <script type="text/javascript" src="Recursos/js/function.js"></script>
    </head>
    <body>
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="titulo">
                    <center><b>Bienvenido <%=sesion.getAttribute("usuarioNombre")%> <%=sesion.getAttribute("usuarioApellido")%></b></center>
                </div>
            </div>
        </div>
        <div class="titulo">
            <center><img src="Recursos/img/logo2.png"></center>
        </div>
        <%@include file ="menu.jsp"%>
        <br >
        <center>
            <iframe src="ListadoProductos.jsp" width="880px" height="380px" name="WORK" style="border:0px solid #158bb7;"></iframe>
        </center>
        <br><br>
        <div class="navbar navbar-fixed-bottom">
            <div class="navbar-inner">
                <div class="titulo">
                    VentasOnline@2017 Super Veliz - todos los derechos reservados.<br>
                </div>
            </div>
        </div>   
    </body>
</html>
