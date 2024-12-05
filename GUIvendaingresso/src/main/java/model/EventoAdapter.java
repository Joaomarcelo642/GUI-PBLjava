package model;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventoAdapter implements JsonDeserializer<Evento>, JsonSerializer<Evento> {

    @Override
    public Evento deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Desserializa os campos de Evento
        String nome = jsonObject.get("nome").getAsString();
        String descricao = jsonObject.get("descricao").getAsString();
        Date data = context.deserialize(jsonObject.get("data"), Date.class); // Desserializa a data

        // Desserializa a lista de Feedback
        List<Feedback> feedbacks = new ArrayList<>();
        if (jsonObject.has("feedbacks") && jsonObject.get("feedbacks").isJsonArray()) {
            for (JsonElement feedbackElement : jsonObject.get("feedbacks").getAsJsonArray()) {
                Feedback feedback = context.deserialize(feedbackElement, Feedback.class);
                feedbacks.add(feedback);
            }
        }

        // Desserializa a lista de assentos
        List<String> assentosDisponiveis = new ArrayList<>();
        if (jsonObject.has("assentosDisponiveis") && jsonObject.get("assentosDisponiveis").isJsonArray()) {
            for (JsonElement assentoElement : jsonObject.get("assentosDisponiveis").getAsJsonArray()) {
                assentosDisponiveis.add(assentoElement.getAsString());
            }
        }

        // Cria o objeto Evento passando os três parâmetros necessários
        Evento evento = new Evento(nome, descricao, data);
        evento.setFeedbacks(feedbacks);  // Define a lista de feedbacks
        evento.setAssentosDisponiveis(assentosDisponiveis);  // Define a lista de assentos

        return evento;
    }

    @Override
    public JsonElement serialize(Evento evento, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        // Serializa os campos de Evento
        jsonObject.addProperty("nome", evento.getNome());
        jsonObject.addProperty("descricao", evento.getDescricao());
        jsonObject.add("data", context.serialize(evento.getData()));  // Serializa a data

        // Serializa a lista de Feedback
        JsonArray feedbackArray = new JsonArray();
        for (Feedback feedback : evento.getFeedbacks()) {
            feedbackArray.add(context.serialize(feedback));
        }
        jsonObject.add("feedbacks", feedbackArray);

        // Serializa a lista de assentos
        JsonArray assentosArray = new JsonArray();
        for (String assento : evento.getAssentosDisponiveis()) {
            assentosArray.add(new JsonPrimitive(assento));
        }
        jsonObject.add("assentosDisponiveis", assentosArray);

        return jsonObject;
    }
}
