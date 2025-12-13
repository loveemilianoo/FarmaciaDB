package app.controller;

import javax.swing.table.DefaultTableModel;

import app.entity.Cliente;

public interface ClienteDAO {
    public abstract void insertarEmpleado(Cliente cliente);
    public abstract void construirTabla(DefaultTableModel tabla);
    public abstract Cliente consultarEmpleado(int id);
    public abstract void eliminarEmpleado(int id);
    public abstract Cliente modificarEmpleado(int id, Cliente cliente);
}
