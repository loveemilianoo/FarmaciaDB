package app.controller;

import javax.swing.table.DefaultTableModel;

import app.entity.Cliente;

public interface ClienteDAO {
    public abstract void insertarCliente(Cliente cliente);
    public abstract void construirTabla(DefaultTableModel tabla);
    public abstract Cliente consultarCliente(int id);
    public abstract void eliminarCliente(int id);
    public abstract Cliente modificarCliente(int id, Cliente cliente);
}
