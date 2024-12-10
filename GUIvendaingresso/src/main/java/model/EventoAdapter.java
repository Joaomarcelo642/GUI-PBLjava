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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Adapter para personalizar a deserialização de Evento com Gson.
 */
public class EventoAdapter implements JsonDeserializer<Evento>, JsonSerializer<Evento> {

    @Override
    public Evento deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String nome = jsonObject.get("nome").getAsString();
        String descricao = jsonObject.get("descricao").getAsString();
        Date data = context.deserialize(jsonObject.get("data"), Date.class);

        List<Feedback> feedbacks = new ArrayList<>();
        if (jsonObject.has("feedbacks") && jsonObject.get("feedbacks").isJsonArray()) {
            for (JsonElement feedbackElement : jsonObject.get("feedbacks").getAsJsonArray()) {
                Feedback feedback = context.deserialize(feedbackElement, Feedback.class);
                feedbacks.add(feedback);
            }
        }

        List<String> assentosDisponiveis = new ArrayList<>();
        if (jsonObject.has("assentosDisponiveis") && jsonObject.get("assentosDisponiveis").isJsonArray()) {
            for (JsonElement assentoElement : jsonObject.get("assentosDisponiveis").getAsJsonArray()) {
                assentosDisponiveis.add(assentoElement.getAsString());
            }
        }

        Evento evento = new Evento(nome, descricao, data);
        evento.setFeedbacks(feedbacks);
        evento.setAssentosDisponiveis(assentosDisponiveis);

        return evento;
    }

    @Override
    public JsonElement serialize(Evento evento, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("nome", evento.getNome());
        jsonObject.addProperty("descricao", evento.getDescricao());
        jsonObject.add("data", context.serialize(evento.getData()));

        JsonArray feedbackArray = new JsonArray();
        for (Feedback feedback : evento.getFeedbacks()) {
            feedbackArray.add(context.serialize(feedback));
        }
        jsonObject.add("feedbacks", feedbackArray);

        JsonArray assentosArray = new JsonArray();
        for (String assento : evento.getAssentosDisponiveis()) {
            assentosArray.add(new JsonPrimitive(assento));
        }
        jsonObject.add("assentosDisponiveis", assentosArray);

        return jsonObject;
    }
}
