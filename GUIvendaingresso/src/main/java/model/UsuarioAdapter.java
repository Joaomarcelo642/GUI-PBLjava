package model;

import com.google.gson.*;
import java.lang.reflect.Type;

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
        return jsonObject;
    }

    @Override
    public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        return new Usuario(
                jsonObject.get("login").getAsString(),
                jsonObject.get("senha").getAsString(),
                jsonObject.get("nome").getAsString(),
                jsonObject.get("cpf").getAsString(),
                jsonObject.get("email").getAsString(),
                jsonObject.get("isAdmin").getAsBoolean()
        );
    }
}

