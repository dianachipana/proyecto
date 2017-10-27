package uni.controlador;

import java.io.IOException;
import java.util.Vector;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uni.modelo.Usuario;

@SuppressWarnings("UseOfObsoleteCollectionType")
public class ServletInicioSesion extends HttpServlet {


    private Vector<Usuario> usuarios;

    //Metodo que va a crear dos usuarios y lo va adicionar al vector de usuarios
    public void inicializarUsuarios() {
        //Creando el vector de usuarios
        this.usuarios = new Vector<>();
        //Creando los usuarios
        Usuario user1 = new Usuario(1, "Carlos Daniel", "Zapata", "czapata", "12345");
        Usuario user2 = new Usuario(2, "Armando", "Paredes", "aparedes", "12345");
        Usuario user3 = new Usuario(3, "Yolanda", "Benavides", "ybenavides", "12345");
        //Adicionando los usuarios al vector de usuarios
        this.usuarios.add(user1);
        this.usuarios.add(user2);
        this.usuarios.add(user3);
        
    }


    @Override
    public void init(ServletConfig confing) throws ServletException {
        this.inicializarUsuarios();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //variables
        String usuario = request.getParameter("nombre");
        String clave = request.getParameter("clave");
        String direccion="errorLogUsuario.html";
        //
        HttpSession sesion = request.getSession();
        if (sesion.isNew()) {
            sesion = request.getSession(true);
            //sesion.setAttribute("usuarios", usuarios);
        } else {
            //usuarios = (Vector<Usuario>) sesion.getAttribute("usuarios");
        }


        for (int i = 0; i < usuarios.size(); i++) {
            if ((usuarios.get(i).getUsuario().compareTo(usuario) == 0) &&
                    (usuarios.get(i).getClave().compareTo(clave) == 0)) {
                sesion.setAttribute("usuarioCodigo", usuarios.get(i).getCodigo());
                sesion.setAttribute("usuarioNombre", usuarios.get(i).getNombre());
                sesion.setAttribute("usuarioApellido", usuarios.get(i).getApellido());
                direccion="Principal.jsp";
                break;
            }
        }
        //
        response.sendRedirect(direccion);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
