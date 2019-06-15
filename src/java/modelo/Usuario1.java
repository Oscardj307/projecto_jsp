package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario1 extends Conexion{
    int id_usuario;
    String nombre;
    String apellido;
    String user;
    String clave;
    String email;
    int tipo;
   
    Statement state;
    ResultSet result;

    public Usuario1() {
        
    }

    public Usuario1(int id_usuario, String nombre, String apellido, String user, String clave, String email, int tipo) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.user = user;
        this.clave = clave;
        this.email = email;
        this.tipo = tipo;
    }
    
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
    
     public boolean insertardatos() {
        try {
            String miQuery =  "insert into tb_usuario(nombre,apellido,usuario,clave,email,tipo)  values('" + nombre + "','" + apellido + "','" + user + "',MD5('"+ clave +"'),'" + email + "'," + tipo + ");";
            int estado = 0;
            state = obtenerConexion().createStatement();
            estado = state.executeUpdate(miQuery);
            if (estado == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
     
    public ArrayList<Usuario1> sesion() {
        ArrayList<Usuario1> userr = new ArrayList(); // crear el array de almacenamiento en cada fial los registros encontrados
        try {
            String miQuery = "select * from usuarios where usuario='"+ user +"' and clave=md5('"+ clave +"') and tipo='"+ tipo +"'; ";
            //String miQuery = "select * from usuarios where usuario='"+ usuario +"' and clave=md5('"+ clave +"'); ";
           //String miQuery = "select * from usuarios where usuario='"+ usuario +"' and clave='"+ clave +"'; ";
            state = obtenerConexion().createStatement();
            result = state.executeQuery(miQuery); 
            while(result.next()){ 
                userr.add(new Usuario1(result.getInt("id"), result.getString("nombre"), result.getString("apellido"), result.getString("usuario"), result.getString("clave"), result.getString("email"), result.getInt("tipo")));  
            }            
        } catch (SQLException e) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE ,null, e);
        }
        return userr;
    }
     
  public ArrayList<Usuario1> consultarRegistros() {
        ArrayList<Usuario1> userr = new ArrayList();
        try {
            String miQuery = "select * from usuarios";
            state = obtenerConexion().createStatement();
            result = state.executeQuery(miQuery);
            while (result.next()) {
                userr.add(new Usuario1(result.getInt("id"), result.getString("nombre"), result.getString("apellido"), result.getString("usuario"), result.getString("clave"), result.getString("email"), result.getInt("tipo")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userr;
    }
  
     public boolean eliminarDatos() {
        try {
            String miQuery = "delete from usuarios where id='" + id_usuario + "'";
            int estado = 0;
            state = obtenerConexion().createStatement();
            estado = state.executeUpdate(miQuery);
            if (estado == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
     
       public boolean modificarDatos() {
        try{
            String pass="";
            if (clave.isEmpty()) {
                pass="";   
            }else{
                pass="clave=MD5('"+clave +"'),";
            }
            
            String miQuery = "update usuarios set nombre='" + nombre + "',apellido='" + apellido + "',usuario='" + user + "',"+pass+"email='" + email + "',tipo=" + tipo + " where id=" + id_usuario + ";";
            int estado = 0;
            state = obtenerConexion().createStatement();
            estado = state.executeUpdate(miQuery);
            if (estado == 1) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
