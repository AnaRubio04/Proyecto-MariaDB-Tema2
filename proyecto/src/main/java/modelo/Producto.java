/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Clase que representa la tabla producto
 * @author Ana, Kamila, Usue, Alex
 */
public class Producto {

        /**
         * Entero que representa en id del producto
         */
        private int id;
        /**
         * Entero que representa en id del proveedor
         */
        private int id_proveedor; 
        /**
         * Cadena del nombre del producto
         */
        private String nombre; 
        /**
         * Precio del producto
         */
        private double precio; 
        /**
         * Stock del producto
         */
        private int stock;
        
        /**
         * Constructor vacío
         */
        public Producto() {
        }

        /**
         * Constructor al que se pasa el id del producto, id_proveedor, nombre, precio y el stock
         * @param id id del producto
         * @param id_proveedor id del proveedor que provee el producto
         * @param nombre nombre del producto
         * @param precio precio del producto
         * @param stock stock del producto
         */
        public Producto(int id, int id_proveedor, String nombre, double precio, int stock) {
            this.id = id;
            this.id_proveedor = id_proveedor;
            this.nombre = nombre;
            this.precio = precio;
            this.stock = stock;
        }

        /**
         * Método que devuelve el id del producto
         * @return id del producto
         */
        public int getId() {
            return id;
        }

        /**
         * Método para asignar el id del producto
         * @param id id a asignar
         */
        public void setId(int id) {
            this.id = id;
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
         * Método que devuelve el nombre del producto
         * @return nombre del producto
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * Método para asignar el nombre del preducto
         * @param nombre nombre a asignar al producto
         */
        public void setNombre(String nombre) {
            this.nombre = nombre;
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
         * Método que devuelve la cantidad del Stock
         * @return stock
         */
        public int getStock() {
            return stock;
        }

        /**
         * Método para asignarle un stock al producto
         * @param stock stock a signar al producto
         */
        public void setStock(int stock) {
            this.stock = stock;
        }

        /**
         * Método toString
         * @return cadena con la información del producto
         */
        @Override
        public String toString() {
            return "Producto{" + "id=" + id + ", id_proveedor=" + id_proveedor + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + '}';
        }
    
        
}

