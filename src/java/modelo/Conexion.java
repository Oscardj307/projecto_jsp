package modelo;

import java.sql.*;

public class Conexion {
    private Connection cnx = null;
    private ResultSet rset;  
    private Statement stment;
    private PreparedStatement pStment;
    private int cantFilas, idGenerado;
    
    public Connection obtenerConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //return  DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "SistemaBibliotecario", "root", "");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_profinal?zeroDateTimeBehavior=convertToNull","root","");
        }catch (Exception ex){
            System.out.println("No se pudo establecer la conexión... " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
    
    public void abrirConexion() {
        //System.out.println("[Abriendo la conexión]");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //this.cnx =  DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "SistemaBibliotecario", "root", "");
            DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_profinal?zeroDateTimeBehavior=convertToNull","root","");
            System.out.println("Conexión Lista!!!");
        }catch (Exception ex){
            System.out.println("No se pudo abrir la conexión...");
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Conexion pruebaCnn = new Conexion();
        pruebaCnn.abrirConexion();
    }
    
    
    public ResultSet consultar(String sql){
        rset = null;
        try{
            stment = cnx.createStatement();
            rset = stment.executeQuery(sql);
        }catch(SQLException ex){
            System.out.println("Error al consultar: " + sql + " Conexion.java");
            ex.printStackTrace();
        }
        return rset;
    }
    
    public void ejecutar(String sql){
        try{
            stment = cnx.createStatement();
            stment.executeUpdate(sql);
        }catch (SQLException ex){
            System.out.println("Error al ejecutar: " + sql + " Conexion.java");
            ex.printStackTrace();
        }
    }
    
    public int ejecutarX(String sql){
        try{
            pStment = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            cantFilas = pStment.executeUpdate();
            if (cantFilas == 1){
                rset = pStment.getGeneratedKeys();
                if (rset.next()){
                    return rset.getInt(1);
                }
            }
        }catch (SQLException ex){
            System.out.println("Error al ejecutar: " + sql + " Conexion.java");
            ex.printStackTrace();
        }
        return 0;
    }
    
    public void cerrarConexion(){
        //System.out.println("[Cerrando la conexión]");
        try{
            this.cnx.close();
        }catch (SQLException ex){
            System.out.println("No se pudo cerrar la conexión...");
            ex.printStackTrace();
        }
    }
}
