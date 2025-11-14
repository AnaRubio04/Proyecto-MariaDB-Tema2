/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import modelo.Compra;
import modelo.CompraDetalleView;
import modelo.DetalleCompra;
import modelo.Producto;
import modelo.Proveedor;

/**
 * Clase que crea la conexión entre el entorno y la base de dato
 * @author Ana, Kamila, Usue, Alex
 */
public class Conexion {
/** Variables estaticas para la conexion de la base de datos, url del conector, usuario y contraseña. */
    private static String URL = "jdbc:mysql://localhost:3306/gestion_compras";
    private static String USER = "root";
    private static String PASSWORD = "";
/**
 * Metodo que obtiene la conexion con la base de datos
 * @return devuelve la conexion si ha salido y bien y null si no se ha podido conectar
 */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
/**
 * Metodo para insertar un proveedor en mi base de datos
 * @param nombre nombre del proveedor
 * @param contacto telefono del proveedor
 * @param email email del proveedor
 * @param direccion direccion del proveedor
 */
    public void insertarProveedor(String nombre, String contacto, String email, String direccion) {
        String sql = "INSERT INTO proveedores (nombre,contacto,email,direccion) VALUES (?,?,?,?)";
        try (Connection con=getConnection();
             PreparedStatement ps = con.prepareStatement(sql);){

            ps.setString(1, nombre);
            ps.setString(2, contacto);
            ps.setString(3, email);
            ps.setString(4, direccion);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al insertar el usuario: " + ex.getMessage());
        }

    }
/**
 * Metodo para insertar la compra en la base de datos
 * @param fecha fecha en la que se realizo la compra
 * @param idProveedor proveedor al que se le realizo la compra
 */
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
/**
 * Metodo para actualizar el precio del producto
 * @param producto producto que se va a cambiar
 * @param nuevoPrecio nuevo precio que se va a establecer
 */
    public void actualizarPrecioProducto(String producto, double nuevoPrecio) {
        String sql = """
                     UPDATE productos 
                     SET precio_unitario=? 
                     WHERE nombre=?;
                     """;
        try (Connection con=getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){
            ps.setDouble(1, nuevoPrecio);
            ps.setString(2, producto);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("No se ha podido actualizar el producto: " + e.getMessage());
        }
    }
/**
 * Metodo que modifica el detalle de compra
 * @param idCompra id de la compra que se va a modificar
 * @param cantidad cantidad a la que se va a cambiar
 */
    public void modificarDetalleCompra(int idCompra, int cantidad) {
                String sql = """
                             UPDATE detalle_compra 
                             SET cantidad = ?
                             WHERE id_compra = ?;
                             """;

                try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                        ps.setInt(1, cantidad);
                       // ps.setDouble(2, precio);
                        ps.setInt(2, idCompra);

                        
                        ps.executeUpdate();
                    
                } catch (SQLException e) {
                         System.err.println("Error al modificar la compra: " + e.getMessage());
    }
    }
//    public void eliminarProveedor(int idProveedor) {
//        String sql = "DELETE FROM proveedores WHERE id_proveedor=?";
//        try (Connection con=getConnection();
//            PreparedStatement ps = con.prepareStatement(sql);){
//            ps.setInt(1, idProveedor);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            System.err.println("No se ha podido eliminar el proveedor: " + e.getMessage());
//        }
//    }
/**
 * Metodo que elimina el proveedor
 * @param idProveedor proveedor que se va a eliminar de la base de datos
 */
    public void eliminarProveedor(int idProveedor) {
        String sql = "{ CALL eliminar_proveedor(?) }";
            try (Connection con = getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, idProveedor);
            cs.execute();

        } catch (SQLException e) {
            System.err.println("No se ha podido eliminar el proveedor: " + e.getMessage());
        }
    }
