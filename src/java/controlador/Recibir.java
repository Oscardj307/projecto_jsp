package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Persona;

public class Recibir extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        //try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //out.println("<!DOCTYPE html>");
            //out.println("<html>");
            //out.println("<head>");
            //out.println("<title>Servlet Recibir</title>");            
            //out.println("</head>");
            //out.println("<body>");
            //out.println("<h1>Servlet Recibir at " + request.getContextPath() + "</h1>");
            //out.println("</body>");
            //out.println("</html>");
        //}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {    
        String dui = request.getParameter("dui");
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        Persona person=new Persona();
        person.setDui(dui);
        person.setNombres(nombres);
        person.setApellidos(apellidos);
        //out.println("<!DOCTYPE html>");
        //out.println("<html>");
        if(person.insertarDatos()){
            
            out.println("<script src=\"lib/jquery-3.3.1.min.js\"></script>");
            out.println("<script src=\"lib/sweetalert.min.js\"></script>");
            out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"lib/sweetalert.css\">");
            out.println("<script>");
            out.println("$(document).ready(function(){");
            out.println("swal('Exito','Registro guardado correctamente!','success');");
            out.println("});");
            out.println("</script>");
            
            System.out.println("Ubicación: " + request.getContextPath());
            
            response.sendRedirect("pages/forms/basic-form-elements.jsp?mensaje=si");
            //request.getRequestDispatcher("pages/forms/basic-form-elements.jsp?mensaje=si").forward(request, response);
            //request.getRequestDispatcher("exito.jsp").forward(request, response);
            
            /*
            out.print("<div class='alert alert-warning alert-dismissible' role='alert'>");
            out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>");
            out.print("Registro Almacenado Satisfactoriamente!");  
            out.print("</div>");
            */
        }else{
            response.sendRedirect("pages/forms/basic-form-elements.jsp?mensaje=no");
            //request.getRequestDispatcher("pages/forms/basic-form-elements.jsp?mensaje=no").forward(request, response);
            //request.getRequestDispatcher("noexito.jsp").forward(request, response);
            
            /*
            out.print("<div class='alert alert-warning alert-dismissible' role='alert'>");
            out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>");
            out.print("Se encontrarón problemas...!");  
            out.print("</div>");
            */
        }
        
    }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
