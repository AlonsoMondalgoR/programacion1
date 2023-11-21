
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MiConexion {
    //vinculo con el Driver MySql
    static{
        try{
            Class .forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    //metodo de la conexion
    public Connection getConexion() throws SQLException, ClassCastException{
        Connection conn=null;
        try{
            //cadena de conexion
            //conn=DriverManager.getConnection("jdbc:mysql://localhost", "root", "");
            conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/champion", "root", "");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}


