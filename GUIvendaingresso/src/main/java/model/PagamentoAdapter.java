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

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * A classe PagamentoAdapter é um adaptador personalizado para a serialização e
 * desserialização de objetos da classe {@link Pagamento} em JSON, utilizando
 * a biblioteca Gson. Esta classe identifica se o objeto é uma instância de
 * {@link Boleto} ou {@link Cartao} e adapta a saída JSON de acordo.
 */
public class PagamentoAdapter extends TypeAdapter<Pagamento> {

    /**
     * Serializa um objeto {@link Pagamento} para JSON.
     *
     * @param out      O escritor JSON onde a saída será escrita.
     * @param pagamento O objeto {@link Pagamento} a ser serializado.
     * @throws IOException Se ocorrer um erro de entrada ou saída.
     */
    @Override
    public void write(JsonWriter out, Pagamento pagamento) throws IOException {
        out.beginObject();
        out.name("tipo").value(pagamento instanceof Boleto ? "Boleto" : "Cartao");

        if (pagamento instanceof Boleto) {
            out.name("codigoBoleto").value(((Boleto) pagamento).getCodigoBoleto());
        } else if (pagamento instanceof Cartao) {
            out.name("numeroCartao").value(((Cartao) pagamento).getNumero());
            out.name("nome").value(((Cartao) pagamento).getNome());
        }
        out.name("forma").value(pagamento.getForma());
        out.endObject();
    }

    /**
     * Desserializa um JSON para um objeto {@link Pagamento}.
     *
     * @param in O leitor JSON de onde os dados serão lidos.
     * @return Um objeto {@link Pagamento} correspondente aos dados JSON.
     * @throws IOException Se ocorrer um erro de entrada ou saída.
     */
    @Override
    public Pagamento read(JsonReader in) throws IOException {
        in.beginObject();
        String tipo = null;
        String forma = null;
        String codigoBoleto = null;
        String numeroCartao = null;
        String nome = null;

        while (in.hasNext()) {
            String fieldName = in.nextName();
            if (fieldName.equals("tipo") && in.peek() == JsonToken.STRING) {
                tipo = in.nextString();
            } else if (fieldName.equals("forma") && in.peek() == JsonToken.STRING) {
                forma = in.nextString();
            } else if (fieldName.equals("codigoBoleto") && in.peek() == JsonToken.STRING) {
                codigoBoleto = in.nextString();
            } else if (fieldName.equals("numeroCartao") && in.peek() == JsonToken.STRING) {
                numeroCartao = in.nextString();
            } else if (fieldName.equals("nome") && in.peek() == JsonToken.STRING) {
                nome = in.nextString();
            } else {
                in.skipValue();
            }
        }
        in.endObject();

        if ("Boleto".equals(tipo)) {
            return new Boleto(codigoBoleto);
        } else if ("Cartao".equals(tipo)) {
            return new Cartao(numeroCartao, nome);
        } else {
            throw new IOException("Tipo de pagamento não identificado: " + tipo);
        }
    }
}
