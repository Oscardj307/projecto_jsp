package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Persona;
//import com.google.gson.Gson;
import com.google.gson.*;
import java.util.ArrayList;
import modelo.ResultadoJson;


public class Borrar extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        String dui = request.getParameter("dui");
        Persona person = new Persona();
        person.setDui(dui);
        if (person.deleteRegister(dui)){
            request.getRequestDispatcher("mostrar.do?dui=''").forward(request, response);
        }else{
            request.getRequestDispatcher("noeliminar.jsp").forward(request, response);
        }
        */
        
             
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String dui = request.getParameter("dui");
        Persona person = new Persona();
        person.setDui(dui);
        
        ArrayList<ResultadoJson> lista=new ArrayList<ResultadoJson>();
        Gson gson=new Gson();
        
        if (person.deleteRegister(dui)){
            //lista.add(new ResultadoJson("success","Registro eliminado correctamente."));
            lista.add(new ResultadoJson("Eliminado!","success","Registro eliminado correctamente."));
        }else{
            lista.add(new ResultadoJson("Lo sentimos!","error","Error. No se pudo eliminar el registro."));
        }
        
        response.setContentType("application/json");
        //response.getWriter().write(json.toString());
        PrintWriter pw = response.getWriter();
        pw.println(gson.toJson(lista));
        pw.close();   
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
