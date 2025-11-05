/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Producto {

        private int id;
        private int id_proveedor; 
        private String nombre; 
        private double precio; 
        private int stock;
        
        public Producto() {
        }

    public Producto(int id, int id_proveedor, String nombre, double precio, int stock) {
        this.id = id;
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

        

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId_proveedor() {
            return id_proveedor;
        }

        public void setId_proveedor(int id_proveedor) {
            this.id_proveedor = id_proveedor;
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

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", id_proveedor=" + id_proveedor + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + '}';
    }
    
        
}

