package app.controller;

import javax.swing.table.DefaultTableModel;
import app.entity.*;
import app.utils.Conexion;
import java.sql.*;

public class ProductoDaoImp implements ProductoDAO {

    @Override
    public void insertProducto(Producto producto) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.obtenerConexion();
            
            String qInsertaProducto = "INSERT INTO productos (nombre_comercial, nombre_generico, formula, presentacion, control, tipo, precio, stock, id_proveedor) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(qInsertaProducto);
            ps.setString(1, producto.getNombreComercial());
            ps.setString(2, producto.getNombreGenerico());
            ps.setString(3, producto.getFormula());
            ps.setString(4, producto.getPresentacion());
            ps.setString(5, producto.getControl());
            ps.setString(6, producto.getTipo());
            ps.setDouble(7, producto.getPrecio());
            ps.setInt(8, producto.getStock());
            ps.setInt(9, producto.getId_proveedor());
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
            
            String query = "SELECT p.id_producto, p.nombre_comercial, p.nombre_generico, p.formula, p.presentacion, p.control, p.tipo, "
                    + "p.precio, p.stock, pr.nombre "
                    + "FROM productos p "
                    + "LEFT JOIN proveedores pr ON p.id_proveedor = pr.id_proveedor";
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
    public Producto consultarProducto(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Producto producto = null;
        
        try {
            conn = Conexion.obtenerConexion();
            String query = "SELECT p.nombre_comercial, p.nombre_generico, p.formula, p.presentacion, p.control, p.tipo, "
                    + "p.precio, p.stock, p.id_proveedor, pr.nombre AS proveedor "
                    + "FROM productos p LEFT JOIN proveedores pr ON p.id_proveedor = pr.id_proveedor "
                    + "WHERE p.id_producto = ?";
            
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while (rs.next()){
                producto = new Producto (
                        rs.getString("nombre_comercial"), 
                        rs.getString("nombre_generico"), 
                        rs.getString("presentacion"), 
                        rs.getString("formula"), 
                        rs.getString("tipo"), 
                        rs.getString("control"), 
                        rs.getString("proveedor"),
                        rs.getDouble("precio"), 
                        rs.getInt("stock"), 
                        rs.getInt("id_proveedor"));
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
        return producto;
    }
    
    @Override
    public void eliminarProducto(int id) {
        PreparedStatement ps = null;
        Connection conn = null;
        
        try {
            conn = Conexion.obtenerConexion();
            
            String qElimProducto = "DELETE FROM productos WHERE id_producto = ?";
            ps = conn.prepareStatement(qElimProducto);
            ps.setString(1, String.valueOf(id));
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
    public Producto modificarProducto(int id, Producto producto) {
        PreparedStatement ps = null;
        Connection conn = null;
        
        try {
            conn = Conexion.obtenerConexion();
            
            String qUpdProducto = "UPDATE productos SET nombre_comercial = ?, nombre_generico = ?, formula = ?, presentacion = ?, control = ?, tipo = ?, precio = ?, stock = ?, id_proveedor = ? WHERE id_producto = ?";
            ps = conn.prepareStatement(qUpdProducto);
            ps.setString(1, producto.getNombreComercial());
            ps.setString(2, producto.getNombreGenerico());
            ps.setString(3, producto.getFormula());
            ps.setString(4, producto.getPresentacion());
            ps.setString(5, producto.getControl());
            ps.setString(6, producto.getTipo());
            ps.setDouble(7, producto.getPrecio());
            ps.setInt(8, producto.getStock());
            ps.setInt(9, producto.getId_proveedor());
            ps.setInt(10, id);
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
        return producto;
    }
    
    @Override
    public String obtenerProveedores() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        StringBuilder proveedores = new StringBuilder();
        
        try {
            conn = Conexion.obtenerConexion();
            
            String query = "SELECT id_proveedor, nombre FROM proveedores ORDER BY nombre";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                if (proveedores.length() > 0) {
                    proveedores.append(",");
                }
                proveedores.append(rs.getInt("id_proveedor")).append(":").append(rs.getString("nombre"));
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
        return proveedores.toString();
    }
}