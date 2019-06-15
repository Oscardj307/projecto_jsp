package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Persona;

public class Modificar extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            String dui=request.getParameter("dui");
            String nombres=request.getParameter("nombres");
            String apellidos=request.getParameter("apellidos");
            
            Persona person=new Persona();
            person.setDui(dui);
            person.setNombres(nombres);
            person.setApellidos(apellidos);
            
            if (person.modificarDatos()) {
                    //response.sendRedirect("usuario/?modificado=exito");
                    response.sendRedirect("pages/tables/mod_Personas.jsp?chi=algo&&modificado=exito&&dui="+dui+"&&nombres="+nombres+"&&apellidos="+apellidos+"");
                } else {
                    //response.sendRedirect("usuario/?modificado=error");
                    response.sendRedirect("pages/tables/mod_Personas.jsp?chi=algo&&modificado=error&&dui="+dui+"&&nombres="+nombres+"&&apellidos="+apellidos+"");
                }
        }
        
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
