package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import uni.dao.ProductoDao;
import uni.modelo.Producto;
import java.util.ArrayList;
import java.util.*;

public final class agregarCarrito_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write('\n');

   Producto p=ProductoDao.obtenerProducto(Integer.parseInt(request.getParameter("id")));

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>:::Bienvenido al Sistema de Ventas On-line ABC::: Ingrese su Usuario y Clave</title>\n");
      out.write("        <link href=\"Recursos/css/bootstrap.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"Recursos/css/forms.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"Recursos/css/styles.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"Recursos/css/bootstrap-responsive.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"Recursos/css/icons-sprite.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"Recursos/css/themes.css\" id=\"themes\"  rel=\"stylesheet\">\n");
      out.write("        <link href=\"Recursos/css/estilospropios.css\" rel=\"stylesheet\">\n");
      out.write("        <script type=\"text/javascript\" src=\"Recursos/js/function.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"Recursos/js/jquery.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"Recursos/js/validar/jquery.validate.js\" ></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"Recursos/js/validar/reglas.js\" ></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"row-fluid\">\n");
      out.write("            <div class=\"span11\">\n");
      out.write("                <div class=\"widget-block\">\n");
      out.write("                    <div class=\"widget-content\">\n");
      out.write("                        <div class=\"widget-box\">\n");
      out.write("                            ");
      out.write("\n");
      out.write("                            <br>\n");
      out.write("                            <form class=\"form-horizontal white-box validform formulario\" id=\"formulario\" name=\"formulario\" method=\"post\" action=\"Controlador\">\n");
      out.write("                                <input type=\"hidden\" name=\"accion\" value=\"AnadirCarrito\" />\n");
      out.write("                                <fieldset>\n");
      out.write("                                <legend>&nbsp;&nbsp;&nbsp;&nbsp;AGREGAR A CARRITO </legend>\n");
      out.write("                                <div class=\"control-group\"><label class=\"control-label\">CODIGO <span class=\"error\">*</span></label>\n");
      out.write("                                    <div class=\"controls\">\n");
      out.write("                                        <!--GUARDO EL CODIGO DEL EMPLEADO-->\n");
      out.write("                                        <input class=\"required\" onKeyUp=\"this.value = this.value.toUpperCase();\" type=\"text\" name=\"txtCodigo\" id=\"txtCodigo\" value=\"");
      out.print( p.getIdProducto());
      out.write("\" readonly/>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"control-group\"><label class=\"control-label\">PRODUCTO <span class=\"error\">*</span></label>\n");
      out.write("                                    <div class=\"controls\">\n");
      out.write("                                        <!--GUARDO EL CODIGO DEL EMPLEADO-->\n");
      out.write("                                        <input class=\"required\" onKeyUp=\"this.value = this.value.toUpperCase();\" type=\"text\" name=\"txtNombre\" id=\"txtNombre\" value=\"");
      out.print( p.getNombre());
      out.write("\" readonly />\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"control-group\"><label class=\"control-label\">PRECIO <span class=\"error\">*</span></label>\n");
      out.write("                                    <div class=\"controls\">\n");
      out.write("                                        <!--GUARDO EL CODIGO DEL EMPLEADO-->\n");
      out.write("                                        <input class=\"required\" onKeyUp=\"this.value = this.value.toUpperCase();\" style=\"width: 20%\" type=\"text\" name=\"txtPrecio\" id=\"txtPrecio\" value=\"");
      out.print( p.getPrecio());
      out.write("\" readonly/>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"control-group\"><label class=\"control-label\">CANTIDAD A PEDIR <span class=\"error\">*</span></label>\n");
      out.write("                                    <div class=\"controls\">\n");
      out.write("                                        <!--GUARDO EL CODIGO DEL EMPLEADO-->\n");
      out.write("                                        <input class=\"required\" onkeypress=\"return acceptNum(event);\" style=\"width: 20%\" type=\"text\" name=\"txtCantidad\" id=\"txtCantidad\"/>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                <br>\n");
      out.write("                                <div class=\"form-actions\">\n");
      out.write("                                    <input type=\"submit\" value=\"Añadir\" name=\"btnRegistrar\" />\n");
      out.write("                                    <input type=\"button\" value=\"Salir\" name=\"btnRegistrar\" onclick=\"Salir();\"/>\n");
      out.write("                                </div>\n");
      out.write("                                </fieldset>\n");
      out.write("                            </form>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
