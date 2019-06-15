package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

public class Login extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
        out.print("pase aqui...");
            
        String tipoSesion="";
        String usuario = request.getParameter("user");   
        String clave = request.getParameter("clave");
        
        Usuario user=new Usuario();
        ArrayList<Usuario> users_Array = new ArrayList();
        HttpSession SESSION = request.getSession(true);
        
        user.setUser(usuario);
        user.setClave(clave);
        
        users_Array = user.sesion();
        
        if (users_Array.size() > 0) {
                String nombreCompleto = users_Array.get(0).getNombres()+ "  " + users_Array.get(0).getApellidos();
                int t = users_Array.get(0).getTipo();
                String e_mail=users_Array.get(0).getEmail();
                SESSION.setAttribute("usuario", user);
                SESSION.setAttribute("correo", e_mail);
                SESSION.setAttribute("NombreCompleto", nombreCompleto);
                
                if (t==1) {
                    tipoSesion="Administrador";
                }else{
                    tipoSesion="Invitado";
                }
                
                SESSION.setAttribute("tipo", tipoSesion);
                response.sendRedirect("principal.jsp");
                
               }else{
                      //response.sendRedirect("index.jsp");
                      response.sendRedirect("?error");
                    }
        }
        */
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String tipoSesion="";
        String usuario = request.getParameter("user");   
        String clave = request.getParameter("clave");
        
        Usuario user=new Usuario();
        ArrayList<Usuario> users_Array = new ArrayList();
        
        //$_SESSION["X"]="DATO";
        HttpSession sesion = request.getSession(true);
        
        user.setUser(usuario);
        user.setClave(clave);
        
        users_Array = user.sesion();
        
        if (users_Array.size() > 0) {
                String nombreCompleto = users_Array.get(0).getNombres()+ "  " + users_Array.get(0).getApellidos();
                int t = users_Array.get(0).getTipo();
                String e_mail=users_Array.get(0).getEmail();
                
                sesion.setAttribute("usuario", usuario);
                sesion.setAttribute("correo", e_mail);
                sesion.setAttribute("NombreCompleto", nombreCompleto);
                
                if (t==1) {
                    tipoSesion="Administrador";
                }else{
                    tipoSesion="Invitado";
                }
                
                sesion.setAttribute("tipo", tipoSesion);
                response.sendRedirect("principal.jsp");
                
               }else{
                      //response.sendRedirect("index.jsp");
                        response.sendRedirect("./?error");
                    }
        
        
        /*        
        String usuario = request.getParameter("user");   
        String clave = request.getParameter("clave");
        Usuario user=new Usuario();
        user.setUser(usuario);
        user.setClave(clave);
        if(user.login()){
            //Creacion de la variable de sesion
            HttpSession variablesesion = request.getSession(true);
            variablesesion.setAttribute("usuario", usuario);
            response.sendRedirect("principal.jsp");
        }else{
             response.sendRedirect("index.jsp");
        }
        */
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
