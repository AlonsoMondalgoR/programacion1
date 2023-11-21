
package controller;

import db.MiConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Campeonato;


public class ControladorCampeonato {
    //metodo agregar campeonato
    public int insertarCampeonato(Campeonato obj){
    int insertados=-1;
    Connection conn=null;//cadena de conexion bd
    PreparedStatement pstm=null;//prepara la consulta sql o transaccion
    try{
        conn=new MiConexion().getConexion();
        String sql="insert into campeonato values(null,?,?)";
        pstm=conn.prepareStatement(sql);
        //aqui se asigna valores
        pstm.setString(1, obj.getDescripcion());
        pstm.setInt(2, obj.getAnho());
        insertados=pstm.executeUpdate();
        JOptionPane.showMessageDialog(null, "Registrado Satisfactoriamente");
    }catch(Exception e){
        e.printStackTrace();
    }finally{
        try{
            if (pstm!=null) {
                pstm.close();
            }
            if (conn!=null){
                conn.close();
            }
               
        }catch(Exception e2){
            e2.printStackTrace();
        }
    }
    
    return insertados;
    }
    //metodo para actualizar campeonato
    public int actualizarCampeonato(Campeonato obj){
        int actualizados=-1;
        Connection conn=null;
        PreparedStatement pstm=null;
        try{
            conn=new MiConexion().getConexion();
            String sql="update campeonato set nombre=?,anho=? where id_campeonato=?";
            pstm=conn.prepareStatement(sql);
            pstm.setString(1, obj.getDescripcion());
            pstm.setInt(2, obj.getAnho());
            pstm.setInt(3, obj.getIdCampeonato());
            actualizados=pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Actualizado Satisfactoriamente");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if (pstm!=null) {
                    pstm.close();
                }
                if (conn!=null){
                    conn.close();
                }
               
            }catch(Exception e2){
            e2.printStackTrace();
            }
        }
        
                
        return actualizados;        
    }
    public int eliminarCampeonato(int idCampeonato){
        int eliminados=-1;
        Connection conn=null;
        PreparedStatement pstm=null;
        try{
            conn=new MiConexion().getConexion();
            String sql="delete from campeonato where id_campeonato=?";
            pstm=conn.prepareStatement(sql);
            pstm.setInt(1, idCampeonato);//parametro codigo
            eliminados=pstm.executeUpdate();//ejecutar transaccion
            JOptionPane.showMessageDialog(null, "Eliminado Satisfactoriamente");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
            if (pstm!=null) {
                pstm.close();
            }
            if (conn!=null){
                conn.close();
            }
               
        }catch(Exception e2){
            e2.printStackTrace();
        }
        }
        return eliminados;
    }
    //metodo para listar campeonato
    public List<Campeonato>listaCampeonato(){
        List<Campeonato>data=new ArrayList<Campeonato>();
        Connection conn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        try{
            conn=new MiConexion().getConexion();
            String sql="select * from campeonato";
            pstm=conn.prepareStatement(sql);
            rs=pstm.executeQuery();
            Campeonato c=null;
            while(rs.next()){
                c=new Campeonato();
                c.setIdCampeonato(rs.getInt("id_campeonato"));
                c.setDescripcion(rs.getString("nombre"));
                c.setAnho(rs.getInt("anho"));
                data.add(c);
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        return data;
    }
    public List<Campeonato>listaCampeonato2(){
        List<Campeonato>data=new ArrayList<Campeonato>();
        Connection conn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        try{
            conn=new MiConexion().getConexion();
            String sql="select c.id_campeonato,c.nombre,c.anho,p.nombre AS pais from campeonato c inner join pais p ON (c.idpais=p.idpais)";
            pstm=conn.prepareStatement(sql);
            rs=pstm.executeQuery();
            Campeonato c=null;
            while(rs.next()){
                c=new Campeonato();
                c.setIdCampeonato(rs.getInt("id_campeonato"));
                c.setDescripcion(rs.getString("nombre"));
                c.setAnho(rs.getInt("anho"));
                c.setPais(rs.getString("pais"));
                data.add(c);
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        return data;
    }
    //metodo para buscar un campeonato
    public List<Campeonato>buscarCampeonato(String nombre){
        ArrayList<Campeonato>data=new ArrayList<>();
        Connection conn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        try{
            conn=new MiConexion().getConexion();
            String sql="select * from campeonato where nombre like ?";
            pstm=conn.prepareStatement(sql);
            pstm.setString(1, nombre+"%");//el porcentaje completa la palabra 
            rs=pstm.executeQuery();
            Campeonato c;
            while(rs.next()){
                c=new Campeonato();
                c.setIdCampeonato(rs.getInt("id_campeonato"));
                c.setDescripcion(rs.getString("nombre"));
                c.setAnho(rs.getInt("anho"));
                data.add(c);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
            if (pstm!=null) {
                pstm.close();
            }
            if (conn!=null){
                conn.close();
            }
               
        }catch(Exception e2){
            e2.printStackTrace();
        }
        }
        
        return data;
    }
    
    public List<Campeonato>buscarEntreAnho(int inicio,int fin){
        ArrayList<Campeonato>data=new ArrayList<>();
        Connection conn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        try{
            conn=new MiConexion().getConexion();
            String sql="select c.id_campeonato,c.nombre,c.anho,p.nombre AS pais from campeonato c inner join pais p ON (c.idpais=p.idpais) where c.anho BETWEEN ? AND ?;";
            pstm=conn.prepareStatement(sql);
            pstm.setInt(1, inicio);
            pstm.setInt(2, fin);
            rs=pstm.executeQuery();
            Campeonato c;
            while(rs.next()){
                c=new Campeonato();
                c.setIdCampeonato(rs.getInt("id_campeonato"));
                c.setDescripcion(rs.getString("nombre"));
                c.setAnho(rs.getInt("anho"));
                c.setPais(rs.getString("pais"));
                data.add(c);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
            if (pstm!=null) {
                pstm.close();
            }
            if (conn!=null){
                conn.close();
            }
               
        }catch(Exception e2){
            e2.printStackTrace();
        }
        }
        
        return data;
    }
    
    public List<Campeonato>buscarPais(String pais){
        ArrayList<Campeonato>data=new ArrayList<>();
        Connection conn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        try{
            conn=new MiConexion().getConexion();
            String sql="select c.id_campeonato,c.nombre,c.anho,p.nombre AS pais from campeonato c inner join pais p ON (c.idpais=p.idpais) where p.nombre = ?;";
            pstm=conn.prepareStatement(sql);
            pstm.setString(1, pais);//el porcentaje completa la palabra 
            rs=pstm.executeQuery();
            Campeonato c;
            while(rs.next()){
                c=new Campeonato();
                c.setIdCampeonato(rs.getInt("id_campeonato"));
                c.setDescripcion(rs.getString("nombre"));
                c.setAnho(rs.getInt("anho"));
                c.setPais(rs.getString("pais"));
                data.add(c);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
            if (pstm!=null) {
                pstm.close();
            }
            if (conn!=null){
                conn.close();
            }
               
        }catch(Exception e2){
            e2.printStackTrace();
        }
        }
        
        return data;
    }
    
}
