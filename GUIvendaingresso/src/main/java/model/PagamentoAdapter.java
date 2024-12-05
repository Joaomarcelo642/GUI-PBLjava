/*******************************************************************************
 Sistema Operacional: Windows 10 - 64 Bits
 Linguagem: JAVA 21.0.4
 Autor: João Marcelo Nascimento Fernandes
 Componente Curricular: EXA 863 - MI Programção
 Concluido em: 28/10/2024
 Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 ******************************************************************************************/

package model;

import com.google.gson.*;
import java.lang.reflect.Type;

public class PagamentoAdapter implements JsonSerializer<Pagamento>, JsonDeserializer<Pagamento> {

    @Override
    public JsonElement serialize(Pagamento src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("forma", src.getForma());

        // Serializar dependendo da classe concreta
        if (src instanceof Cartao) {
            Cartao cartao = (Cartao) src;
            jsonObject.addProperty("numeroCartao", cartao.getNumero());
            jsonObject.addProperty("nome", cartao.getNome());
        } else if (src instanceof Boleto) {
            Boleto boleto = (Boleto) src;
            jsonObject.addProperty("codigoBoleto", boleto.getCodigoBoleto());
        }

        return jsonObject;
    }

    @Override
    public Pagamento deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String forma = jsonObject.get("forma").getAsString();

        if ("Cartão de Crédito".equals(forma)) {
            String numeroCartao = jsonObject.get("numeroCartao").getAsString();
            String nome = jsonObject.get("nome").getAsString();
            return new Cartao(numeroCartao, nome);
        } else if ("Boleto Bancário".equals(forma)) {
            String codigoBoleto = jsonObject.get("codigoBoleto").getAsString();
            return new Boleto(codigoBoleto);
        }

        throw new JsonParseException("Forma de pagamento desconhecida: " + forma);
    }
}
