
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Usuario extends Conexion{
     
    int id_usuario;
    String nombres;
    String apellidos;
    String user;
    String clave;
    String email;
    int tipo;
    
    Connection cnn;
    Statement state;
    ResultSet result;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombre(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }    

    public Usuario(){
        
    }
    
    public Usuario(String user, String clave) {
        this.user = user;
        this.clave = clave;
    }

    public Usuario(int id_usuario, String nombres, String apellidos, String user, String clave, String email, int tipo) {
        this.id_usuario = id_usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.user = user;
        this.clave = clave;
        this.email = email;
        this.tipo = tipo;
    }

    public boolean login(){
        PreparedStatement pst=null;
        ResultSet rs=null;   //Se ocupa para guardar el resultado de la consulta
        
        try{
            //String sql = "select * from usuarios where usuario='"+ this.user +"' and clave=md5('"+ this.clave +"');";
            String sql="select * from tb_usuario where BINARY usuario=? and clave=md5(?)";
            //String sql="select * from usuarios where usuario=? and clave=?";
            pst = obtenerConexion().prepareStatement(sql);
            pst.setString(1, this.user);
            pst.setString(2, this.clave);
            rs= pst.executeQuery();
            
            //Comprobando si existe el registro
            if (rs.absolute(1)){
                return true;
            }
            
        } catch (Exception e){
            System.err.println ("ERROR, " + e);
        }finally{
            try{
                if (obtenerConexion() != null) obtenerConexion().close();
                if (pst != null) pst.close();
                if (rs != null) rs.close();
                
            } catch(Exception e){
                
            }
        }   
        return false;
    }
    
    
      public ArrayList<Usuario> sesion() {
        ArrayList<Usuario> user = new ArrayList(); // crear el array de almacenamiento en cada fial los registros encontrados
        try {
            String miQuery = "select * from usuarios where BINARY usuario='"+ this.user +"' and clave=md5('"+ this.clave +"');";
            state = obtenerConexion().createStatement();
            result = state.executeQuery(miQuery); 
            while(result.next()){ 
                user.add(new Usuario(result.getInt("id"), result.getString("nombres"), result.getString("apellidos"), result.getString("usuario"), result.getString("clave"), result.getString("email"), result.getInt("tipo")));  
            }            
        } catch (SQLException e) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE ,null, e);
        }
        return user;
    }
     
    public static void main(String[] args){
        Usuario p = new Usuario();  
        p.setUser("Administrador");
        p.setClave("12345");
        //p.setTipo(1);
        ArrayList<Usuario> user = new ArrayList();
        user = p.sesion();
        //Para obtener el tamaño de un arraylist. El tamaño esta dado en base
        //a la cantidad de filas o registros existentes en la BD.
        int size = user.size();
        
        if(size>0){
        //Buble for para recorrer un arrylist.
          for (int i = 0; i < user.size(); i++) {
              System.out.println(user.get(0).getUser() + "\t" + user.get(i).getClave() + "\t" + user.get(i).getEmail() + "\t" + user.get(0).getNombres() + "\t" + user.get(0).getTipo());
              System.out.println("El valor máximo de i es: " + i);
          } 
        }else{
            System.out.println("No se encontro resultados que coincidan con la busqueda.");
        }
   }
       
    //INICIO   
    //Creacion del metodo registrar usuarios
    public boolean registroUser(String user, String nombre, String contras, String tipo){
        PreparedStatement pst=null;
        try {
            //String sql1= "insert into usuarios (user, nombre, contrasena,tipo_user) values ('"+usu+"','"+nombre+"','"+pass+"','"+tipo+"')";
            String SQL= "insert into usuarios (user, nombre, contrasena,tipo_user) values (?,?,?,?)";
            pst = obtenerConexion().prepareStatement(SQL);
            pst.setString(1, user);
            pst.setString(2, nombre);
            pst.setString(3, contras);
            pst.setString(4, tipo);
            
            if (pst.executeUpdate()==1){
                return true;
            }
                
        } catch (Exception ex){
            System.err.println("Error, " + ex);
        }finally{
            try  {
                if (obtenerConexion() != null) obtenerConexion().close();
                if (pst != null) pst.close();
                
            }catch (Exception ex1){
                System.err.println("Error, " + ex1);
            }
        }
        return false;
    }   
    
    
     
     public boolean sesion(String usu, String contra){
        PreparedStatement pst=null;
        ResultSet rs=null;   //Se ocupa para guardar el resultado de la consulta
        try
        {
            //String sql = "select * from usuarios where usuario='"+ usuario +"' and clave=md5('"+ clave +"'); ";
            //String sql="select * from usuarios where usuario=? and clave=md5(?)";
            //String sql = "select * from usuarios where usuario='"+ usuario +"' and clave=md5('"+ clave +"'); ";
            String sql="select * from usuarios where usuario=? and clave=?";
            pst = cnn.prepareStatement(sql);
          
            pst.setString(1, usu);
            pst.setString(2, contra);
            rs= pst.executeQuery();
            
            //Comprobando si existe el registro
            if (rs.absolute(1)){
                return true;
            }
            
        } catch (Exception e){
            System.err.println ("ERROR, " + e); 
        }finally{
            try{
                if (cnn != null) cnn.close();
                if (pst != null) pst.close();
                if (rs != null) rs.close(); 
            } catch(Exception e){ 
            }
        }
        return false;
    }
     
      /*public static void main(String[] args){
        usuarios p = new usuarios();
        System.out.println(p.iniciosesion("manuel", "123"));  
       }*/  
    
    //FIN
    
    
}
