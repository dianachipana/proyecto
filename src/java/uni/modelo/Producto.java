package uni.modelo;

public class Producto {
    //Las columnas que tiene la tabla Producto

    private int idProducto;
    private String nombre;
    private double precio;
    private int stock;
    //Constructor de la clase sin parametros

    public Producto() {
    }
    //Constructor de la clase con parametros

    public Producto(int idProducto, String nombre, double precio,int stock) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock=stock;
    }
    //Metodo toString de la clase que nos retorna
    //el nombre del producto

    @Override
    public String toString() {
        return nombre.toUpperCase();
    }
    //Metodos get y set de la clase

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
