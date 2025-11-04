/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Diurno
 */
public class DetalleCompra {
    private int id_compra;
    private int id_proveedor;
    private int id_producto;
    private int cantidad;
    private double precio;

    public DetalleCompra(int id_compra, int id_proveedor, int id_producto, int cantidad, double precio) {
        this.id_compra = id_compra;
        this.id_proveedor = id_proveedor;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "DetalleCompra{" + "id_compra=" + id_compra + ", id_proveedor=" + id_proveedor + ", id_producto=" + id_producto + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
    
}
