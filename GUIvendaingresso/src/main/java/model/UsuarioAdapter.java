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
import java.util.List;

/**
 * Adapter para personalizar a deserialização de Usuario com Gson.
 */
public class UsuarioAdapter implements JsonDeserializer<Usuario>, JsonSerializer<Usuario> {

    @Override
    public Usuario deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String login = jsonObject.get("login").getAsString();
        String senha = jsonObject.get("senha").getAsString();
        String nome = jsonObject.get("nome").getAsString();
        String cpf = jsonObject.get("cpf").getAsString();
        String email = jsonObject.get("email").getAsString();
        Boolean admin = jsonObject.has("admin") && !jsonObject.get("admin").isJsonNull()
                ? jsonObject.get("admin").getAsBoolean()
                : false;

        Usuario usuario = new Usuario(login, senha, nome, cpf, email, admin);

        if (jsonObject.has("ingressos") && jsonObject.get("ingressos").isJsonArray()) {
            List<Ingresso> ingressos = new ArrayList<>();
            for (JsonElement ingressoElement : jsonObject.get("ingressos").getAsJsonArray()) {
                Ingresso ingresso = context.deserialize(ingressoElement, Ingresso.class);
                ingressos.add(ingresso);
            }
            usuario.setIngressos(ingressos);
        }

        if (jsonObject.has("cartoes") && jsonObject.get("cartoes").isJsonArray()) {
            List<Cartao> cartoes = new ArrayList<>();
            for (JsonElement cartaoElement : jsonObject.get("cartoes").getAsJsonArray()) {
                Cartao cartao = context.deserialize(cartaoElement, Cartao.class);
                cartoes.add(cartao);
            }
            usuario.setCartoes(cartoes);
        }

        if (jsonObject.has("boletos") && jsonObject.get("boletos").isJsonArray()) {
            List<Boleto> boletos = new ArrayList<>();
            for (JsonElement boletoElement : jsonObject.get("boletos").getAsJsonArray()) {
                Boleto boleto = context.deserialize(boletoElement, Boleto.class);
                boletos.add(boleto);
            }
            usuario.setBoletos(boletos);
        }

        return usuario;
    }

    @Override
    public JsonElement serialize(Usuario usuario, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("login", usuario.getLogin());
        jsonObject.addProperty("senha", usuario.getSenha());
        jsonObject.addProperty("nome", usuario.getNome());
        jsonObject.addProperty("cpf", usuario.getCpf());
        jsonObject.addProperty("email", usuario.getEmail());
        jsonObject.addProperty("admin", usuario.getAdmin());

        JsonArray ingressosArray = new JsonArray();
        for (Ingresso ingresso : usuario.getIngressos()) {
            ingressosArray.add(context.serialize(ingresso));
        }
        jsonObject.add("ingressos", ingressosArray);

        JsonArray cartoesArray = new JsonArray();
        for (Cartao cartao : usuario.getCartoes()) {
            cartoesArray.add(context.serialize(cartao));
        }
        jsonObject.add("cartoes", cartoesArray);

        JsonArray boletosArray = new JsonArray();
        for (Boleto boleto : usuario.getBoletos()) {
            boletosArray.add(context.serialize(boleto));
        }
        jsonObject.add("boletos", boletosArray);

        return jsonObject;
    }
}

