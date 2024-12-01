package model;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventoAdapter implements JsonDeserializer<Evento>, JsonSerializer<Evento> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public JsonElement serialize(Evento src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("nome", src.getNome());
        jsonObject.addProperty("descricao", src.getDescricao());
        jsonObject.addProperty("data", dateFormat.format(src.getData()));
        jsonObject.add("assentosDisponiveis", context.serialize(src.getAssentosDisponiveis()));
        jsonObject.add("feedbacks", context.serialize(src.getFeedbacks()));
        return jsonObject;
    }

    @Override
    public Evento deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        try {
            String nome = jsonObject.get("nome").getAsString();
            String descricao = jsonObject.get("descricao").getAsString();
            Date data = dateFormat.parse(jsonObject.get("data").getAsString());
            List<String> assentosDisponiveis = context.deserialize(jsonObject.get("assentosDisponiveis"), List.class);
            List<Feedback> feedbacks = context.deserialize(jsonObject.get("feedbacks"), List.class);

            Evento evento = new Evento(nome, descricao, data);
            evento.setAssentosDisponiveis(assentosDisponiveis);
            evento.setFeedbacks(feedbacks);
            return evento;
        } catch (ParseException e) {
            throw new JsonParseException("Erro ao desserializar o campo 'data'", e);
        }
    }
}

