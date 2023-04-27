/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author saudd
 */
public class Ordenamiento {
    public static void ordenarAscendente(Comparable[] arr) {
        int n = arr.length;
        Comparable temp = null;
        for(int i = 0; i < n; i++) {
            for(int j = 1; j < (n - i); j++) {
                if(arr[j].menorQue(arr[j-1])) {
                    // intercambia elementos si están en el orden incorrecto
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    
    public static void ordenarDescendente(Comparable[] arr) {
        int n = arr.length;
        Comparable temp = null;
        for(int i = 0; i < n; i++) {
            for(int j = 1; j < (n - i); j++) {
                if(arr[j-1].menorQue(arr[j])) {
                    // intercambia elementos si están en el orden incorrecto
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    
    public static void ordenarAscendenteAux(Comparable[] arr) {
        int n = arr.length;
        Comparable temp = null;
        boolean flag = true;
        while(flag){
            flag = false;
            for(int i = 1; i < n; i++) {
                if(arr[i-1].menorQue(arr[i])) {
                    // intercambia elementos si están en el orden incorrecto
                    temp = arr[i-1];
                    arr[i-1] = arr[i];
                    arr[i] = temp;
                    flag = true;
                }
            }
            n--;
        }
    }
    
    public static void ordenarDescendenteAux(Comparable[] arr) {
        int n = arr.length;
        boolean flag = true;
        Comparable temp;
        int j = 0;

        while (flag) {
            flag = false;
            for (int i = 0; i < n - 1 - j; i++) {
                if (arr[i].menorQue(arr[i + 1])) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    flag = true;
                }
            }
            j++;
        }
    }   
    
}
