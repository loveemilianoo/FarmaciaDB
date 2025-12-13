package app.controller;

import app.entity.Empleado;
import app.utils.Conexion;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class EmpleadoDaoImp implements EmpleadoDAO{

    @Override
    public void insertarEmpleado(Empleado empleado) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.obtenerConexion();
            
            // 1. Insertar en Persona
            String qIdPersona = "INSERT INTO personas (nombre, edad, sexo, telefono, direccion) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(qIdPersona, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, empleado.getNombre());
            ps.setInt(2, empleado.getEdad());
            ps.setString(3, String.valueOf(empleado.getSexo()));
            ps.setString(4, empleado.getTelefono());
            ps.setString(5, empleado.getDireccion());
            ps.executeUpdate();
            
            // Obtener ID de persona generado
            int idPersona = 0;
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idPersona = rs.getInt(1);
            }
            
            // 2. Opcional: Crear cuenta si se requiere
            int idCuenta = 0;
            if (empleado.getCuenta() != null) {
                String qCuenta = "INSERT INTO cuentas (saldo, tarjeta, tipo, titular, banco, status) VALUES (?, ?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(qCuenta, Statement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, empleado.getCuenta().getSaldo());
                ps.setString(2, empleado.getCuenta().getTarjeta());
                ps.setString(3, "EMPLEADO");
                ps.setString(4, empleado.getNombre());
                ps.setString(5, empleado.getCuenta().getBanco());
                ps.setString(6, "ACTIVA");
                ps.executeUpdate();
                
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idCuenta = rs.getInt(1);
                }
            }
            
            // 3. Insertar en Empleados
            String qInsertaEmp = "INSERT INTO empleados (rfc, curp, tipo, id_persona, id_cuenta) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(qInsertaEmp);
            ps.setString(1, empleado.getRfc());
            ps.setString(2, empleado.getCurp());
            ps.setString(3, empleado.getTipo());
            ps.setInt(4, idPersona);
            
            if (idCuenta > 0) {
                ps.setInt(5, idCuenta);
            } else {
                ps.setNull(5, Types.INTEGER);
            }
            
            ps.executeUpdate();
            
            System.out.println("Empleado insertado correctamente");
            
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
    
    /**
     * Construye una tabla con los datos de empleados y personas
     */
    @Override
    public void construirTabla(DefaultTableModel tabla) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.obtenerConexion();
            // Limpiar tabla existente
            tabla.setRowCount(0);
            
            // Query con JOIN
            String query = "SELECT e.id_empleado, p.nombre, p.edad, p.sexo, p.telefono, p.direccion, "
                         + "e.rfc, e.curp, e.tipo, c.saldo "
                         + "FROM empleados e "
                         + "INNER JOIN personas p ON e.id_persona = p.id_persona "
                         + "LEFT JOIN cuentas c ON e.id_cuenta = c.id_cuenta "
                         + "ORDER BY p.nombre";
            
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            
            // Verificar que las columnas coincidan
            int columnas = rsmd.getColumnCount();
            
            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                tabla.addRow(fila);
            }
            
            System.out.println("Tabla de empleados construida correctamente");
            
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
    
    /**
     * Consulta un empleado por su ID
     */
    @Override
    public Empleado consultarEmpleado(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Empleado empleado = null;
        
        try {
            conn = Conexion.obtenerConexion();
            String query = "SELECT e.id_empleado, e.rfc, e.curp, e.tipo, "
                         + "p.id_persona, p.nombre, p.edad, p.sexo, p.telefono, p.direccion, "
                         + "c.id_cuenta, c.saldo, c.tipo as tipo_cuenta, c.status "
                         + "FROM empleados e "
                         + "INNER JOIN personas p ON e.id_persona = p.id_persona "
                         + "LEFT JOIN cuentas c ON e.id_cuenta = c.id_cuenta "
                         + "WHERE e.id_empleado = ?";
            
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                // Convertir sexo de String a char
                char sexoChar = ' ';
                String sexoStr = rs.getString("sexo");
                if (sexoStr != null && !sexoStr.isEmpty()) {
                    sexoChar = sexoStr.charAt(0);
                }
                
                // Crear Persona
                Persona persona = new Persona(
                    rs.getInt("id_persona"),
                    rs.getString("nombre"),
                    rs.getInt("edad"),
                    sexoChar,
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
                
                // Crear Cuenta (si existe)
                Cuenta cuenta = null;
                if (rs.getObject("id_cuenta") != null) {
                    cuenta = new Cuenta();
                    cuenta.setIdCuenta(rs.getInt("id_cuenta"));
                    cuenta.setSaldo(rs.getDouble("saldo"));
                    cuenta.setTipo(rs.getString("tipo_cuenta"));
                    cuenta.setStatus(rs.getString("status"));
                }
                
                // Crear Empleado
                empleado = new Empleado(
                    rs.getInt("id_empleado"),
                    rs.getString("rfc"),
                    rs.getString("curp"),
                    rs.getString("tipo"),
                    persona,
                    cuenta
                );
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
        return empleado;
    }
    
    /**
     * Elimina un empleado (y su persona asociada)
     */
    @Override
    public void eliminarEmpleado(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.obtenerConexion();
            // 1. Obtener id_persona y id_cuenta del empleado
            String query = "SELECT id_persona, id_cuenta FROM empleados WHERE id_empleado = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            int idPersona = 0;
            Integer idCuenta = null;
            
            if (rs.next()) {
                idPersona = rs.getInt("id_persona");
                idCuenta = rs.getInt("id_cuenta");
                if (rs.wasNull()) {
                    idCuenta = null;
                }
            }
            
            if (idPersona == 0) {
                System.err.println("No se encontró el empleado con ID: " + id);
                return;
            }
            
            // 2. Eliminar empleado
            String qElimEmp = "DELETE FROM empleados WHERE id_empleado = ?";
            ps = conn.prepareStatement(qElimEmp);
            ps.setInt(1, id);
            ps.executeUpdate();
            
            // 3. Eliminar cuenta (si existe)
            if (idCuenta != null) {
                String qElimCuenta = "DELETE FROM cuentas WHERE id_cuenta = ?";
                ps = conn.prepareStatement(qElimCuenta);
                ps.setInt(1, idCuenta);
                ps.executeUpdate();
            }
            
            // 4. Eliminar persona
            String qElimPerso = "DELETE FROM personas WHERE id_persona = ?";
            ps = conn.prepareStatement(qElimPerso);
            ps.setInt(1, idPersona);
            ps.executeUpdate();
            
            System.out.println("Empleado eliminado correctamente");
            
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
    
    /**
     * Modifica un empleado existente
     */
    @Override
    public Empleado modificarEmpleado(int id, Empleado empleado) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.obtenerConexion();
            // 1. Obtener id_persona del empleado
            String queryId = "SELECT id_persona FROM empleados WHERE id_empleado = ?";
            ps = conn.prepareStatement(queryId);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            int personaId = 0;
            while (rs.next()) {
                personaId = rs.getInt("id_persona");
            }
            // 2. Actualizar Persona
            String qUpdPers = "UPDATE personas SET nombre = ?, edad = ?, sexo = ?, telefono = ?, direccion = ? WHERE id_persona = ?";
            ps = conn.prepareStatement(qUpdPers);
            ps.setString(1, empleado.getNombre());
            ps.setInt(2, empleado.getEdad());
            ps.setString(3, String.valueOf(empleado.getSexo()));
            ps.setString(4, empleado.getTelefono());
            ps.setString(5, empleado.getDireccion());
            ps.setInt(6, personaId);
            ps.executeUpdate();
            
            // 3. Actualizar Empleado
            String qUpdEmp = "UPDATE empleados SET rfc = ?, curp = ?, tipo = ? WHERE id_empleado = ?";
            ps = conn.prepareStatement(qUpdEmp);
            ps.setString(1, empleado.getRfc());
            ps.setString(2, empleado.getCurp());
            ps.setString(3, empleado.getTipo());
            ps.setInt(4, id);
            ps.executeUpdate();
            
            // Devolver empleado actualizado
            return consultarEmpleado(id);
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
