
package db;

import java.sql.Connection;
import java.sql.SQLException;


public class Test {
    //psvm+tab
    public static void main(String[] args) {
        try{
            Connection cn=new MiConexion().getConexion();
            if(cn!=null){
                System.out.println("Conexion conforme a la bd");
            }else{
                System.out.println("No hay conexion a la bd");
            }
        }catch(SQLException e ){
            System.out.println("Error:"+e.getMessage());
        }
    }
    
}



