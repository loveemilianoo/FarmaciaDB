package app.controller;

import javax.swing.table.DefaultTableModel;
import app.entity.*;
import app.utils.Conexion;
import java.sql.*;

public class ClienteDaoImp implements ClienteDAO {

    @Override
    public void insertarCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.obtenerConexion();
            
            //Insertar en Persona
            String qIdPersona = "INSERT INTO personas (nombre, edad, sexo, telefono, direccion) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(qIdPersona, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNombre());
            ps.setInt(2, cliente.getEdad());
            ps.setString(3, String.valueOf(cliente.getSexo()));
            ps.setInt(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.executeUpdate();
            // Obtener ID de persona generado
            int idPersona = 0;
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                idPersona = rs.getInt(1);
            }
            //Crear cuenta si se requiere
            String qCuenta = "INSERT INTO cuentas (saldo, tipo_cuenta, banco, estatus, clabe) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(qCuenta, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, cliente.getCuenta().getSaldo());
            ps.setString(2, cliente.getCuenta().getTipo());
            ps.setString(3, cliente.getCuenta().getBanco());
            ps.setString(4, cliente.getCuenta().getEstatus());
            ps.setString(5, cliente.getCuenta().getClabe());
            ps.executeUpdate();
            int idCuenta = 0;
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                idCuenta = rs.getInt(1);
            }
            //Insertar en Empleados
            String qInsertaCl = "INSERT INTO clientes (correo, estatus_cliente, membresia, id_persona, id_cuenta) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(qInsertaCl);
            ps.setString(1, cliente.getCorreo());
            ps.setString(2, cliente.getEstatus());
            ps.setString(3, cliente.getMembresia());
            ps.setInt(4, idPersona);
            ps.setInt(5, idCuenta);
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
    public void construirTabla(DefaultTableModel tabla) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.obtenerConexion();
            
            String query = "SELECT cl.id_cliente, p.nombre, p.direccion, p.edad, p.telefono, p.sexo, cl.correo, cl.estatus_cliente, cl.membresia, "
                    + "c.tipo_cuenta, c.clabe, c.banco, c.estatus, c.saldo "
                    + "FROM clientes cl "
                    + "INNER JOIN personas p ON cl.id_persona = p.id_persona "
                    + "LEFT JOIN cuentas c ON cl.id_cuenta = c.id_cuenta";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            
            int columnas = rsmd.getColumnCount();
            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                tabla.addRow(fila);
            }
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
    public Cliente consultarCliente(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Cliente cliente = null;
        
        try {
            conn = Conexion.obtenerConexion();
            String query = "SELECT cl.correo, cl.estatus_cliente, cl.membresia, p.nombre, p.direccion, p.edad, p.telefono, p.sexo, "
                    + "c.tipo_cuenta, c.clabe, c.banco, c.estatus, c.saldo "
                    + "FROM clientes cl "
                    + "INNER JOIN personas p ON cl.id_persona = p.id_persona "
                    + "LEFT JOIN cuentas c ON cl.id_cuenta = c.id_cuenta "
                    + "WHERE cl.id_cliente = ?";
            
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while (rs.next()){
                char sexoChar = rs.getString("sexo") != null ? rs.getString("sexo").charAt(0): ' ';
                
                Cuenta cuenta = new Cuenta (
                        rs.getString("tipo_cuenta"), 
                        rs.getString("clabe"), 
                        rs.getString("banco"), 
                        rs.getString("estatus"), 
                        rs.getDouble("saldo"));
                
                cliente = new Cliente (
                        rs.getString("correo"), 
                        rs.getString("estatus_cliente"), 
                        rs.getString("membresia"), 
                        cuenta, 
                        rs.getString("nombre"), 
                        rs.getString("direccion"), 
                        rs.getInt("edad"), 
                        rs.getInt("telefono"), 
                        sexoChar);
            }
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
        return cliente;
    }
    
    @Override
    public void eliminarCliente(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.obtenerConexion();
            
            String query = "SELECT id_persona, id_cuenta FROM clientes WHERE id_cliente = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            int idPersona = 0;
            Integer idCuenta = null;
            
            while (rs.next()) {
                idPersona = rs.getInt("id_persona");
                idCuenta = rs.getInt("id_cuenta");
            }

            String qElimCliente = "DELETE FROM clientes WHERE id_cliente = ?";
            ps = conn.prepareStatement(qElimCliente);
            ps.setString(1, String.valueOf(id));
            ps.executeUpdate();
           
            String qElimCuenta = "DELETE FROM cuentas WHERE id_cuenta = ?";
            ps = conn.prepareStatement(qElimCuenta);    
            ps.setString(1, String.valueOf(idCuenta));
            ps.executeUpdate();      
            
            String qElimPerso = "DELETE FROM personas WHERE id_persona = ?";
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
    public Cliente modificarCliente(int id, Cliente cliente) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.obtenerConexion();
            
            String queryId = "SELECT id_persona, id_cuenta FROM clientes WHERE id_cliente = ?";
            ps = conn.prepareStatement(queryId);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            int personaId = 0;
            int cuentaId=0;
            while (rs.next()) {
                personaId = rs.getInt("id_persona");
                cuentaId = rs.getInt("id_cuenta");
            }
            // 2. Actualizar Persona
            String qUpdPers = "UPDATE personas SET nombre = ?, edad = ?, sexo = ?, telefono = ?, direccion = ? WHERE id_persona = ?";
            ps = conn.prepareStatement(qUpdPers);
            ps.setString(1, cliente.getNombre());
            ps.setInt(2, cliente.getEdad());
            ps.setString(3, String.valueOf(cliente.getSexo()));
            ps.setInt(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setInt(6, personaId);
            ps.executeUpdate();
            
            // 3. Actualizar Empleado
            String qUpCl = "UPDATE clientes SET correo = ?, estatus_cliente = ?, membresia = ? WHERE id_cliente = ?";
            ps = conn.prepareStatement(qUpCl);
            ps.setString(1, cliente.getCorreo());
            ps.setString(2, cliente.getEstatus());
            ps.setString(3, cliente.getMembresia());
            ps.setInt(4, id);
            ps.executeUpdate();
            
            // 4. Actualizar cuenta
            String qUpCuen = "UPDATE cuentas SET saldo=?, tipo_cuenta=?, clabe=?, banco=?, estatus=? WHERE id_cuenta=?";
            ps = conn.prepareStatement(qUpCuen);
            ps.setDouble(1, cliente.getCuenta().getSaldo());
            ps.setString(2, cliente.getCuenta().getTipo());
            ps.setString(3, cliente.getCuenta().getClabe());
            ps.setString(4, cliente.getCuenta().getBanco());
            ps.setString(5, cliente.getCuenta().getEstatus());
            ps.setInt(6, cuentaId);
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
        return cliente;
    }
    
}
