/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Clase que representa a un proveedor.
 * Contiene información básica como el nombre, email, dirección, contacto y su id.
 * @author Ana, Kamila, Usue, Alex
 */
public class Proveedor {

    /**
     * Id del proveedor.
     */
    private int idProveedor;
    /**
     * Nombre del proveedor
     */
    private String nombre;
    /**
     * Email del proveedor
     */
    private String email;
    /**
     * Dirección del proveedor
     */
    private String direccion;
    /**
     * Contacto del proveedor
     */
    private String contacto;
    
    /**
     * Constructor completo de la clase
     * 
     * @param idProveedor identificador unico del proveedor
     * @param nombre nombre del proveedor
     * @param email email del proveedor
     * @param direccion dirección del proveedor
     * @param contacto contacto del proveedor
     */
    public Proveedor(int idProveedor, String nombre, String email, String direccion, String contacto) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.contacto = contacto;
    }

    /**
     * Constructor alternativo sin el identificador del proveedor.
     * Util cuando el id se genera en la base de datos.
     * Contiene validaciones.
     * 
     * @param nombre nombre del proveedor
     * @param email email del proveedor
     * @param direccion dirección del proveedor
     * @param contacto contacto del proveedor
     */
    public Proveedor(String nombre, String email, String direccion, String contacto) {
        this.nombre = nombre;
        setEmail(email);
        this.direccion = direccion;
        setContacto(contacto);
    }
    
    /**
     * Obtiene el identificador del proveedor
     * @return idProveedor
     */
    public int getIdProveedor() {
        return idProveedor;
    }
    
    /**
     * Asigna un identificador al proveedor
     * @param idProveedor identificador unico
     */
    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
    /**
     * Obtiene el nombre del proveedor
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Establece el nombre del proveedor
     * @param nombre nombre del proveedor
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Obtiene el email del proveedor
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece un email válido para el proveedor
     * Valida el formato mediante expresiones regulares.
     * 
     * @param email correo electrónico del proveedor
     * @throws IllegalArgumentException Si el formato del email es inválido
     */
    public void setEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!email.matches(regex)) {
            throw new IllegalArgumentException("El formato del email no es válido.");
        }

        this.email = email;
    }
    
    /**
     * Obtiene la dirección del proveedor
     * @return direccion
     */
    public String getDireccion() {
        return direccion;
    }
    
    /**
     * Establece la dirección del proveedor
     * @param direccion direccion del proveedor
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Obtiene el telefono del proveedor
     * @return contacto
     */
    public String getContacto() {
        return contacto;
    }
    
    /**
     * Establece el telefono válido.
     * Debe tener exactamente 9 digitos
     * @param contacto 
     * @throws IllegalArgumentException Si el telefono no tiene 9 digitos
     */
    public void setContacto(String contacto) {
        if (contacto != null && contacto.matches("\\d{9}")) {
            this.contacto = contacto;
        } else {
            throw new IllegalArgumentException("Número de teléfono no válido: " + contacto);
        }
    }

}
