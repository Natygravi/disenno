/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import config.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import Modelo.Item;
import Modelo.Valoracion;

/**
 *
 * @author saudd
 */
public class ItemDAO {
    private Connection conexion;
    
    public ItemDAO() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Conexion con  = new Conexion();
        this.conexion = con.getConexion();
    }
    
public List<Item> listarItems() throws SQLException {
    Connection conn = DriverManager.getConnection("jdbc:mysql://servidoratitup.mysql.database.azure.com:3306/disennoDB?useSSL=TRUE", "adminNaty", "Coquito2022?");
    List<Item> items = new ArrayList<>();
    String query = "SELECT * FROM item";
    try (PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            String prompt = rs.getString("prompt");
            String respuesta = rs.getString("respuesta");
            String fuente = rs.getString("fuente");
            String ejemploRelacionado = rs.getString("ejemplo_relacionado");
            String fuenteEjemplo = rs.getString("fuente_ejemplo");
            Item item = new Item(prompt, respuesta, fuente, ejemploRelacionado, fuenteEjemplo);
            items.add(item);
        }
    }
    return items;
}

    
    public void agregarItem(Item item) throws SQLException {
        //Connection conexion;
        //Conexion con  = new Conexion();
        //conexion = con.getConexion();
        System.out.println(conexion);

        try {
            // 2. Crear una transacción
            conexion.setAutoCommit(false);

            // 3. Insertar el nuevo Item en la tabla item y obtener el ID generado automáticamente
            PreparedStatement itemStmt = conexion.prepareStatement("INSERT INTO item (prompt, respuesta, fuente, ejemploRelacionado, fuenteEjemplo) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            itemStmt.setString(1, item.getPrompt());
            itemStmt.setString(2, item.getRespuesta());
            itemStmt.setString(3, item.getFuente());
            itemStmt.setString(4, item.getEjemploRelacionado());
            itemStmt.setString(5, item.getFuenteEjemplo());
            itemStmt.executeUpdate();

            // Obtener el ID generado automáticamente del nuevo Item
            ResultSet itemKeys = itemStmt.getGeneratedKeys();
            itemKeys.next();
            int idItem = itemKeys.getInt(1);

            // 4. Insertar cada Valoración en la tabla valoracion asociado con el ID del nuevo Item
            PreparedStatement valoracionStmt = conexion.prepareStatement("INSERT INTO valoracion (estrellas, comentarios, id_item) VALUES (?, ?, ?)");
            for (Valoracion valoracion : item.getValoracionesRes()) {
                valoracionStmt.setInt(1, valoracion.getEstrellas());
                valoracionStmt.setString(2, valoracion.getComentarios());
                valoracionStmt.setInt(3, idItem);
                valoracionStmt.executeUpdate();
            }

            // 5. Confirmar la transacción
            conexion.commit();
        } catch (SQLException ex) {
            // 6. Manejar cualquier excepción
            conexion.rollback();
            throw ex;
        } finally {
            // Cerrar la conexión
            conexion.close();
        }
    }
    
    
    public static void obtenerItemsPorCategoria(String categoria) throws ClassNotFoundException {
    try {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/prueba";
        String username = "root";
        String password = "naye2003";
        Connection conn = DriverManager.getConnection(url, username, password);
        String query = "SELECT item.prompt FROM categoria JOIN item ON FIND_IN_SET(item.id, categoria.items) WHERE categoria.nombre = ? ORDER BY item.prompt ASC;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, categoria);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String prompt = rs.getString("prompt");
            System.out.println(prompt);
        }
        rs.close();
        ps.close();
        conn.close();
    } catch (SQLException e) {
        System.out.println("Error al ejecutar la consulta: " + e);
    }
}

    public List<String> getItemsByCategoria(String categoria) {
        String url = "jdbc:mysql://localhost:3306/prueba";
        String username = "root";
        String password = "naye2003";
        Connection conexion = null;
        List<String> items = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url, username, password);
            try (PreparedStatement ps = conexion.prepareStatement("SELECT item.prompt FROM categoria JOIN item ON FIND_IN_SET(item.id, categoria.items) WHERE categoria.nombre = ? ORDER BY item.prompt ASC;")) {
                ps.setString(1, categoria); // Establecer el valor del parámetro
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String prompt = rs.getString("prompt");
                        items.add(prompt);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return items;
    }

    
    public Item getItemByPrompt(String prompt) throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/prueba";
        String username = "root";
        String password = "naye2003";
        Connection conexion = null;
        Item item = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM item WHERE prompt = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, prompt);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item = new Item(rs.getString("prompt"), rs.getString("respuesta"), rs.getString("fuente"), rs.getString("ejemplo_relacionado"), rs.getString("fuente_ejemplo"));
            }
        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }
        return item;
    }
    
    
    public Item buscarItemPorPrompt(String prompt) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "naye2003");
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Item WHERE prompt = ?");
        stmt.setString(1, prompt);
        ResultSet rs = stmt.executeQuery();
        Item item = null;
        while(rs.next()) {
            String respuesta = rs.getString("respuesta");
            String fuente = rs.getString("fuente");
            String ejemploRelacionado = rs.getString("ejemplo_relacionado");
            String fuenteEjemplo = rs.getString("fuente_ejemplo");
            item = new Item(prompt, respuesta, fuente, ejemploRelacionado, fuenteEjemplo);
            // Agregar las valoraciones si es necesario
            // ArrayList<Valoracion> valoraciones = obtenerValoraciones(item);
            // item.setValoraciones(valoraciones);
        }
        rs.close();
        stmt.close();
        conn.close();
        return item;
    } catch(SQLException e) {
        System.out.println("Error al buscar item: " + e.getMessage());
        return null;
    }
}

    
/*
    public static void obtenerItemsPorCategoria(String categoria) {

        try {
            boolean success = false;
            PreparedStatement ps = null;
            String query = "SELECT item.prompt FROM categoria JOIN item ON FIND_IN_SET(item.id, categoria.items) WHERE categoria.nombre = '?' ORDER BY item.prompt ASC;";
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {
                String prompt = rs.getString("prompt");
                System.out.println(prompt);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }
*/


    public  void agregarValoracionAItem(Item item, Valoracion valoracion) {
        try {
            Conexion con  = new Conexion();
            con.getConexion();
            boolean success = false;
            PreparedStatement ps = null;
            String query = "INSERT INTO valoraciones (estrellas, comentario) VALUES (?, ?)";
            ps = conexion.prepareStatement(query);
            ps.setInt(1, valoracion.getEstrellas());
            ps.setString(2, valoracion.getComentarios());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    public Item getItemByPrompts(String prompt) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/prueba";
        String username = "root";
        String password = "naye2003";
        Item item = null;
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM item WHERE prompt = ?";
            try(PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, prompt);
                try(ResultSet rs = stmt.executeQuery()) {
                    if(rs.next()) {
                        String respuesta = rs.getString("respuesta");
                        String fuente = rs.getString("fuente");
                        String ejemploRelacionado = rs.getString("ejemplo_relacionado");
                        String fuenteEjemplo = rs.getString("fuente_ejemplo");
                        item = new Item(prompt, respuesta, fuente, ejemploRelacionado, fuenteEjemplo);
                    }
                }
            }
        }
        return item;
    }


