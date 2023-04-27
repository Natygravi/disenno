/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author nayel
 */


import static DAO.ItemDAO.obtenerItemsPorCategoria;
import Modelo.Categoria;
import Modelo.Item;
import Modelo.Valoracion;
import config.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CategoriaDAO {
    
    private Connection conexion;
    private Map<Integer, Categoria> categoriasMap = new HashMap<>();

    public CategoriaDAO() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Conexion con  = new Conexion();
        this.conexion = con.getConexion();
    }
    
    public static List<String> obtenerCategorias() {
        // Establecer la conexión con la base de datos MySQL
        List<String> categorias = new ArrayList<String>();

        try (Connection conexion  = DriverManager.getConnection("jdbc:mysql://servidoratitup.mysql.database.azure.com:3306/disennoDB?useSSL=TRUE", "adminNaty", "Coquito2022?")){    
            // Consultar la base de datos para obtener los nombres de las categorías
            try (PreparedStatement ps = conexion.prepareStatement("SELECT nombre FROM categoria");
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    categorias.add(nombre);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return categorias;
    }
    
    

    // Modificar el método obtenerCategoriasConItemsYValoraciones() como sigue
    public List<Categoria> obtenerCategoriasConItemsYValoraciones() throws SQLException {

        try {
            PreparedStatement consultaCategorias = conexion.prepareStatement("SELECT * FROM categoria");
            ResultSet resultadoCategorias = consultaCategorias.executeQuery();

            while (resultadoCategorias.next()) {
                int codigoCategoria = resultadoCategorias.getInt("codigo");
                String nombreCategoria = resultadoCategorias.getString("nombre");
                String descripcionCategoria = resultadoCategorias.getString("descripcion");

                Categoria categoria = new Categoria(nombreCategoria, descripcionCategoria);
                categoria.setCodigo(codigoCategoria);

                PreparedStatement consultaItems = conexion.prepareStatement("SELECT * FROM item WHERE categoria_codigo = ?");
                consultaItems.setInt(1, codigoCategoria);
                ResultSet resultadoItems = consultaItems.executeQuery();

                while (resultadoItems.next()) {
                    int codigoItem = resultadoItems.getInt("id");
                    String promptItem = resultadoItems.getString("prompt");
                    String respuestaItem = resultadoItems.getString("respuesta");
                    String fuenteItem = resultadoItems.getString("fuente");
                    String ejemploRelacionadoItem = resultadoItems.getString("ejemplo_relacionado");
                    String fuenteEjemploItem = resultadoItems.getString("fuente_ejemplo");

                    Item item = new Item(promptItem, respuestaItem, fuenteItem, ejemploRelacionadoItem, fuenteEjemploItem);
                    item.setCodigo(codigoItem);

                    PreparedStatement consultaValoraciones = conexion.prepareStatement("SELECT * FROM valoracion_respuesta WHERE item_id = ?");
                    consultaValoraciones.setInt(1, codigoItem);
                    ResultSet resultadoValoraciones = consultaValoraciones.executeQuery();

                    while (resultadoValoraciones.next()) {
                        int estrellasValoracion = resultadoValoraciones.getInt("estrellas");
                        String comentariosValoracion = resultadoValoraciones.getString("comentarios");

                        //Valoracion valoracion = new Valoracion(estrellasValoracion, comentariosValoracion);
                        item.agregarValoracionRes(estrellasValoracion, comentariosValoracion);
                    }

                    resultadoValoraciones.close();

                    PreparedStatement consultaValoracionesEjemplo = conexion.prepareStatement("SELECT * FROM valoracion_ejemplo WHERE item_id = ?");
                    consultaValoracionesEjemplo.setInt(1, codigoItem);
                    ResultSet resultadoValoracionesEjemplo = consultaValoracionesEjemplo.executeQuery();

                    while (resultadoValoracionesEjemplo.next()) {
                        int estrellasValoracionEjemplo = resultadoValoracionesEjemplo.getInt("estrellas");
                        String comentariosValoracionEjemplo = resultadoValoracionesEjemplo.getString("comentarios");

                        //Valoracion valoracionEjemplo = new Valoracion(estrellasValoracionEjemplo, comentariosValoracionEjemplo);
                        item.agregarValoracionEjm(estrellasValoracionEjemplo, comentariosValoracionEjemplo);
                    }

                    resultadoValoracionesEjemplo.close();

                    categoria.getItems().add(item);
                }

                resultadoItems.close();
                categoriasMap.put(codigoCategoria, categoria);
            }

            resultadoCategorias.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conexion.close();
        }

        return new ArrayList<>(categoriasMap.values());
    }
    
    public void guardarCategoriasConItemsYValoraciones(List<Categoria> categorias) throws SQLException {
        PreparedStatement insertCategoria = null;
        PreparedStatement insertItem = null;
        PreparedStatement insertValoracion = null;
        PreparedStatement insertValoracionEjemplo = null;
        try {
            conexion.setAutoCommit(false);

            // Insertar categorías
            insertCategoria = conexion.prepareStatement("INSERT INTO categoria (codigo, nombre, descripcion) VALUES (?, ?, ?)");
            for (Categoria categoria : categorias) {
                System.out.println(categoria.getCodigo());
                insertCategoria.setInt(1, categoria.getCodigo());
                insertCategoria.setString(2, categoria.getNombre());
                insertCategoria.setString(3, categoria.getDescripcion());
                insertCategoria.executeUpdate();

                // Insertar items
                insertItem = conexion.prepareStatement("INSERT INTO item (prompt, respuesta, fuente, ejemplo_relacionado, fuente_ejemplo, categoria_codigo) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                for (Item item : categoria.getItems()) {
                    insertItem.setString(1, item.getPrompt());
                    insertItem.setString(2, item.getRespuesta());
                    insertItem.setString(3, item.getFuente());
                    insertItem.setString(4, item.getEjemploRelacionado());
                    insertItem.setString(5, item.getFuenteEjemplo());
                    insertItem.setInt(6, categoria.getCodigo());
                    insertItem.executeUpdate();
                    ResultSet generatedKeys = insertItem.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int codigoItem = generatedKeys.getInt(1);

                        // Insertar valoraciones
                        insertValoracion = conexion.prepareStatement("INSERT INTO valoracion_respuesta (estrellas, comentarios, item_id) VALUES (?, ?, ?)");
                        for (Valoracion valoracion : item.getValoracionesRespuesta()) {
                            insertValoracion.setInt(1, valoracion.getEstrellas());
                            insertValoracion.setString(2, valoracion.getComentarios());
                            insertValoracion.setInt(3, codigoItem);
                            insertValoracion.executeUpdate();
                        }

                        // Insertar valoraciones de ejemplo
                        insertValoracionEjemplo = conexion.prepareStatement("INSERT INTO valoracion_ejemplo (estrellas, comentarios, item_id) VALUES (?, ?, ?)");
                        for (Valoracion valoracionEjemplo : item.getValoracionesEjemplo()) {
                            insertValoracionEjemplo.setInt(1, valoracionEjemplo.getEstrellas());
                            insertValoracionEjemplo.setString(2, valoracionEjemplo.getComentarios());
                            insertValoracionEjemplo.setInt(3, codigoItem);
                            insertValoracionEjemplo.executeUpdate();
                        }
                    }
                }
            }

            conexion.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            conexion.rollback();
        } finally {
            if (insertCategoria != null) {
                insertCategoria.close();
            }
            if (insertItem != null) {
                insertItem.close();
            }
            if (insertValoracion != null) {
                insertValoracion.close();
            }
            if (insertValoracionEjemplo != null) {
                insertValoracionEjemplo.close();
            }
            conexion.setAutoCommit(true);
        }
    }
      

 /*   
    public Categoria buscarCategoriaPorCodigo(int codigo) throws SQLException {
        String query = "SELECT * FROM categoria WHERE codigo = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    Categoria categoria = new Categoria(nombre, descripcion);
                    return categoria;
                } else {
                    return null;
                }
            }
        }
    }
    
    public void crearCategoria(Categoria categoria) throws SQLException {
        String query = "INSERT INTO categoria (nombre, descripcion) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int codigo = generatedKeys.getInt(1);

                }
            }
        }
    }
    
    public void actualizarCategoria(Categoria categoria) throws SQLException {
        String query = "UPDATE categoria SET nombre = ?, descripcion = ? WHERE codigo = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.setInt(3, categoria.getCodigo());
            ps.executeUpdate();
        }
    }
    
    public void eliminarCategoria(Categoria categoria) throws SQLException {
        String query = "DELETE FROM categoria WHERE codigo = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, categoria.getCodigo());
            ps.executeUpdate();
        }
    }
   */ 
    
   /* 
    public static void main(String[] args) throws ClassNotFoundException {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = CategoriaDAO.();
        for (Categoria categoria : categorias) {
            System.out.println(categoria.getNombre());
        }
}*/
      
      
      public static void main(String[] args) {
        List<String> categorias = obtenerCategorias();

        System.out.println("Categorías encontradas:");
        for (String categoria : categorias) {
 
            System.out.println(categoria);
        }
    }

}



