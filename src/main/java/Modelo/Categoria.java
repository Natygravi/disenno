/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author saudd
 */
public class Categoria {
    private static int contadorCategorias = 0;
    private String nombre;
    private int codigo;
    private String descripcion;
    private ArrayList<String> cursos; //TI234 BASES
    private ArrayList<Item> items;
    
    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        //asignarCodigo();
        //this.codigo = ++contadorCategorias;
        this.cursos = new ArrayList<>();
        this.items = new ArrayList<>();
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }
    
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<String> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<String> cursos) {
        this.cursos = cursos;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    
    public void agregarItem(Item item){
        items.add(item);
    }
    
    public void agregarItem(String pregunta, String respuesta, String fuente, String ejemplo, String fuenteEjemplo){
        
        //Creo que esta malll lo del codigo;
        Item item = new Item(pregunta,respuesta, fuente, ejemplo,fuenteEjemplo);
        int cantidadItems = items.size();
        item.setCodigo(cantidadItems);
        items.add(item);
        
    }
    
    
    public void agregarCurso(String curso){
        cursos.add(curso);
    }
    
    private void asignarCodigo() throws ClassNotFoundException, SQLException{
        Catalogo main;
        main = Catalogo.obtenerInstancia();
        this.codigo = main.getCantidadCategorias();
    }

@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Categoría: ").append(nombre).append("\n");
        sb.append("Código: ").append(codigo).append("\n");
        sb.append("Descripción: ").append(descripcion).append("\n");
        sb.append("Cursos: ").append(cursos).append("\n");
        sb.append("Items: ").append(items).append("\n");
        return sb.toString();
    }
}
