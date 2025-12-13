package app.controller;

import app.entity.*;
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
            
            //Insertar en Persona
            String qIdPersona = "INSERT INTO personas (nombre, edad, sexo, telefono, direccion) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(qIdPersona, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, empleado.getNombre());
            ps.setInt(2, empleado.getEdad());
            ps.setString(3, String.valueOf(empleado.getSexo()));
            ps.setInt(4, empleado.getTelefono());
            ps.setString(5, empleado.getDireccion());
            ps.executeUpdate();
            // Obtener ID de persona generado
            int idPersona = 0;
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                idPersona = rs.getInt(1);
            }
            //Crear cuenta si se requiere
            int idCuenta = 0;
            if (empleado.getCuenta() != null) {
                String qCuenta = "INSERT INTO cuentas (saldo, tipo_cuenta, banco, estatus, clabe) VALUES (?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(qCuenta, Statement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, empleado.getCuenta().getSaldo());
                ps.setString(2, empleado.getCuenta().getTipo());
                ps.setString(3, empleado.getCuenta().getBanco());
                ps.setString(4, empleado.getCuenta().getEstatus());
                ps.setString(5, empleado.getCuenta().getClabe());
                ps.executeUpdate();
                
                rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    idCuenta = rs.getInt(1);
                }
            }
            //Insertar en Empleados
            String qInsertaEmp = "INSERT INTO empleados (rfc, curp, tipo_cuenta, id_persona, id_cuenta) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(qInsertaEmp);
            ps.setString(1, empleado.getRfc());
            ps.setString(2, empleado.getCurp());
            ps.setString(3, empleado.getTipo());
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
            
            String query = "SELECT e.id_empleados, p.nombre, p.edad, p.sexo, p.telefono, "
                    + "p.direccion, e.rfc, e.curp, e.tipo, c.saldo, c.tipo_cuenta, c.estatus, c.clabe, c.banco "
                    + "FROM empleados e INNER JOIN personas p ON e.id_persona = p.id_persona "
                    + "LEFT JOIN cuentas c ON e.id_cuenta = c.id_cuenta ORDER BY p.nombre";
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
    public Empleado consultarEmpleado(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Empleado empleado = null;
        
        try {
            conn = Conexion.obtenerConexion();
            String query = "SELECT e.id_empleados, e.rfc, e.curp, e.tipo, "
                         + "p.id_persona, p.nombre, p.edad, p.sexo, p.telefono, p.direccion, "
                         + "c.id_cuenta, c.saldo, c.tipo_cuenta, c.estatus, c.clabe, c.banco "
                         + "FROM empleados e "
                         + "INNER JOIN personas p ON e.id_persona = p.id_persona "
                         + "LEFT JOIN cuentas c ON e.id_cuenta = c.id_cuenta "
                         + "WHERE e.id_empleados = ?";
            
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
                
                empleado = new Empleado (
                        rs.getString("rfc"), 
                        rs.getString("curp"), 
                        rs.getString("tipo"), 
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
        return empleado;
    }
    
    @Override
    public void eliminarEmpleado(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.obtenerConexion();
            
            String query = "SELECT id_persona, id_cuenta FROM empleados WHERE id_empleados = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            int idPersona = 0;
            Integer idCuenta = null;
            
            while (rs.next()) {
                idPersona = rs.getInt("id_persona");
                idCuenta = rs.getInt("id_cuenta");
            }

            String qElimEmp = "DELETE FROM empleados WHERE id_empleado = ?";
            ps = conn.prepareStatement(qElimEmp);
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
    public Empleado modificarEmpleado(int id, Empleado empleado) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.obtenerConexion();
            
            String queryId = "SELECT id_persona, id_cuenta FROM empleados WHERE id_empleados = ?";
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
            ps.setString(1, empleado.getNombre());
            ps.setInt(2, empleado.getEdad());
            ps.setString(3, String.valueOf(empleado.getSexo()));
            ps.setInt(4, empleado.getTelefono());
            ps.setString(5, empleado.getDireccion());
            ps.setInt(6, personaId);
            ps.executeUpdate();
            
            // 3. Actualizar Empleado
            String qUpdEmp = "UPDATE empleados SET rfc = ?, curp = ?, tipo = ? WHERE id_empleados = ?";
            ps = conn.prepareStatement(qUpdEmp);
            ps.setString(1, empleado.getRfc());
            ps.setString(2, empleado.getCurp());
            ps.setString(3, empleado.getTipo());
            ps.setInt(4, id);
            ps.executeUpdate();
            
            // 4. Actualizar cuenta
            String qUpCuen = "UPDATE cuentas SET saldo=?, tipo_cuenta=?, clabe=?, banco=?, estatus=? WHERE id_cuenta=?";
            ps = conn.prepareStatement(qUpdEmp);
            ps.setDouble(1, empleado.getCuenta().getSaldo());
            ps.setString(2, empleado.getCuenta().getTipo());
            ps.setString(3, empleado.getCuenta().getClabe());
            ps.setString(4, empleado.getCuenta().getBanco());
            ps.setInt(5, cuentaId);
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
        return empleado;
    }
    
}
