package app.entity;

public class Producto {
    private String nombreComercial, nombreGenerico, presentacion, formula, tipo, control, proveedor;
    private double precio;
    private int stock, id_proveedor;

    public Producto(String nombreComercial, String nombreGenerico, String presentacion, String formula, String tipo, String control, double precio, int stock) {
        this.nombreComercial = nombreComercial;
        this.nombreGenerico = nombreGenerico;
        this.presentacion = presentacion;
        this.formula = formula;
        this.tipo = tipo;
        this.control = control;
        this.precio = precio;
        this.stock = stock;
        this.id_proveedor = 0;
    }

    public Producto(String nombreComercial, String nombreGenerico, String presentacion, String formula, String tipo, String control, double precio, int stock, int id_proveedor) {
        this.nombreComercial = nombreComercial;
        this.nombreGenerico = nombreGenerico;
        this.presentacion = presentacion;
        this.formula = formula;
        this.tipo = tipo;
        this.control = control;
        this.precio = precio;
        this.stock = stock;
        this.id_proveedor = id_proveedor;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
}
