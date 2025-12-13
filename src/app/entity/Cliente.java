package app.entity;

import java.util.Date;

public class Cliente extends Persona{
    private String correo, estatus;
    private Date fecha_creacion;
    private Cuenta cuenta;

    public Cliente(String correo, String estatus, Date fecha_creacion, Cuenta cuenta, String nombre, String direccion, int edad, int telefono, char sexo) {
        super(nombre, direccion, edad, telefono, sexo);
        this.correo = correo;
        this.estatus = estatus;
        this.fecha_creacion = fecha_creacion;
        this.cuenta = cuenta;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}