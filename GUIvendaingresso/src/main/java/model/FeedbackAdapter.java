/*******************************************************************************
 Sistema Operacional: Windows 10 - 64 Bits
 Linguagem: JAVA 21.0.4
 Autor: João Marcelo Nascimento Fernandes
 Componente Curricular: EXA 863 - MI Programção
 Concluido em: 09/12/2024
 Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 ******************************************************************************************/

package model;

import com.google.gson.*;
import java.lang.reflect.Type;

/**
 * Adapter para personalizar a deserialização de Feedback com Gson.
 */
public class FeedbackAdapter implements JsonSerializer<Feedback>, JsonDeserializer<Feedback> {

    @Override
    public JsonElement serialize(Feedback feedback, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.add("usuario", context.serialize(feedback.getUsuario()));
        jsonObject.addProperty("avaliacao", feedback.getAvaliacao());
        jsonObject.addProperty("comentario", feedback.getComentario());

        return jsonObject;
    }

    @Override
    public Feedback deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Usuario usuario = context.deserialize(jsonObject.get("usuario"), Usuario.class);
        String avaliacao = jsonObject.get("avaliacao").getAsString();
        String comentario = jsonObject.get("comentario").getAsString();

        return new Feedback(usuario, avaliacao, comentario);
    }
}
