package app.controller;

import javax.swing.table.DefaultTableModel;

import app.entity.Producto;

public interface ProductoDAO {
    public abstract void insertProducto(Producto producto);
    public abstract void construirTabla (DefaultTableModel tabla);
    public abstract Producto consultarProducto (int id);
    public abstract void eliminarProducto (int id);
    public abstract Producto modificarProducto (int id, Producto producto);
    public abstract String obtenerProveedores ();
}
