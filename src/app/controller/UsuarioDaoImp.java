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
            ps = conn.prepareStatement(qIdPersona, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getNombre());
            ps.setInt(2, usuario.getEdad());
            ps.setString(3, String.valueOf(usuario.getSexo()));
            ps.setInt(4, usuario.getTelefono());
            ps.setString(5, usuario.getDireccion());
            ps.executeUpdate();
            
            int idPersona = 0;
            rs = ps.getGeneratedKeys();
            while (rs.next()){
                idPersona = rs.getInt(1);
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
                    + "FROM personas p, usuarios u WHERE p.id_persona = u.id_persona";
            
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

    @Override
    public Usuario consultarUsr(int id) {
        Usuario usuario = null;
        PreparedStatement ps= null;
        Connection conn = null;
        ResultSet rs = null;
        
        try{
            conn = Conexion.obtenerConexion();
            
            String query = "SELECT u.id_usuario, u.login, u.password, p.id_persona, p.nombre, p.edad, p.sexo, p.telefono, p.direccion "
                    + "FROM usuarios u INNER JOIN personas p ON u.id_persona = p.id_persona "
                    + "WHERE u.id_usuario = ?";
            
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            while (rs.next()){
                char sexoChar = rs.getString("sexo") != null ? rs.getString("sexo").charAt(0): ' ';
                
                usuario = new Usuario (
                        rs.getString("login"), rs.getString("password"), 
                        rs.getString("nombre"), rs.getString("direccion"), 
                        rs.getInt("edad"), rs.getInt("telefono"), sexoChar);
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
    public void eliminarUsuario(int id) {
        PreparedStatement ps= null;
        Connection conn = null;
        ResultSet rs = null;
        
        try{
            conn = Conexion.obtenerConexion();
            
            String query ="SELECT id_persona FROM usuarios WHERE id_usuario=?";
            ps= conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            int idPersona=0;
            while (rs.next()){
                idPersona = rs.getInt("id_persona");
            }
            
            
            String qElimUsr = "DELETE FROM usuarios WHERE id_usuario =?";
            ps = conn.prepareStatement(qElimUsr);
            ps.setString(1, String.valueOf(id));
            ps.executeUpdate();
            
            
            String qElimPerso = "DELETE FROM personas WHERE id_persona =?";
            ps = conn.prepareStatement(qElimPerso);
            ps.setString(1, String.valueOf(idPersona));
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error en la base de datos: "+e.toString());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e){
                System.out.println("Error al cerrar recursos: "+e.toString());
            }
        }
    }

    @Override
    public Usuario modificarUsuario(int id, Usuario usuario) {
        PreparedStatement ps = null;
        Connection conn = Conexion.obtenerConexion();
        ResultSet rs = null;
        
        try{
            String queryId = "SELECT id_persona FROM usuarios WHERE id_usuario=?";
            ps = conn.prepareStatement(queryId);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            int personaId=0;
            
            while (rs.next()){
                personaId = rs.getInt("id_persona");
            }
            
            
            String qUperPers = "UPDATE personas SET nombre=?, edad=?, sexo=?, telefono=?, direccion=? WHERE id_persona=? ";
            ps = conn.prepareStatement(qUperPers);
            ps.setString(1, usuario.getNombre());
            ps.setInt(2, usuario.getEdad());
            ps.setString(3, String.valueOf(usuario.getSexo()));
            ps.setInt(4, usuario.getTelefono());
            ps.setString(5, usuario.getDireccion());
            ps.setString(6, String.valueOf(personaId));
            ps.executeUpdate();
            
            
            String qUpUsr = "UPDATE usuarios SET login =?, password=? WHERE id_usuario=?";
            ps= conn.prepareStatement(qUpUsr);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, String.valueOf(id));
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error en la base de datos: "+e.toString());
            e.printStackTrace();
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
    
    
}
