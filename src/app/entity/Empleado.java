package app.entity;

public class Empleado extends Persona{
    private String rfc, curp, tipo;

    public Empleado(String rfc, String curp, String tipo, String nombre, String direccion, int edad, int telefono, char sexo) {
        super(nombre, direccion, edad, telefono, sexo);
        this.rfc = rfc;
        this.curp = curp;
        this.tipo = tipo;
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
}