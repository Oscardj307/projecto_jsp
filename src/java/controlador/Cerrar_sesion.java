package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Cerrar_sesion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
      try (PrintWriter out = response.getWriter()) {
        
        HttpSession session = request.getSession();
        try{
      
        session.invalidate();
        //session.removeAttribute("usuario");
        //session.removeAttribute("correo");
        //session.removeAttribute("NombreCompleto");
        //session.removeAttribute("tipo");
        
        //out.println("<center><h3><font color='blue'> Cerrando Session </font></h3></center><hr><center><h3><font color='blue'>Por Favor  Espere...</font></h3></center><hr><br>");
        //out.println("<meta http-equiv='refresh' content='3; url=http:index.jsp'/ >");
        
        //request.getRequestDispatcher("index.jsp").forward(request, response);
        //request.getRequestDispatcher("./").forward(request, response);
        response.sendRedirect("./");
        }catch(Exception e){
            e.printStackTrace();
        }
      
      }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
