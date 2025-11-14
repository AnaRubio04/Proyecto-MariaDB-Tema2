/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase Compra que hace referencia a la tabla Compra de la bd.
 * @author Ana, Kamila, Usue, Alex
 */
public class Compra {
    /**
     * Entero que representa el id de la compra
     */
    private int id_compra;
    /**
     * Entero que representa el id del provedor
     */
    private int id_proveedor;
    /**
     * Date que representa la fecha de la compra
     */
    private Date fecha;

    /**
     * Constructo al que se le pasa id_compra, id_proveedor, y fecha
     * @param id_compra id de la compra
     * @param id_proveedor id del provedor que realizá la compra
     * @param fecha fecha de la compra
     */
    public Compra(int id_compra, int id_proveedor, Date fecha) {
        this.id_compra = id_compra;
        this.id_proveedor = id_proveedor;
        this.fecha = fecha;
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
     * @param id_proveedor id provedor a asignar a la compra
     */
    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    /**
     * Método que devuelve la fecha de la compra en Date
     * @return fecha de la compra
     */
    public Date getFecha() {
        return fecha;
    }
    
    /**
     * Método para asignar una fecha 
     * @param fecha fecha a asignar
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    /**
     * Método que devuelve la fecha parseada a un String 
     * @return cadena fecha de la compra
     */
    public String getFechaString(){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaLegible = formato.format(fecha);
        return fechaLegible;
    }
}
