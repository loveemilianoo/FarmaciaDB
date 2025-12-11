package app.entity;

public class Producto {
    private String nombreComercial, nombreGenerico, presentacion, formula, tipo, control;
    private double precio;
    private boolean receta;

    public Producto(String nombreComercial, String nombreGenerico, String presentacion, String formula, String tipo, String control, double precio, boolean receta) {
        this.nombreComercial = nombreComercial;
        this.nombreGenerico = nombreGenerico;
        this.presentacion = presentacion;
        this.formula = formula;
        this.tipo = tipo;
        this.control = control;
        this.precio = precio;
        this.receta = receta;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNombreGenerico() {
        return nombreGenerico;
    }

    public void setNombreGenerico(String nombreGenerico) {
        this.nombreGenerico = nombreGenerico;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isReceta() {
        return receta;
    }

    public void setReceta(boolean receta) {
        this.receta = receta;
    }

    
}
