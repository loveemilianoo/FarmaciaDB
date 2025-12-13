package app.entity;

public class Empleado extends Persona{
    private String rfc, curp, tipo;
    private Cuenta cuenta;

    public Empleado(String rfc, String curp, String tipo, Cuenta cuenta, String nombre, String direccion, int edad, int telefono, char sexo) {
        super(nombre, direccion, edad, telefono, sexo);
        this.rfc = rfc;
        this.curp = curp;
        this.tipo = tipo;
        this.cuenta = cuenta;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    
}