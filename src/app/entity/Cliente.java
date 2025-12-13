package app.entity;

public class Cliente extends Persona{
    private String correo, estatus, membresia;
    private Cuenta cuenta;

    public Cliente(String correo, String estatus, String membresia, Cuenta cuenta, String nombre, String direccion, int edad, int telefono, char sexo) {
        super(nombre, direccion, edad, telefono, sexo);
        this.correo = correo;
        this.estatus = estatus;
        this.membresia = membresia;
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

    public String getMembresia() {
        return membresia;
    }

    public void setMembresia(String membresia) {
        this.membresia = membresia;
    }

    
}