package model;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UsuarioAdapter implements JsonDeserializer<Usuario>, JsonSerializer<Usuario> {

    @Override
    public Usuario deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Desserializa os campos básicos de Usuario
        String login = jsonObject.get("login").getAsString();
        String senha = jsonObject.get("senha").getAsString();
        String nome = jsonObject.get("nome").getAsString();
        String cpf = jsonObject.get("cpf").getAsString();
        String email = jsonObject.get("email").getAsString();
        Boolean admin = jsonObject.has("admin") && !jsonObject.get("admin").isJsonNull()
                ? jsonObject.get("admin").getAsBoolean()
                : false;

        // Cria o objeto Usuario
        Usuario usuario = new Usuario(login, senha, nome, cpf, email, admin);

        // Desserializa a lista de ingressos
        if (jsonObject.has("ingressos") && jsonObject.get("ingressos").isJsonArray()) {
            List<Ingresso> ingressos = new ArrayList<>();
            for (JsonElement ingressoElement : jsonObject.get("ingressos").getAsJsonArray()) {
                Ingresso ingresso = context.deserialize(ingressoElement, Ingresso.class);
                ingressos.add(ingresso);
            }
            usuario.setIngressos(ingressos);
        }

        // Desserializa a lista de cartões
        if (jsonObject.has("cartoes") && jsonObject.get("cartoes").isJsonArray()) {
            List<Cartao> cartoes = new ArrayList<>();
            for (JsonElement cartaoElement : jsonObject.get("cartoes").getAsJsonArray()) {
                Cartao cartao = context.deserialize(cartaoElement, Cartao.class);
                cartoes.add(cartao);
            }
            usuario.setCartoes(cartoes);
        }

        // Desserializa a lista de boletos
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

        // Serializa os campos básicos de Usuario
        jsonObject.addProperty("login", usuario.getLogin());
        jsonObject.addProperty("senha", usuario.getSenha());
        jsonObject.addProperty("nome", usuario.getNome());
        jsonObject.addProperty("cpf", usuario.getCpf());
        jsonObject.addProperty("email", usuario.getEmail());
        jsonObject.addProperty("admin", usuario.getAdmin());

        // Serializa a lista de ingressos
        JsonArray ingressosArray = new JsonArray();
        for (Ingresso ingresso : usuario.getIngressos()) {
            ingressosArray.add(context.serialize(ingresso));
        }
        jsonObject.add("ingressos", ingressosArray);

        // Serializa a lista de cartões
        JsonArray cartoesArray = new JsonArray();
        for (Cartao cartao : usuario.getCartoes()) {
            cartoesArray.add(context.serialize(cartao));
        }
        jsonObject.add("cartoes", cartoesArray);

        // Serializa a lista de boletos
        JsonArray boletosArray = new JsonArray();
        for (Boleto boleto : usuario.getBoletos()) {
            boletosArray.add(context.serialize(boleto));
        }
        jsonObject.add("boletos", boletosArray);

        return jsonObject;
    }
}

