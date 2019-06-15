
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


class Planilla extends Conexion {
    
     public ResultSet consultaSelect(){
        PreparedStatement pst=null;
        ResultSet rs=null;
        try {
            String sql= "select * from tb_personal";
            pst = obtenerConexion().prepareStatement (sql);
            rs = pst.executeQuery();
            
        } catch (Exception e) {
            System.err.println ("ERROR, " + e);
            
        }
        return rs;
    }
 
    public ResultSet consultaset (){
        PreparedStatement pst=null;
        ResultSet rs=null;
        try {
            String sql="select * from tb_personal";
            pst = obtenerConexion(). prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (Exception e) {
            System.err.println("ERROR, " + e);
        }
        return rs;
    }
    
}
