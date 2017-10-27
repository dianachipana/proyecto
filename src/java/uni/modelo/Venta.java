package uni.modelo;

import java.sql.Timestamp;

public class Venta {
    //Las columnas que tiene la tabla Venta

    private int idVenta;
    private String cliente;
    private Timestamp fecha;
    //Constructor de la clase sin parametros

    public Venta() {
    }
    //Constructor de la clase con parametros

    public Venta(int idVenta, String cliente, Timestamp fecha) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.fecha = fecha;
    }
    //Metodos get y set de la clase

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
