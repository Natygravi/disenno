/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import DAO.CategoriaDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import DAO.CategoriaDAO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import config.Traductor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileTypeMap;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpSession;

/**
 *
 * @author saudd
 */
public class Catalogo {
    private static Catalogo instancia = null;
    private List<Categoria> categorias;
    private CategoriaDAO categoriaDAO;
    private static List<Categoria> categoriasTraducidas;

    private Catalogo() throws ClassNotFoundException, SQLException {
        categorias = new ArrayList<>();
        categoriaDAO = new CategoriaDAO();
        //categorias = categoriaDAO.obtenerCategoriasConItemsYValoraciones();
    }

    public static Catalogo obtenerInstancia() throws ClassNotFoundException, SQLException {
        if (instancia == null) {
            instancia = new Catalogo();
        }
        return instancia;
    }

    public List<Categoria> obtenerCategorias() {
        return categorias;
    }
    
    public int getCantidadCategorias(){
        return categorias.size();
    }
    
    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public void eliminarCategoria(Categoria categoria) {
        categorias.remove(categoria);
    }
    
    public void guardarCategorias() throws SQLException{
        categoriaDAO.guardarCategoriasConItemsYValoraciones(categorias);
    }
    
    public void insertarValoracionEjem(String promptSeleccionado, int estrellas, String comentarios) {

        // Aquí puedes acceder a la variable "categorias" para buscar el Item correspondiente al prompt seleccionado y agregarle la valoración
        // Paso 1: Buscar el Item correspondiente al prompt seleccionado
        Item item = null;
        for (Categoria categoria : categorias) {
            for (Item i : categoria.getItems()) {
                if (i.getPrompt().equals(promptSeleccionado)) {
                    item = i;
                    break;
                }
            }
            if (item != null) {
              break;
            }
        }


        // Paso 2: Insertar la valoración del usuario en la tabla Valoracion del Item encontrado
        if (item != null) {
            // Paso 2: Insertar la valoración del usuario en la tabla Valoracion del Item encontrado
            item.agregarValoracionEjm(estrellas,comentarios);
        } else {
            System.out.println("No se encontró ningún Item correspondiente al prompt seleccionado.");
        }
    }
    
    public void insertarValoracionRes(String promptSeleccionado, int estrellas, String comentarios) {

        // Aquí puedes acceder a la variable "categorias" para buscar el Item correspondiente al prompt seleccionado y agregarle la valoración
        // Paso 1: Buscar el Item correspondiente al prompt seleccionado
        Item item = null;
        for (Categoria categoria : categorias) {
            for (Item i : categoria.getItems()) {
                if (i.getPrompt().equals(promptSeleccionado)) {
                    item = i;
                    break;
                }
            }
            if (item != null) {
              break;
            }
        }


        // Paso 2: Insertar la valoración del usuario en la tabla Valoracion del Item encontrado
        if (item != null) {
            // Paso 2: Insertar la valoración del usuario en la tabla Valoracion del Item encontrado
            item.agregarValoracionRes(estrellas,comentarios);
        } else {
            System.out.println("No se encontró ningún Item correspondiente al prompt seleccionado.");
        }
    }
    
