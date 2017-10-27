<%@page import="javax.servlet.http.HttpSession"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("usuarioNombre")!= null)
    { // si la sesion existe
    }
    else
    {
        response.sendRedirect("LogUsuario.html");
    }   
%>