/**
 * Metodo que consulta las compras por proveedor
 * @param idProveedor se le pasa el id del proveedor a buscar
 * @return devuelve un arraylist con las compras que cumplen los requisitos
 */
    public ArrayList<CompraDetalleView> consultarCompraPorProveedor(int idProveedor) {

        ArrayList<CompraDetalleView> dCompras = new ArrayList<>();
        String sql = """
                     SELECT c.id_compra, d.id_proveedor,d.id_producto,d.cantidad, d.precio, c.fecha  
                     FROM compras c INNER JOIN detalle_compra d 
                     ON c.id_compra=d.id_compra 
                     WHERE d.id_proveedor=?;
                     """;

        try (Connection con=getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){

            ps.setInt(1, idProveedor);

            ResultSet resul = ps.executeQuery();
            while (resul.next()) {
                int id_compra = resul.getInt("c.id_compra");
                int id_proveedor = resul.getInt("d.id_proveedor");
                int id_producto = resul.getInt("d.id_producto");
                int cantidad = resul.getInt("d.cantidad");
                double precio = resul.getDouble("d.precio");
                Date fecha = resul.getDate("c.fecha");
                dCompras.add(new CompraDetalleView(id_compra, id_proveedor, id_producto, cantidad, precio, fecha));

            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return dCompras;
    }
/**
 * Metodo que consulta la compra por la fecha
 * @param fechaBuscada fecha por la que se va a filtrar
 * @return devuelve un arraylist con las compras que cumplen los requisitos
 */
    public ArrayList<CompraDetalleView> consultarCompraPorFecha(Date fechaBuscada) {

        ArrayList<CompraDetalleView> dCompras = new ArrayList<>();
        String sql = """
                     SELECT c.id_compra, d.id_proveedor,d.id_producto,d.cantidad, d.precio, c.fecha  
                     FROM compras c INNER JOIN detalle_compra d 
                     ON c.id_compra=d.id_compra 
                     WHERE c.fecha=?;
                     """;

        try (Connection con=getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){

            ps.setDate(1, (java.sql.Date) fechaBuscada);

            ResultSet resul = ps.executeQuery();
            while (resul.next()) {
                int id_compra = resul.getInt("c.id_compra");
                int id_proveedor = resul.getInt("d.id_proveedor");
                int id_producto = resul.getInt("d.id_producto");
                int cantidad = resul.getInt("d.cantidad");
                double precio = resul.getDouble("d.precio");
                Date fecha = resul.getDate("c.fecha");
                dCompras.add(new CompraDetalleView(id_compra, id_proveedor, id_producto, cantidad, precio, fecha));

            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return dCompras;
    }
/**
 * 
 * @param proveedor id de proveedor por el que se busca
 * @param fechaBuscada fecha por la que se va a filtrar
 * @return  devuelve un arraylist con las compras que cumplen los requisitos
 */
    public ArrayList<CompraDetalleView> consultarCompraPorProveedoryFecha(int proveedor, Date fechaBuscada) {

        ArrayList<CompraDetalleView> dCompras = new ArrayList<>();
        String sql = """
                     SELECT c.id_compra, d.id_proveedor,d.id_producto,d.cantidad, d.precio, c.fecha  
                     FROM compras c INNER JOIN detalle_compra d 
                     ON c.id_compra=d.id_compra 
                     WHERE c.fecha=? AND d.id_proveedor=?;
                     """;

        try (Connection con=getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){

            ps.setDate(1, (java.sql.Date) fechaBuscada);
            ps.setInt(2, proveedor);

            ResultSet resul = ps.executeQuery();
            while (resul.next()) {
                int id_compra = resul.getInt("c.id_compra");
                int id_proveedor = resul.getInt("d.id_proveedor");
                int id_producto = resul.getInt("d.id_producto");
                int cantidad = resul.getInt("d.cantidad");
                double precio = resul.getDouble("d.precio");
                Date fecha = resul.getDate("c.fecha");
                dCompras.add(new CompraDetalleView(id_compra, id_proveedor, id_producto, cantidad, precio, fecha));

            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return dCompras;
    }
/**
 * Metodo que obtiene todos los proveedores
 * @return devuelve una lista con todos los proveedores
 */
    public ArrayList<Proveedor> obtenerProveedores() {
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT id_proveedor, nombre, email, direccion, contacto FROM proveedores";

        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_proveedor");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                String contacto = rs.getString("contacto");
                proveedores.add(new Proveedor(id, nombre, email, direccion, contacto));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener proveedores: " + e.getMessage());
        }
        return proveedores;
    }
/**
 * Metodo que obtiene todos los ids de los productos
 * @return devuelve una lista con los productos
 */
    public ArrayList<Producto> sacarIdProductos() {

        ArrayList<Producto> productos = new ArrayList<>();
        String sql = """
                     SELECT *
                     FROM productos; 
                     """;

        try (Connection con=getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){

            ResultSet resul = ps.executeQuery();
            while (resul.next()) {
                int id = resul.getInt(1);
                int id_proveedor = resul.getInt(2);
                String nombre = resul.getString(3);
                double precio = resul.getDouble(4);
                int stock = resul.getInt(5);
                productos.add(new Producto(id, id_proveedor, nombre, precio, stock));
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return productos;
    }

//    public ArrayList<Proveedor> sacarProveedores() {
//        
//        ArrayList<Proveedor> proveedores = new ArrayList<>();
//        String sql = """
//                     SELECT *
//                     FROM proveedores; 
//                     """;
//                                                        
//        try (Connection con=getConnection();
//            PreparedStatement ps = con.prepareStatement(sql);){
//           
//            ResultSet resul = ps.executeQuery();
//            while (resul.next()) {
//                int id = resul.getInt(1);
//                String nombre = resul.getString(2);
//                int contacto = resul.getInt(3);
//                String email = resul.getString(4);
//                String dir = resul.getString(5);
//                proveedores.add(new Proveedor(id, nombre, email, dir, contacto));
//            }
//            
//        } catch (SQLException e) {
//            e.getMessage();
//        }
//        return proveedores;
//    }
    /**
     * Metodo que obtiene las fechas 
     * @return devuelve un arraylist con las compras filtradas por fecha
     */
    public ArrayList<Compra> sacarFechas() {

        ArrayList<Compra> compras = new ArrayList<>();
        String sql = """
                     SELECT DISTINCT *
                     FROM compras
                     GROUP BY fecha;
                     """;

        try (Connection con=getConnection();
            PreparedStatement ps = con.prepareStatement(sql);){

            ResultSet resul = ps.executeQuery();
            while (resul.next()) {
                int id_compra = resul.getInt(1);
                int id_proveedor = resul.getInt(2);
                Date fecha = (Date) resul.getDate(3);
                compras.add(new Compra(id_compra, id_proveedor, fecha));
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return compras;
    }
/**
 * Metodo que obtiene todos los ids de ccompras
 * @return devuelve un array con los ids de los detalles de compras
 */
    public ArrayList<Integer> obtenerIdsCompras() {
        ArrayList<Integer> compras = new ArrayList<>();
        String sql = "SELECT id_compra FROM detalle_compra ORDER BY id_compra";

        try (Connection con = getConnection(); 
              PreparedStatement ps = con.prepareStatement(sql);
              ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                compras.add(rs.getInt("id_compra"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener IDs de compras: " + e.getMessage());
        }

        return compras;

    }
    /**
     * Metodo que obtiene el detalle de la compras 
     * @param idCompra compra que se va a buscar
     * @return devuelve el detalle de la compra
     */
    public DetalleCompra obtenerDetalleCompraPorId(int idCompra) {
    DetalleCompra detalle = null;
    String sql = """
                 SELECT id_compra, id_proveedor, id_producto, cantidad, precio
                 FROM detalle_compra
                 WHERE id_compra = ?
                 """;

    try (Connection con = getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idCompra);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int idProveedor = rs.getInt("id_proveedor");
                int idProducto = rs.getInt("id_producto");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");

                detalle = new DetalleCompra(idCompra, idProveedor, idProducto, cantidad, precio);
            }
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener el detalle de la compra con ID " + idCompra + ": " + e.getMessage());
    }

    return detalle;
}
    /**
     * Metodo que obtiene el nombre de producto por ID
     * @param idProducto id de producto que se va a buscar
     * @return devuelve elnombre del producto
     */
    public String obtenerNombreProductoPorId(int idProducto) {
    String nombre = "";
    String sql = "SELECT nombre FROM productos WHERE id_producto = ?";

    try (Connection con = getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idProducto);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                nombre = rs.getString("nombre");
            }
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener el nombre del producto: " + e.getMessage());
    }

    return nombre;
}
    /**
     * Obtiene la cantidad de producto que hay en detalle compra
     * @param idCompra id de compra por la que se va a filtrar
     * @return la cantidad de compra
     */
    public int obtenerCantidadDetallePorCompra(int idCompra) {
          String sql = "SELECT SUM(cantidad) AS total_cantidad FROM detalle_compra WHERE id_compra = ?";
    int totalCantidad = 0;
    try (Connection cn = getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        
        ps.setInt(1, idCompra);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
             totalCantidad = rs.getInt("total_cantidad");
            
       
        }
        
    } catch (SQLException e) {
        System.err.println("Error al obtener cantidad total: " + e.getMessage());
    }
        return totalCantidad;
        
}
    
//    public double obtenerPrecioTotalPorCompra(int idCompra) {
//        double totalPrecio = 0.0;
//         String sql = "SELECT  precio AS total_precio FROM detalle_compra WHERE id_compra = ?";
//    
//    try (Connection cn = getConnection();
//         PreparedStatement ps = cn.prepareStatement(sql)) {
//        
//        ps.setInt(1, idCompra);
//        ResultSet rs = ps.executeQuery();
//        
//        if (rs.next()) {
//             totalPrecio = rs.getDouble("total_precio");
//            
//        }
//        
//    } catch (SQLException e) {
//        System.err.println("Error al obtener precio total: " + e.getMessage());
//    }
//
//
//        return totalPrecio;
//        }
    /**
     * Metodo que obtiene el total del precio de la compra
     * @param idCompra id de la compra por la que se va a filtrar
     * @return devuelve el precio de la compra
     */
    public double obtenerPrecioTotalPorCompra(int idCompra) {
    double precio = 0.0;
    String sql = """
                 SELECT p.precio_unitario 
                 FROM productos p JOIN detalle_compra d ON p.id_producto = d.id_producto 
                 WHERE d.id_compra = ?;
                 """;
    try (Connection cn = getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setInt(1, idCompra);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            precio = rs.getDouble("precio_unitario");
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener precio: " + e.getMessage());
    }
    return precio;
}
}