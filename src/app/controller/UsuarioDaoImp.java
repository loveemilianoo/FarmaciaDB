package app.controller;

import app.utils.Conexion;
import java.sql.*;
import app.entity.*;
import javax.swing.table.DefaultTableModel;

public class UsuarioDaoImp implements UsuarioDAO {
    @Override
    public Usuario validarUsuario(String login, String password) {
        Usuario usuario = null;
        PreparedStatement ps= null;
        Connection conn = Conexion.obtenerConexion();
        ResultSet rs = null;
        
        try{
            String query = "SELECT login, password, nombre, direccion, edad, telefono, sexo "
                    + "FROM personas p, usuarios u WHERE  login=? AND password=? AND p.id_persona = u.id_persona";
            ps = conn.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            
            while (rs.next()){
                usuario = new Usuario (
                        rs.getString("login"),
                        rs.getString("password"), 
                        rs.getString("nombre"), 
                        rs.getString("direccion"), 
                        rs.getInt("edad"), 
                        rs.getInt("telefono"), 
                        rs.getString(("sexo")).charAt(0));
            }
        } catch (SQLException e){
            System.out.println("Error en la base de datos: "+e.toString());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e){
                System.out.println("Error al cerrar recursos: "+e.toString());
            }
        }
        return usuario;
    }

    @Override
    public void insertaUsuario(Usuario usuario) {
        Connection conn= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            conn = Conexion.obtenerConexion();
            // Query para insartar en Persona
            String qIdPersona = "INSERT INTO personas (nombre, edad, sexo, telefono, direccion) VALUES (?,?,?,?,?) ";
            ps = conn.prepareStatement(qIdPersona);
            ps.setString(1, usuario.getNombre());
            ps.setInt(2, usuario.getEdad());
            ps.setString(3, String.valueOf(usuario.getSexo()));
            ps.setInt(4, usuario.getTelefono());
            ps.setString(5, usuario.getDireccion());
            ps.executeUpdate();
            
            int idPersona = 0;
            while (rs.next()){
                idPersona = rs.getInt("edad");
            }
            // Query para insertar Usuario
            String qInsertaU = "INSERT INTO usuarios (id_persona, login, password) VALUES (?,?,?)";
            ps = conn.prepareStatement(qInsertaU);
            ps.setInt(1, idPersona);
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getPassword());
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.toString());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e){
                System.out.println("Error al cerrar recursos: "+e.toString());
            }
        }
    }

    @Override
    public void construirTabla(DefaultTableModel tabla) {
        PreparedStatement ps = null;
        Connection conn = Conexion.obtenerConexion();
        
        try{
            String query = "SELECT u.id_usuario, nombre, edad, sexo, telefono, direccion "
                    + "FROM personas p, usuarios u WHERE p.id_persona = e.id_persona";
            
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            
            int columnas = rsmd.getColumnCount();
            while (rs.next()){
                Object [] fila = new Object [columnas];
                for (int i=0; i < columnas; i++){
                    fila [i] = rs.getObject(i+1);
                }
                tabla.addRow(fila);
            }
            System.out.println("Tabla bien mostrada");
        } catch (SQLException e){
            System.out.println("Error en la base de datos: "+e.toString());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e){
                System.out.println("Error al cerrar recursos: "+e.toString());
            }
        }
    }
    
    
}
