/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

/**
 *
 * @author saudd
 */

import Modelo.Item;
import DAO.ItemDAO;
import Modelo.Valoracion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Traductor {

    private static String key = "83b288d1ef834a3aaccb41fbb93193a6";
    private static String location = "eastus";

    OkHttpClient client = new OkHttpClient();
    
    
    private String Post(String text, String fromLanguage, String toLanguage) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "[{\"Text\": \"" + text + "\"}]");
        Request request = new Request.Builder()
                .url("https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=" + fromLanguage + "&to=" + toLanguage)
                .post(body)
                .addHeader("Ocp-Apim-Subscription-Key", key)
                .addHeader("Ocp-Apim-Subscription-Region", location)
                .addHeader("Content-type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }
    
    public String Traducir(String text, String fromLanguage, String toLanguage) throws IOException { 
         String translatedText = "";
        try {
            Traductor translateRequest = new Traductor();
            String response = translateRequest.Post(text, fromLanguage, toLanguage);
            JsonParser parser = new JsonParser();
            JsonElement json = parser.parse(response);
             translatedText = json.getAsJsonArray().get(0).getAsJsonObject().get("translations")
                .getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString();
            System.out.println(translatedText);
        } catch (Exception e) {
            System.out.println(e);
        }return translatedText;
}
    
    
    //Idiomas (es: español - en: inglés - fr: francés - de: alemán)
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            Traductor translateRequest = new Traductor();
            String textToTranslate = "Hola, cómo estás?";
            String fromLanguage = "es";
            String toLanguage = "en";
            String response = translateRequest.Post(textToTranslate, fromLanguage, toLanguage);
            JsonParser parser = new JsonParser();
            JsonElement json = parser.parse(response);
            String translatedText = json.getAsJsonArray().get(0).getAsJsonObject().get("translations")
                .getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString();
            System.out.println(translatedText);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        Connection conexion;
        Conexion con  = new Conexion();
        conexion = con.getConexion();
        System.out.println(conexion);
        Item item = new Item("¿Cuál es la capital de Francia?", "París", "Wikipedia", "París es la capital de Francia", "Wikipedia");
       // System.out.println(ConexionGPT.conexion("Qué dia de la semana va despues de lunes?"));
       ItemDAO itemDao = new ItemDAO();
       itemDao.agregarItem(item);
        
    }
    
 
}
