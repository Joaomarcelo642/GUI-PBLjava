package model;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Adapter para personalizar a deserialização de Cartao com Gson.
 */
public class CartaoAdapter implements JsonDeserializer<Cartao>, JsonSerializer<Cartao> {
    @Override
    public JsonElement serialize(Cartao src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty("numero", src.getNumero());
        json.addProperty("nome", src.getNome());
        return json;
    }

    @Override
    public Cartao deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Verifica se "numero" e "nome" estão presentes e não são null
        String numero = jsonObject.has("numero") && !jsonObject.get("numero").isJsonNull()
                ? jsonObject.get("numero").getAsString()
                : ""; // Valor padrão (pode ser alterado conforme sua lógica)

        String nome = jsonObject.has("nome") && !jsonObject.get("nome").isJsonNull()
                ? jsonObject.get("nome").getAsString()
                : ""; // Valor padrão

        // Cria o objeto Cartao
        return new Cartao(numero, nome);
    }
}



