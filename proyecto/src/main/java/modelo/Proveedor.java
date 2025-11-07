/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author User
 */
public class Proveedor {
   private int idProveedor;
   private String nombre;
   private String email;
   private String direccion;
   private String contacto;

    public Proveedor(int idProveedor, String nombre, String email, String direccion, String contacto) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.contacto = contacto;
    }

    public Proveedor(String nombre, String email, String direccion, String contacto) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        setContacto(contacto);
    }
    
   

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        if (contacto != null && contacto.matches("\\d{9}")) {
            this.contacto = contacto;
        } else {
            throw new IllegalArgumentException("Número de teléfono no válido: " + contacto);
        }
    }
   
}
