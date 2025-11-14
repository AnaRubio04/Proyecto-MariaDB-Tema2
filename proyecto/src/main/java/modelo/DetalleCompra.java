/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *  Clase que representala tabla Detalle Compra
 * @author Ana, Kamila, Usue, Alex
 */
public class DetalleCompra {
    /**
     * Entero que representa el id de la compra
     */
    private int id_compra;
    /**
     * Entero que representa el id del proveedor
     */
    private int id_proveedor;
    /**
     * Entero que representa el id del producto comprado
     */
    private int id_producto;
    /**
     * Entero que representa la cantidad del producto comprado
     */
    private int cantidad;
    /**
     * Precio del producto comprado
     */
    private double precio;

    /**
     * Constructo al que se le pasa el id_compra, id_proveedor, 
     * id_producto, cantidad y precio
     * 
     * @param id_compra id de la compra
     * @param id_proveedor id del provedor que realiza la compra
     * @param id_producto id del producto comprado
     * @param cantidad cantidad del producto comprado
     * @param precio precio del producto
     */
    public DetalleCompra(int id_compra, int id_proveedor, int id_producto, int cantidad, double precio) {
        this.id_compra = id_compra;
        this.id_proveedor = id_proveedor;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    /**
     * Método que devuelve el id de la compra
     * @return id de la compra
     */
    public int getId_compra() {
        return id_compra;
    }

    /**
     * Método para asignar un id compra
     * @param id_compra id compra a asignar
     */
    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    /**
     * Método que devuelve el id del proveedor vinculado a la compra
     * @return id proveedor de la compra
     */
    public int getId_proveedor() {
        return id_proveedor;
    }

    /**
     * Método para asignar un id proveedor
     * @param id_proveedor id proveedor a asignar a la compra
     */
    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    /**
     * Método que devuelve el id del producto vinculado a la compra
     * @return id producto de la compra
     */
    public int getId_producto() {
        return id_producto;
    }

    /**
     * Método para asignar un id producto a la compra
     * @param id_producto id proveedor a asignar
     */
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    /**
     *  Método que devuelve la cantidad del producto comprado
     * @return cantidad de producto comprado
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Método para asignar la cantidad comprada
     * @param cantidad cantidad comprada
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Método que devuelve el precio del producto comprado
     * @return precio producto
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Método para asignar un precio
     * @param precio precio a asignar
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Método toString
     * @return cadena con los datos del detalle de la compra 
     */
    @Override
    public String toString() {
        return "DetalleCompra{" + "id_compra=" + id_compra + ", id_proveedor=" + id_proveedor + ", id_producto=" + id_producto + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
    
}