    public void generarPDFItemParticular(String promptSeleccionado,List<Categoria> categorias, ByteArrayOutputStream outputStream) throws IOException, DocumentException {
        // Crear el documento
        promptSeleccionado ="Qué son los olores de software?"; 
        Document documento = new Document();
        // Crear el archivo PDF en el que se escribirá el documento
        PdfWriter.getInstance(documento, outputStream);
        // Abrir el documento para escribir
        documento.open();
        
        // Recorrer la lista de categorías y agregar cada una al documento
        for (Categoria categoria : categorias) {
            for (Item item : categoria.getItems()) {              
                if (item.getPrompt().equals(promptSeleccionado)) {
                    
                Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.DARK_GRAY);    
                Paragraph prompt = new Paragraph("                                             INFORMACIÓN DEL ITEM SELECCIONADO: \n\n\n" + item.toString(),font);
                    prompt.setAlignment(Element.ALIGN_MIDDLE);
                    prompt.setIndentationLeft(20);
                    prompt.setIndentationRight(20);
                    prompt.setSpacingAfter(20);
                documento.add(prompt);          
            }
        }
        documento.close(); 
    }} 
    
    
    
    
    public  void generarPDFCategoriaConceptual(String categoriaSeleccionada,List<Categoria> categorias, ByteArrayOutputStream outputStream) throws IOException, DocumentException {
        // Crear el documento
        categoriaSeleccionada ="Refactoring"; 
        Document documento = new Document();
        // Crear el archivo PDF en el que se escribirá el documento
        PdfWriter.getInstance(documento, outputStream);
        // Abrir el documento para escribir
        documento.open();
        Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.DARK_GRAY); 
        // Recorrer la lista de categorías y agregar cada una al documento
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equals(categoriaSeleccionada)){
                Paragraph categoriaConceptual = new Paragraph("                                                INFORMACIÓN DE LA CATEGORIA CONCEPTUAL: \n\n\n" + categoria.toString(),font);
                documento.add(categoriaConceptual); }
        }
        documento.close(); 
    }
    
    
    
    
    public  void generarPDFAtitup(List<Categoria> categorias, ByteArrayOutputStream outputStream) throws IOException, DocumentException {
        // Crear el documento
         
        Document documento = new Document();
        // Crear el archivo PDF en el que se escribirá el documento
        PdfWriter.getInstance(documento, outputStream);
        // Abrir el documento para escribir
        documento.open();
        Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.DARK_GRAY); 
        Paragraph atitup = new Paragraph("                                                  TODA LA INFORMACIÓN DE ATITUP: \n\n\n"+categorias,font);
        documento.add(atitup); 
        
        documento.close(); 
        }
    
     public static void enviarCorreo(String destinatario, String asunto, String cuerpo, byte[] archivoAdjunto, String nombreArchivo) throws Exception {
        final String remitente = "nayelicn11@gmail.com";
        final String clave = "vavtnoezzczpejps";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            
            
            
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(remitente));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        message.setSubject(asunto);

        // Agregar archivo generado en memoria como adjunto al mensaje de correo electrónico
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new ByteArrayDataSource(archivoAdjunto, FileTypeMap.getDefaultFileTypeMap().getContentType(nombreArchivo));
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(nombreArchivo);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(attachmentBodyPart);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(cuerpo, "text/plain; charset=utf-8");

        multipart.addBodyPart(bodyPart);
        message.setContent(multipart);

        Transport.send(message);
    }
    
    public void traducirCategoria(String toLanguage) throws IOException{
        Traductor traductor = new Traductor();

       for(Categoria categoria: categorias){
           Categoria categoriaAux = categoria;
           categoriaAux.setNombre(traductor.Traducir(categoriaAux.getNombre(), "es",toLanguage ));
           categoriaAux.setDescripcion(traductor.Traducir(categoriaAux.getDescripcion(), "es",toLanguage ));
           for(Item i : categoria.getItems()){
               Item itemAux = i;
               i.setPrompt(traductor.Traducir(itemAux.getPrompt(), "es", toLanguage));
               i.setRespuesta(traductor.Traducir(itemAux.getRespuesta(), "es", toLanguage));
               i.setFuente(traductor.Traducir(itemAux.getFuente(), "es", toLanguage));
               i.setEjemploRelacionado(traductor.Traducir(itemAux.getEjemploRelacionado(), "es", toLanguage));
               i.setFuenteEjemplo(traductor.Traducir(itemAux.getFuenteEjemplo(), "es", toLanguage));
               for (Valoracion v : itemAux.getValoracionesEjm()) {
                   Valoracion valoracionAuxEjm = v;
                   v.setComentarios(traductor.Traducir(valoracionAuxEjm.getComentarios(), "es", toLanguage));
                   itemAux.agregarValoracionEjm(valoracionAuxEjm);
               }
               for (Valoracion v : itemAux.getValoracionesRes()) {
                   Valoracion valoracionAuxRes = v;
                   v.setComentarios(traductor.Traducir(valoracionAuxRes.getComentarios(), "es", toLanguage));
                   itemAux.agregarValoracionEjm(valoracionAuxRes);

                }

           }
           categoriasTraducidas.add(categoriaAux);
       }   
    }
    
    public List<Map<String, Object>> filtrarItems(Categoria categoria, boolean considerarRespuestas, boolean considerarEjemplos, boolean considerarAdmin, boolean considerarChatGPT,boolean considerarAdminEjm, boolean considerarChatGPTEjm) {

        ArrayList<Item> itemsFiltrados = new ArrayList<>();
        ArrayList<Valoracion> valoracionesItem = new ArrayList<>();
        List<Map<String, Object>> lista = new ArrayList<>();
        
        //Agregar algo que limpie el array
        
        System.out.println(considerarRespuestas);
        System.out.println(considerarEjemplos);
        System.out.println(considerarAdmin);
        System.out.println(considerarChatGPT);
        System.out.println(considerarAdminEjm);
        System.out.println(considerarChatGPTEjm);

        for (Item item : categoria.getItems()) {
 
            // Verificar si se deben considerar las valoraciones de respuestas
            if (considerarRespuestas) {
                for (Valoracion valoracion : item.getValoracionesRespuesta()) {
                    //if (valoracion.getEstrellas() >= 4) {
                        if (considerarAdmin && item.getFuente().equals("admin")) {
                            valoracionesItem.add(valoracion);
                        } else if (considerarChatGPT && item.getFuente().equals("chatGPT")) {
                            valoracionesItem.add(valoracion);
                        } else if (!considerarAdmin && !considerarChatGPT) {
                            valoracionesItem.add(valoracion);
                        }
                    }
                //}
            }

            // Verificar si se deben considerar las valoraciones de ejemplos
            if (considerarEjemplos) {
                for (Valoracion valoracion : item.getValoracionesEjemplo()) {
                    //if (valoracion.getEstrellas() >= 4) {
                        if (considerarAdmin && item.getFuenteEjemplo().equals("admin")) {
                            valoracionesItem.add(valoracion);
                        } else if (considerarChatGPTEjm && item.getFuenteEjemplo().equals("chatGPT")) {
                            valoracionesItem.add(valoracion);
                        } else if (!considerarAdminEjm && !considerarChatGPT) {
                           valoracionesItem.add(valoracion);
                        }
                    //}
                }
            }
            /*System.out.println("Holaaa");
            //System.out.println(valoracionesItem);
            for (Valoracion valoracion: valoracionesItem){
                System.out.println(valoracion.getEstrellas());
            }
            System.out.println(item.calcularPromedio(valoracionesItem))*/
            if(item.calcularPromedio(valoracionesItem)>=4){
                
                itemsFiltrados.add(item);
                Map<String, Object> itemTop = new HashMap<>();
                itemTop.put("prompt", item.getPrompt());
                itemTop.put("valoracion",item.calcularPromedio(valoracionesItem));
                lista.add(itemTop);
                
            }

            // Agregar el item al array si cumple con las condiciones
            /*if (cumpleCondiciones) {
                itemsFiltrados.add(item);
            }*/

            // Detener la búsqueda si se han encontrado suficientes ítems
            /*if (itemsFiltrados.size() == cantidadItems) {
                break;
            }*/
            valoracionesItem.clear();
        }

        //return itemsFiltrados;
        return lista;
    }
    
    public List<Map<String, Object>> realizarTop(Categoria categoria, int cantidadItems, boolean considerarRespuestas, boolean considerarEjemplos, boolean considerarAdmin, boolean considerarChatGPT,boolean considerarAdminEjm, boolean considerarChatGPTEjm){
        ArrayList<Item> itemsFiltrados = new ArrayList<>();
        List<Map<String, Object>> listaTop = new ArrayList<>();
        List<Map<String, Object>> listaTopFinal = new ArrayList<>();
        
        listaTop = filtrarItems(categoria, considerarRespuestas, considerarEjemplos, considerarAdmin, considerarChatGPT,considerarAdminEjm, considerarChatGPTEjm);
        //Item[] itemArray = itemsFiltrados.toArray(new Item[itemsFiltrados.size()]);
        //Ordenamiento.ordenarAscendente(itemArray);
        /*itemsFiltrados.clear(); // borra todos los elementos del ArrayList
        itemsFiltrados.addAll(Arrays.asList(itemArray)); 
        for (Item item : itemsFiltrados){
            itemsTop.add(item);
            if (itemsFiltrados.size() == cantidadItems) {
                break;
            }
        }*/
        Collections.sort(listaTop, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Double item1 = (Double) o1.get("valoracion");
                Double item2 = (Double) o2.get("valoracion");
                return Double.compare(item2, item1);
            }
        });
        for (Map<String, Object> map : listaTop){
            listaTopFinal.add(map);
            if (listaTopFinal.size() == cantidadItems) {
                break;
            }
        }
        
        return listaTopFinal;
    }
    
    public List<Map<String, Object>> realizarTopGeneral( int cantidadItems, boolean considerarRespuestas, boolean considerarEjemplos, boolean considerarAdmin, boolean considerarChatGPT,boolean considerarAdminEjm, boolean considerarChatGPTEjm){
        ArrayList<Item> itemsFiltrados = new ArrayList<>();
        List<Map<String, Object>> listaTop = new ArrayList<>();
        List<Map<String, Object>> listaAux = new ArrayList<>();
        List<Map<String, Object>> listaTopFinal = new ArrayList<>();
        for (Categoria categoria: categorias){
            listaTop.addAll(filtrarItems(categoria, considerarRespuestas, considerarEjemplos, considerarAdmin, considerarChatGPT,considerarAdminEjm, considerarChatGPTEjm)); 
        }
        
        //Item[] itemArray = itemsFiltrados.toArray(new Item[itemsFiltrados.size()]);
        //Ordenamiento.ordenarAscendente(itemArray);
        /*itemsFiltrados.clear(); // borra todos los elementos del ArrayList
        itemsFiltrados.addAll(Arrays.asList(itemArray)); 
        for (Item item : itemsFiltrados){
            itemsTop.add(item);
            if (itemsFiltrados.size() == cantidadItems) {
                break;
            }
        }*/
        Collections.sort(listaTop, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Double item1 = (Double) o1.get("valoracion");
                Double item2 = (Double) o2.get("valoracion");
                return Double.compare(item2, item1);
            }
        });
        for (Map<String, Object> map : listaTop){
            listaTopFinal.add(map);
            if (listaTopFinal.size() == cantidadItems) {
                break;
            }
        }
        
        return listaTopFinal;
    }
    public Categoria obtenerCategoria(String nombreCategoria){
        for (Categoria categoria: categorias){
            if (categoria.getNombre().equals(nombreCategoria)){
                return categoria;
            }
        }
        return null;
    }
    
    
    
    
}
