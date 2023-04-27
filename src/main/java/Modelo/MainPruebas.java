/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author saudd
 */
public class MainPruebas {
    static ArrayList<Item> items = new ArrayList<>();
    static ArrayList<Valoracion> valoraciones = new ArrayList<>();
    
    public static void main(String[] args) {
        //ITEMS
        items.add(new Item("¿BCuál es la capital de Francia?", "París", "Wikipedia", "París es la capital de Francia", "Wikipedia"));
        items.add(new Item("¿CCuál es la capital de Alemania?", "Berlín", "Wikipedia", "Berlín es la capital de Alemania", "Wikipedia"));
        items.add(new Item("¿AEn qué año se descubrió América?", "1492", "Wikipedia", "Cristóbal Colón descubrió América en 1492", "Wikipedia"));
        
        //VALORACIONES
        valoraciones.add(new Valoracion(4,"comentarios1"));
        valoraciones.add(new Valoracion(3,"comentarios2"));
        valoraciones.add(new Valoracion(5,"comentarios3"));
        
        //Prompt[] prompts = new Prompt[0];
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        
        while (opcion != 5) {
            System.out.println("Menú:");
            System.out.println("1.Listar Items");
            System.out.println("2.Listar Valoraciones");
            System.out.println("3.Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    listarItems();
                    break;
                case 2:
                    listarValoraciones();
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        scanner.close();
    } 
    public static void listarItems()
    {
       Item[] itemArray = items.toArray(new Item[items.size()]);
       Ordenamiento.ordenarAscendente(itemArray);
       items.clear(); // borra todos los elementos del ArrayList
       items.addAll(Arrays.asList(itemArray)); // agrega cada objeto Prompt del arreglo al ArrayList
       for (Item persona : items) {
            System.out.println(persona.toString());
       }
    }
    
    
    public static void listarValoraciones()
    {
       Valoracion[] valoracionArray = valoraciones.toArray(new Valoracion[valoraciones.size()]);
       Ordenamiento.ordenarDescendente(valoracionArray);
       valoraciones.clear(); // borra todos los elementos del ArrayList
       valoraciones.addAll(Arrays.asList(valoracionArray)); // agrega cada objeto Prompt del arreglo al ArrayList
       for (Valoracion persona : valoraciones) {
            System.out.println(persona.toString());
       }
    }
}
