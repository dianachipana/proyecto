package uni.modelo;

public class DetalleVenta {
    //Las columnas que tiene la tabla DetalleVenta

    private int idVenta;
    private int idProducto;
    private double cantidad;
    private double descuento;
    private double parcial;
    private double subtotal;
    private Producto producto;
    private Venta venta;

    //Constructor sin parametros
    public DetalleVenta() {
    }

    //Constructor con parametros
    public DetalleVenta(int idVenta, int idProducto, double cantidad, double descuento, double parcial, double subtotal, Producto producto, Venta venta) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.parcial = parcial;
        this.subtotal = subtotal;
        this.producto = producto;
        this.venta = venta;
    }


    //Metodos Get y Set de la clase

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    public double getParcial() {
        return parcial;
    }

    public void setParcial(double parcial) {
        this.parcial = parcial;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
