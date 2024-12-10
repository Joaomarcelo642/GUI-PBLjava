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

        String numero = jsonObject.has("numero") && !jsonObject.get("numero").isJsonNull()
                ? jsonObject.get("numero").getAsString()
                : "";

        String nome = jsonObject.has("nome") && !jsonObject.get("nome").isJsonNull()
                ? jsonObject.get("nome").getAsString()
                : "";

        return new Cartao(numero, nome);
    }
}



