package model;

import com.google.gson.*;
import java.lang.reflect.Type;

public class FeedbackAdapter implements JsonSerializer<Feedback>, JsonDeserializer<Feedback> {

    @Override
    public JsonElement serialize(Feedback feedback, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        // Serializa os campos da classe Feedback
        jsonObject.add("usuario", context.serialize(feedback.getUsuario()));
        jsonObject.addProperty("avaliacao", feedback.getAvaliacao());
        jsonObject.addProperty("comentario", feedback.getComentario());

        return jsonObject;
    }

    @Override
    public Feedback deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Desserializa os campos da classe Feedback
        Usuario usuario = context.deserialize(jsonObject.get("usuario"), Usuario.class);
        String avaliacao = jsonObject.get("avaliacao").getAsString();
        String comentario = jsonObject.get("comentario").getAsString();

        return new Feedback(usuario, avaliacao, comentario);
    }
}
