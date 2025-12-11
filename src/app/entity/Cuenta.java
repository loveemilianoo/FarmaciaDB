package app.entity;

public class Cuenta {
    private String tarjeta, tipo, titular, clabe, moneda, banco, estatus;
    private double saldo;

    public Cuenta(String tarjeta, String tipo, String titular, String clabe, String moneda, String banco, String estatus, double saldo) {
        this.tarjeta = tarjeta;
        this.tipo = tipo;
        this.titular = titular;
        this.clabe = clabe;
        this.moneda = moneda;
        this.banco = banco;
        this.estatus = estatus;
        this.saldo = saldo;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getClabe() {
        return clabe;
    }

    public void setClabe(String clabe) {
        this.clabe = clabe;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
}
