/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.edit.EditChoice;
import com.theokanning.openai.edit.EditRequest;
import java.util.List;

/**
 *
 * @author saudd
 */
public class ConexionGPT {
    public static String conexion(String pregunta) {
        OpenAiService service = new OpenAiService("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");
        
        /*EditRequest request = EditRequest.builder()
                .model("text-davinci-edit-001")
                .input(pregunta)
                .instruction("Responder pregunta y explicar respuesta")
                .build();
        String respuesta = "";
        service.createEdit(request).getChoices();
        List<EditChoice> choices = service.createEdit(request).getChoices();
        for (EditChoice choice : choices) {
            respuesta += choice.toString() + "\n.";
        }
        return respuesta;*/
        EditRequest solicitud = EditRequest.builder()
                .model("text-davinci-edit-001")
                .input(pregunta)
                .instruction("Responder la pregunta con una respuesta coherente y completa.")
                .build();

        List<EditChoice> opciones = service.createEdit(solicitud).getChoices();

        String respuesta = "";

        if (!opciones.isEmpty()) {
            //respuesta = opciones.get(0).getText();
            respuesta = opciones.get(0).getText().replace(pregunta, "").trim();
        }

        return respuesta;
    }
}