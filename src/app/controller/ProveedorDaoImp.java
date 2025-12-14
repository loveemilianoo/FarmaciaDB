package app.controller;

import javax.swing.table.DefaultTableModel;
import app.entity.*;
import app.utils.Conexion;
import java.sql.*;

public class ProveedorDaoImp implements ProveedorDAO {

    @Override
    public void insertProveedor(Proveedor proveedor) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.obtenerConexion();
            
            //Crear cuenta si se requiere
            String qCuenta = "INSERT INTO cuentas (saldo, tipo_cuenta, banco, estatus, clabe) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(qCuenta, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, proveedor.getCuenta().getSaldo());
            ps.setString(2, proveedor.getCuenta().getTipo());
            ps.setString(3, proveedor.getCuenta().getBanco());
            ps.setString(4, proveedor.getCuenta().getEstatus());
            ps.setString(5, proveedor.getCuenta().getClabe());
            ps.executeUpdate();
            int idCuenta = 0;
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                idCuenta = rs.getInt(1);
            }
            //Insertar en Proveedores
            String qInsertaProv = "INSERT INTO proveedores (nombre, telefono, direccion, correo, tipo, horario, sitio_web, persona_contacto, id_cuenta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(qInsertaProv);
            ps.setString(1, proveedor.getNombre());
            ps.setInt(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getDireccion());
            ps.setString(4, proveedor.getCorreo());
            ps.setString(5, proveedor.getTipo());
            ps.setString(6, proveedor.getHorario());
            ps.setString(7, proveedor.getSitoWeb());
            ps.setString(8, proveedor.getPersonaContacto());
            ps.setInt(9, idCuenta);
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
            
            String query = "SELECT pr.id_proveedor, pr.nombre, pr.direccion, pr.telefono, pr.correo, pr.tipo, pr.horario, "
                    + "pr.sitio_web, pr.persona_contacto, c.tipo_cuenta, c.clabe, c.banco, c.estatus, c.saldo "
                    + "FROM proveedores pr "
                    + "LEFT JOIN cuentas c ON pr.id_cuenta = c.id_cuenta";
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
    public Proveedor consultarProveedor(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Proveedor proveedor = null;
        
        try {
            conn = Conexion.obtenerConexion();
            String query = "SELECT pr.correo, pr.tipo, pr.horario, pr.sitio_web, pr.persona_contacto, pr.nombre, pr.direccion, pr.telefono, "
                    + "c.tipo_cuenta, c.clabe, c.banco, c.estatus, c.saldo "
                    + "FROM proveedores pr "
                    + "LEFT JOIN cuentas c ON pr.id_cuenta = c.id_cuenta "
                    + "WHERE pr.id_proveedor = ?";
            
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while (rs.next()){
                Cuenta cuenta = new Cuenta (
                        rs.getString("tipo_cuenta"), 
                        rs.getString("clabe"), 
                        rs.getString("banco"), 
                        rs.getString("estatus"), 
                        rs.getDouble("saldo"));
                
                proveedor = new Proveedor (
                        rs.getString("nombre"), 
                        rs.getString("correo"), 
                        rs.getString("direccion"), 
                        rs.getString("tipo"), 
                        rs.getString("horario"), 
                        rs.getString("sitio_web"), 
                        rs.getString("persona_contacto"), 
                        rs.getInt("telefono"), 
                        cuenta);
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
        return proveedor;
    }
    
    @Override
    public void eliminarProveedor(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.obtenerConexion();
            
            String query = "SELECT id_cuenta FROM proveedores WHERE id_proveedor = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            Integer idCuenta = null;
            
            while (rs.next()) {
                idCuenta = rs.getInt("id_cuenta");
            }

            String qElimProveedor = "DELETE FROM proveedores WHERE id_proveedor = ?";
            ps = conn.prepareStatement(qElimProveedor);
            ps.setString(1, String.valueOf(id));
            ps.executeUpdate();
           
            String qElimCuenta = "DELETE FROM cuentas WHERE id_cuenta = ?";
            ps = conn.prepareStatement(qElimCuenta);    
            ps.setString(1, String.valueOf(idCuenta));
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
    public Proveedor modificarProveedor(int id, Proveedor proveedor) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.obtenerConexion();
            
            String queryId = "SELECT id_cuenta FROM proveedores WHERE id_proveedor = ?";
            ps = conn.prepareStatement(queryId);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            int cuentaId=0;
            while (rs.next()) {
                cuentaId = rs.getInt("id_cuenta");
            }
            
            // 2. Actualizar Proveedor
            String qUpdProv = "UPDATE proveedores SET nombre = ?, telefono = ?, direccion = ?, correo = ?, tipo = ?, horario = ?, sitio_web = ?, persona_contacto = ? WHERE id_proveedor = ?";
            ps = conn.prepareStatement(qUpdProv);
            ps.setString(1, proveedor.getNombre());
            ps.setInt(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getDireccion());
            ps.setString(4, proveedor.getCorreo());
            ps.setString(5, proveedor.getTipo());
            ps.setString(6, proveedor.getHorario());
            ps.setString(7, proveedor.getSitoWeb());
            ps.setString(8, proveedor.getPersonaContacto());
            ps.setInt(9, id);
            ps.executeUpdate();
            
            // 4. Actualizar cuenta
            String qUpCuen = "UPDATE cuentas SET saldo=?, tipo_cuenta=?, clabe=?, banco=?, estatus=? WHERE id_cuenta=?";
            ps = conn.prepareStatement(qUpCuen);
            ps.setDouble(1, proveedor.getCuenta().getSaldo());
            ps.setString(2, proveedor.getCuenta().getTipo());
            ps.setString(3, proveedor.getCuenta().getClabe());
            ps.setString(4, proveedor.getCuenta().getBanco());
            ps.setString(5, proveedor.getCuenta().getEstatus());
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
        return proveedor;
    }
    
}
