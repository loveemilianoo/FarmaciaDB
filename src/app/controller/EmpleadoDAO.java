package app.controller;

import app.entity.Empleado;
import javax.swing.table.DefaultTableModel;

public interface EmpleadoDAO {
    public abstract void insertarEmpleado(Empleado empleado);
    public abstract void construirTabla(DefaultTableModel tabla);
    public abstract Empleado consultarEmpleado(int id);
    public abstract void eliminarEmpleado(int id);
    public abstract Empleado modificarEmpleado(int id, Empleado empleado);
}
