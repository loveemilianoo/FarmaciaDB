package app.controller;

import javax.swing.table.DefaultTableModel;

import app.entity.Proveedor;

public interface ProveedorDAO {
    public abstract void insertProveedor(Proveedor proveedor);
    public abstract void construirTabla (DefaultTableModel tabla);
    public abstract Proveedor consultarProveedor (int id);
    public abstract void eliminarProveedor (int id);
    public abstract Proveedor modificarProveedor (int id, Proveedor proveedor);
}
