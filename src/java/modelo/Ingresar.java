
package modelo;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;


public class Ingresar {
   String id;
   String  nombre;
   String usuario;
   String clave;

   int intentos;
   int bloqueado;
   
      Connection cnn;
      Statement state;
     ResultSet result;
     
     
    public Ingresar() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Driver de la base de datos
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_profinal?zeroDateTimeBehavior=convertToNull","root",""); //url de la BD,user,pass
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Ingresar(String id, String nombre, String usuario, String clave, int intentos, int bloqueado) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.clave = clave;
        this.intentos = intentos;
        this.bloqueado = bloqueado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(int bloqueado) {
        this.bloqueado = bloqueado;
    }

   
    public ArrayList<Ingresar> consultarRegistros() {
        ArrayList<Ingresar> in = new ArrayList(); // crear el array de almacenamiento en cada fial los registros encontrados
        try {
            String miQuery = "select * from tb_usuario";
            System.out.println(miQuery);
            
            state = cnn.createStatement();//crear el boton de la consulta
            result = state.executeQuery(miQuery); //ejecutar la consulta
            
            
            while(result.next()){ // recorre todo el rsulset y almacena a cada fila de registros encontrados
                
                in.add(new Ingresar(result.getString("id"), result.getString("nombre"), result.getString("usuario"), result.getString("clave"), result.getInt("intentos"), result.getInt("bloqueado")));
                
                
            }
            
            
        } catch (SQLException e) {
            
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE ,null, e);
            
        }
        
        return in;
    }
     public ArrayList<Ingresar> modificarusuario(String id) {
        ArrayList<Ingresar> in = new ArrayList(); // crear el array de almacenamiento en cada fial los registros encontrados
        try {
            String miQuery = "select * from tb_usuario where id = '" + id + "'";
            System.out.println(miQuery);
            
            state = cnn.createStatement();//crear el boton de la consulta
            result = state.executeQuery(miQuery); //ejecutar la consulta
            
            
            while(result.next()){ // recorre todo el rsulset y almacena a cada fila de registros encontrados
                
                in.add(new Ingresar(result.getString("id"), result.getString("nombre"), result.getString("usuario"), result.getString("clave"), result.getInt("intentos"), result.getInt("bloqueado")));
                
                
            }
            
            
        } catch (SQLException e) {
            
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE ,null, e);
            
        }
        
        return in;
    }
    public boolean IncrementarIntentos(){
        try {
            String miQuery  = "update usuarios set intentos = intentos+1 where usuario = '" + usuario + "'";
            int estado = 0; //estado de inserrcion
            state  =  cnn.createStatement();
            estado = state.executeUpdate(miQuery);
            if (estado == 1) {
                return true;
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE,null, ex);
            
        }
        return false;
    }
    
    public boolean IncrementarBloqueo(){
        try {
            String miQuery  = "update usuarios set bloqueado = '"+ bloqueado +"' where usuario = '" + usuario + "'";
            int estado = 0; //estado de inserrcion
            state  =  cnn.createStatement();
            estado = state.executeUpdate(miQuery);
            if (estado == 1) {
                return true;
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE,null, ex);
            
        }
        return false;
    }
    
    public boolean reiniciarIntento(){
        try {
            String miQuery  = "update usuarios set intentos = 0  where usuario = '" + usuario + "'";
            int estado = 0; //estado de inserrcion
            state  =  cnn.createStatement();
            estado = state.executeUpdate(miQuery);
            if (estado == 1) {
                return true;
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE,null, ex);
            
        }
        return false;
    }
    
    public boolean intento(){
        try {
            String miQuery  = "update usuarios set intentos = 3  where usuario = '" + usuario + "'";
            int estado = 0; //estado de inserrcion
            state  =  cnn.createStatement();
            estado = state.executeUpdate(miQuery);
            if (estado == 1) {
                return true;
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE,null, ex);
            
        }
        return false;
    }
    
    public ArrayList<Ingresar> consultarUsuario() {
        ArrayList<Ingresar> usu = new ArrayList(); // crear el array de almacenamiento en cada fial los registros encontrados
        try {
            String miQuery = "select * from usuarios where usuario='"+ usuario +"' and clave=md5('"+ clave +"'); ";
            System.out.println(miQuery);
            
            state = cnn.createStatement();//crear el boton de la consulta
            result = state.executeQuery(miQuery); //ejecutar la consulta
            
            
            while(result.next()){ // recorre todo el rsulset y almacena a cada fila de registros encontrados
                usu.add(new Ingresar(result.getString("id"),result.getString("nombre"), result.getString("usuario"), result.getString("clave"),result.getInt("intentos"), result.getInt("bloqueado")));  
            }            
        } catch (SQLException e) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE ,null, e);
        }
        
        return usu;
    }
    
    public ArrayList<Ingresar> consultarIntento() {
        ArrayList<Ingresar> usu = new ArrayList(); // crear el array de almacenamiento en cada fial los registros encontrados
        try {
            String miQuery = "select * from usuarios where usuario='"+ usuario +"'; ";
            System.out.println(miQuery);
            
            state = cnn.createStatement();//crear el boton de la consulta
            result = state.executeQuery(miQuery); //ejecutar la consulta
            
            
            while(result.next()){ // recorre todo el rsulset y almacena a cada fila de registros encontrados
                
                usu.add(new Ingresar(result.getString("id"),result.getString("nombre"), result.getString("usuario"), result.getString("clave"),result.getInt("intentos"), result.getInt("bloqueado")));
                
                
            }
            
            
        } catch (SQLException e) {
            
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE ,null, e);
            
        }
        
        return usu;
    }
    
}
