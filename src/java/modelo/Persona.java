
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Persona {
    String dui;
    String nombre;
    String apellido;
    String direccion;
    int telefono;
    String email;
    char tipo_empleado;
    String comentarios;
    
    Connection cnn;
    Statement state;
    ResultSet result;
    
    public Persona() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_profinal?zeroDateTimeBehavior=convertToNull","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Persona(String dui, String nombre, String apellido, String direccion, int telefono, String email, char tipo_empleado, String comentarios) {
        this.dui = dui;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono =  telefono;
        this.email = email;
        this.tipo_empleado = tipo_empleado;
        this.comentarios = comentarios;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getTipo_empleado() {
        return tipo_empleado;
    }

    public void setTipo_empleado(char tipo_empleado) {
        this.tipo_empleado = tipo_empleado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Connection getCnn() {
        return cnn;
    }

    public void setCnn(Connection cnn) {
        this.cnn = cnn;
    }

    public Statement getState() {
        return state;
    }

    public void setState(Statement state) {
        this.state = state;
    }

    public ResultSet getResult() {
        return result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }

  
    
    public boolean insertarDatos(){
        try{
            String myQuery="insert into tb_personal values "
                    + "('" +dui+ "','"+ nombre +"','"+ apellido+ "','" + direccion + "','" + telefono +"','"+ email +"','"+ tipo_empleado +"','" + comentarios +"','"+ "');";
            int estado=0;
            state=cnn.createStatement();
            estado = state.executeUpdate(myQuery);
            if(estado==1){
                return true;
            }
        }catch(SQLException ex){
            //Logger.getLogger(Persona.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("Error. Ya existe un registro con el"
                    + "dui: "+this.dui);
        }
        return false;
    }
    
    /*
    public static void main(String[] args) {
        Persona p = new Persona();
        p.setDui("9999999-2");
        p.setNombres("Manuel de Jesús");
        p.setApellidos("Gámez López");
        System.out.println(p.insertarDatos());
    }
    */
    
    
    //Creacion del metodo registrar usuarios
    public boolean registroPerson(Persona person){
        PreparedStatement pst=null;
        try {
            //String sql1= "insert into usuarios (user, nombre, contrasena,tipo_user) values ('"+usu+"','"+nombre+"','"+pass+"','"+tipo+"')";
            String SQL= "insert into tb_personal (dui, nombre, apellido, direccion, email, tipo_empleado, comentarios) values (?,?,?,?,?,?,?)";
            pst = cnn.prepareStatement(SQL);
            pst.setString(1, person.getDui());
            pst.setString(2, person.getNombre());
            pst.setString(3, person.getApellido());
            pst.setString(4, person.getDireccion());
            pst.setInt(5, person.getTelefono());
            pst.set(6, person.getTipo_empleado());
             pst.setString(8, person.getComentarios());
            
            
            if (pst.executeUpdate()==1){
                return true;
            }
                
        } catch (Exception ex){
            System.err.println("Error, " + ex);
        }finally{
            try  {
                if (cnn != null) cnn.close();
                if (pst != null) pst.close();
                
            }catch (Exception ex1){
                System.err.println("Error, " + ex1);
            }
        }
        return false;
    }
    
    /*
    public static void main(String[] args){
            Persona p = new Persona();
            Scanner leer=new Scanner(System.in);
            System.out.println("Ingrese el numero de DUI: ");
            String dui=leer.nextLine();
            p.setDui(dui);
            System.out.println("Ingrese sus apellidos: ");
            String apellidos=leer.nextLine();
            p.setApellidos(apellidos);
            System.out.println("Ingrese sus nombres: ");
            String nombre=leer.nextLine();
            p.setNombres(nombre);
            System.out.println(p.registroPerson(p));  
        }
    */
    
    //Creacion del metodo registrar usuarios
    public boolean registrouser(){
        PreparedStatement pst=null;
        try {
            //String sql1= "insert into usuarios (user, nombre, contrasena,tipo_user) values ('"+usu+"','"+nombre+"','"+pass+"','"+tipo+"')";
            String SQL= "insert into tb_personal (dui, nombre, apellido, direccion, email, tipo_empleado, comentarios) values (?,?,?,?,?,?,?)";
            pst = cnn.prepareStatement(SQL);
            pst.setString(1, this.dui);
            pst.setString(2, this.nombre);
            pst.setString(3, this.apellido);
            pst.setString(4, this.direccion);
            pst.setString(5, this.email);
            pst.setInt(6, this.telefono);
            pst.char(7, this.tipo_empleado);
            pst.setString(8, this.comentarios);
            
            
            if (pst.executeUpdate()==1){
                return true;
            }
                
        } catch (Exception ex){
            System.err.println("Error, " + ex);
        }finally{
            try  {
                if (cnn != null) cnn.close();
                if (pst != null) pst.close();
                
            }catch (Exception ex1){
                System.err.println("Error, " + ex1);
            }
        }
        return false;
    }
    
    
    public ArrayList<Persona> consultarRegistros(){
        ArrayList<Persona> person = new ArrayList();
        
        try{
            String myQuery="select * from tb_personal order by apellido desc;";
            int estado=0;
            state=cnn.createStatement();
            result = state.executeQuery(myQuery);
            
            while(result.next()){
                person.add(new Persona(result.getString("dui"), result.getString("nombre"), result.getString("apellido")));
            }
        }catch(SQLException e){
            //Logger.getLogger(Persona.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("Error. Se han encontrado problemas. \n"
                    + "Verifique por favor.");
        }
        return person;
    }
    
    /*
    public static void main(String[] args) {
        Persona p=new Persona();
        //System.out.println(p.consultarRegistros());  
        ArrayList<Persona> user = new ArrayList();
        user = p.consultarRegistros();
        
        //Para obtener el tamaño de un arraylist. El tamaño esta dado en base
        //a la cantidad de filas o registros existentes en la BD.
        int size = user.size();
        //System.out.println(size);
        
        //Buble for para recorrer un arrylist.
          for (int i = 0; i < user.size(); i++) {
              System.out.println(user.get(i).getDui()+ "\t" + user.get(i).getNombres()+ "\t" + user.get(i).getApellidos());
              //System.out.println("El valor máximo de i es: " + i);
          }
    }
    */
    
    
    //static private PreparedStatement pst=null;
    public ResultSet consultarRegistros1(){
        PreparedStatement pst=null;
        ResultSet rs=null;   //Se ocupa para guardar el resultado de la consulta
        try
        {
            String sql="select * from tb_personal order by nombre;";
            pst = cnn.prepareStatement(sql);
            rs= pst.executeQuery();
                       
        } catch (Exception e){
            System.err.println ("ERROR, " + e);  
        }
        return rs;
    }
    
    /*
    public static void main(String[] args) throws SQLException {
        Persona p=new Persona();
        //System.out.println(p.consu1combo());
        ResultSet rs = p.consu1combo();
        System.out.println("DUI_PERSONA \t APELLIDOS_PERSONA \t NOMBRES_PERSONA");
            while (rs.next()) {
                System.out.println(rs.getString("dui_persona") + "\t\t" +
                                   rs.getString("apellidos_persona") + "\t\t" +
                                   rs.getString("nombre_persona"));
            }
    }
    */
    
    
    public boolean consucombo(){
        PreparedStatement pst=null;
        ResultSet rs=null;   //Se ocupa para guardar el resultado de la consulta
        
        try
        {
            String sql="select * from tb_usuario";
            pst = cnn.prepareStatement(sql);
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
    
    
    public boolean login(Persona p){
        PreparedStatement pst=null;
        ResultSet rs=null;   //Se ocupa para guardar el resultado de la consulta
        try
        {
            String sql="select * from tb_usuario where usuario=? and clave=?";
            pst = cnn.prepareStatement(sql);
            pst.setString(1, this.apellidos);  //De momento lo dejo así.
            pst.setString(2, this.nombres);
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
            consulta_usuario consu = new consulta_usuario();
            System.out.println(consu.iniciosesion("chovi", "123456"));  
        }*/
    
    
    
    //Métodos de la tarea evaluada.
    //MODIFICAR Y ELIMINAR REGISTROS.
    
    public boolean eliminarDatos(){
        try {
            String miQuery  = "delete from tb_personal where dui ='" + dui + "'";
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
    
   public boolean deleteRegister(String dui){
       try {
           int estado=0;
           String miQuery="delete from tb_personal where dui='"+dui+"';";
           state=cnn.createStatement();
           estado=state.executeUpdate(miQuery);
           if(estado==1){
             return true;   
           } 
       }catch (SQLException ex) {
           Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
    }
    
    
     public boolean modificarDatos(){
        try {
            String miQuery  = "update tb_personal set nombre = '"+ nombres + "',apellido = '" + apellidos + "' where dui = '" + dui + "'";
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
     
     
     public boolean actualizar(String duip,String apellidop,String nombrep){
       try {
           int estado=0;
           String miQuery="update tb_personal set dui='"+duip+"',apellido='"+apellidop+"',nombre='"+nombrep+"' where dui='"+duip+"';";
           state=cnn.createStatement();
           estado=state.executeUpdate(miQuery);
           if(estado==1){
               return true;
           }
       } catch (SQLException ex) {
           Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;
    }
    
    
}
