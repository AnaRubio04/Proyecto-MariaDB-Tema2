/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import modelo.DetalleCompra;
import modelo.Proveedor;

/**
 *
 * @author Diurno
 */
public class Conexion {

    private static String URL = "jdbc:mysql://localhost:3306/gestion_compras";
    private static String USER = "root";
    private static String PASSWORD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertarProveedor(String nombre, int contacto, String email, String direccion) {
        String sql = "INSERT INTO proveedores (nombre,contacto,email,direccion) VALUES (?,?,?,?)";
        try (Connection con=getConnection();
             PreparedStatement ps = con.prepareStatement(sql);){

            ps.setString(1, nombre);
            ps.setInt(2, contacto);
            ps.setString(3, email);
            ps.setString(4, direccion);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al insertar el usuario: " + ex.getMessage());
        }

    }

    public void insertarCompra(Date fecha, int idProveedor) {
        String sql = "INSERT INTO compras (fecha,id_proveedor) VALUES (?,?)";
        try (Connection con=getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){
            ps.setDate(1, (java.sql.Date) fecha);
            ps.setInt(2, idProveedor);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar la compra: " + e.getMessage());
        }
    }

    public void actualizarPrecioProducto(int idProducto, double nuevoPrecio) {
        String sql = """
                     UPDATE productos 
                     SET precio_unitario=? 
                     WHERE id_producto=?;
                     """;
        try (Connection con=getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){
            ps.setDouble(1, nuevoPrecio);
            ps.setInt(2, idProducto);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("No se ha podido actualizar el producto: " + e.getMessage());
        }
    }

    public void modificarCompra(int idCompra, Date fecha, int idProveedor) {
        String sql = """
                     UPDATE compras 
                     SET id_proveedor=? , fecha=? 
                     WHERE id_compra=?;
                     """;
        try (Connection con=getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, idProveedor);
            ps.setDate(2, (java.sql.Date) fecha);
            ps.setInt(3, idCompra);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("No se ha podido modificar la compra: " + e.getMessage());
        }
    }
    public void eliminarProveedor(int idProveedor) {
        String sql = "DELETE FROM proveedores WHERE id_proveedor=?";
        try (Connection con=getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, idProveedor);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("No se ha podido eliminar el proveedor: " + e.getMessage());
        }
    }
    public ArrayList<DetalleCompra> consultarCompraPorProveedor(int idProveedor) {

        ArrayList<DetalleCompra> dCompras = new ArrayList<>();
        String sql = """
                     SELECT id_compra, id_proveedor, id_producto, cantidad, precio
                     FROM detalle_compra 
                     WHERE id_proveedor=?
                     """;

        try (Connection con=getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){

            ps.setInt(1, idProveedor);

            ResultSet resul = ps.executeQuery();
            while (resul.next()) {
                int id_compra = resul.getInt(1);
                int id_proveedor = resul.getInt(2);
                int id_producto = resul.getInt(3);
                int cantidad = resul.getInt(4);
                double precio = resul.getDouble(5);
                dCompras.add(new DetalleCompra(id_compra, id_proveedor, id_producto, cantidad, precio));
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return dCompras;
    }

    public ArrayList<Proveedor> obtenerProveedores() {
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT id_proveedor, nombre, email, direccion, contacto FROM proveedores";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_proveedor");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                int contacto = rs.getInt("contacto");
                proveedores.add(new Proveedor(id, nombre, email, direccion, contacto));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener proveedores: " + e.getMessage());
        }
        return proveedores;
    }
}
