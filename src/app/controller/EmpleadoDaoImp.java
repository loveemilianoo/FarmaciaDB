package app.controller;

import app.utils.Conexion;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class EmpleadoDaoImp implements EmpleadoDAO{

    @Override
    public void construirTabla(DefaultTableModel tabla) {
        PreparedStatement ps = null;
        Connection conn = Conexion.obtenerConexion();
        
        try{
            String query = "SELECT e.id_usuario, nombre, edad, sexo, telefono, direccion "
                    + "FROM personas p, empleados e WHERE p.id_persona = e.id_persona";
            
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