/*public static void main(String[] args) throws ClassNotFoundException {
    String categoria = "Principios básicos de diseño";
    obtenerItemsPorCategoria(categoria);
    try {
        ItemDAO itemDAO = new ItemDAO();
        List<Item> items = itemDAO.listarItems();
        for (Item item : items) {
            System.out.println(item.getPrompt());
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
}*/
    public void actualizarItem(Item item) {
        String url = "jdbc:mysql://localhost:3306/prueba";
        String username = "root";
        String password = "naye2003";
    try (Connection con = DriverManager.getConnection(url, username, password)) {
        PreparedStatement ps = con.prepareStatement("UPDATE items SET respuesta=?, fuente=?, ejemplo_relacionado=?, fuente_ejemplo=? WHERE prompt=?");
        ps.setString(1, item.getRespuesta());
        ps.setString(2, item.getFuente());
        ps.setString(3, item.getEjemploRelacionado());
        ps.setString(4, item.getFuenteEjemplo());
        ps.setString(5, item.getPrompt());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    /*public void agregarValoracion(Item item, Valoracion valoracion) {
    item.agregarValoracion(valoracion);
    actualizarItem(item);
}*/

    
    public static void main(String[] args) throws ClassNotFoundException {
        ItemDAO itemDAO = new ItemDAO();
        String promptBuscado = "¿Qué son los principios básicos de diseño?";
        Item itemEncontrado = itemDAO.buscarItemPorPrompt(promptBuscado);
        System.out.println("Prompt: " + itemEncontrado.getPrompt());
        System.out.println("Respuesta: " + itemEncontrado.getRespuesta());
        System.out.println("Fuente: " + itemEncontrado.getFuente());
        System.out.println("Ejemplo relacionado: " + itemEncontrado.getEjemploRelacionado());
        System.out.println("Fuente del ejemplo: " + itemEncontrado.getFuenteEjemplo());
    }}

