package model;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.List;

public class UsuarioAdapter implements JsonDeserializer<Usuario>, JsonSerializer<Usuario> {

    @Override
    public JsonElement serialize(Usuario src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("login", src.getLogin());
        jsonObject.addProperty("senha", src.getSenha());
        jsonObject.addProperty("nome", src.getNome());
        jsonObject.addProperty("cpf", src.getCpf());
        jsonObject.addProperty("email", src.getEmail());
        jsonObject.addProperty("isAdmin", src.isAdmin());
        jsonObject.add("ingressos", context.serialize(src.getIngressos()));
        jsonObject.add("formasDePagamento", context.serialize(src.getFormasDePagamento()));
        return jsonObject;
    }

    @Override
    public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String login = jsonObject.get("login").getAsString();
        String senha = jsonObject.get("senha").getAsString();
        String nome = jsonObject.get("nome").getAsString();
        String cpf = jsonObject.get("cpf").getAsString();
        String email = jsonObject.get("email").getAsString();
        Boolean isAdmin = jsonObject.get("isAdmin").getAsBoolean();

        List<Ingresso> ingressos = context.deserialize(jsonObject.get("ingressos"), List.class);
        List<Pagamento> formasDePagamento = context.deserialize(jsonObject.get("formasDePagamento"), List.class);

        Usuario usuario = new Usuario(login, senha, nome, cpf, email, isAdmin);
        usuario.setIngressos(ingressos);
        usuario.setFormasDePagamento(formasDePagamento);

        return usuario;
    }
}
