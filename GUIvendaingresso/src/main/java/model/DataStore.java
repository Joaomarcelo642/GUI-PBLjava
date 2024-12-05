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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A classe DataStore é responsável por salvar e carregar dados de usuários, eventos e compras
 * do sistema de venda de ingressos. Utiliza a biblioteca Gson para serializar e desserializar
 * objetos em arquivos JSON.
 */
public class DataStore {

    /**
     * Caminho do arquivo JSON que armazena os dados de usuários.
     */
    private static final String USUARIOS_JSON = "usuarios.json";

    /**
     * Caminho do arquivo JSON que armazena os dados de eventos.
     */
    private static final String EVENTOS_JSON = "eventos.json";

    /**
     * Caminho do arquivo JSON que armazena os dados de compras.
     */
    private static final String COMPRAS_JSON = "compras.json";

    /**
     * Instância de Gson configurada para serialização e desserialização de objetos,
     * excluindo campos {@code static}, {@code final} e {@code transient}, e
     * utilizando um adaptador personalizado para a classe {@link Pagamento}.
     */
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithModifiers(Modifier.STATIC, Modifier.FINAL, Modifier.TRANSIENT)
            .registerTypeAdapter(Feedback.class, new FeedbackAdapter())
            .registerTypeAdapter(Pagamento.class, new PagamentoAdapter())
            .registerTypeAdapter(Usuario.class, new UsuarioAdapter())
            .registerTypeAdapter(Evento.class, new EventoAdapter())
            .create();

    /**
     * Salva uma lista de usuários em um arquivo JSON.
     *
     * @param usuarios A lista de usuários a ser salva.
     */
    public void salvarUsuarios(List<Usuario> usuarios) {
        salvarDados(USUARIOS_JSON, usuarios);
    }

    /**
     * Salva uma lista de eventos em um arquivo JSON.
     *
     * @param eventos A lista de eventos a ser salva.
     */
    public void salvarEventos(List<Evento> eventos) {
        salvarDados(EVENTOS_JSON, eventos);
    }

    /**
     * Salva uma lista de compras em um arquivo JSON.
     *
     * @param compras A lista de compras a ser salva.
     */
    public void salvarCompras(List<Compra> compras) {
        salvarDados(COMPRAS_JSON, compras);
    }

    /**
     * Carrega uma lista de usuários a partir de um arquivo JSON.
     *
     * @return Uma lista de objetos {@link Usuario} carregada do arquivo JSON.
     */
    public List<Usuario> carregarUsuarios() {
        return carregarDados(USUARIOS_JSON, Usuario[].class);
    }

    /**
     * Carrega uma lista de eventos a partir de um arquivo JSON.
     *
     * @return Uma lista de objetos {@link Evento} carregada do arquivo JSON.
     */
    public List<Evento> carregarEventos() {
        return carregarDados(EVENTOS_JSON, Evento[].class);
    }

    /**
     * Carrega uma lista de compras a partir de um arquivo JSON.
     *
     * @return Uma lista de objetos {@link Compra} carregada do arquivo JSON.
     */
    public List<Compra> carregarCompras() {
        return carregarDados(COMPRAS_JSON, Compra[].class);
    }

    /**
     * Salva uma lista de dados em um arquivo JSON especificado.
     *
     * @param filePath O caminho do arquivo onde os dados serão salvos.
     * @param dados    A lista de dados a ser salva.
     * @param <T>      O tipo dos dados na lista.
     */
    public <T> void salvarDados(String filePath, List<T> dados) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(dados, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega uma lista de dados de um arquivo JSON especificado.
     *
     * @param filePath O caminho do arquivo de onde os dados serão carregados.
     * @param clazz    A classe dos dados a serem carregados.
     * @param <T>      O tipo dos dados na lista.
     * @return Uma lista de objetos carregada do arquivo JSON. Retorna uma lista vazia se
     * ocorrer uma falha na leitura.
     */
    public <T> List<T> carregarDados(String filePath, Class<T[]> clazz) {
        try (FileReader reader = new FileReader(filePath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);

            if (jsonElement.isJsonArray()) {
                T[] dataArray = gson.fromJson(jsonElement, clazz);
                return new ArrayList<>(Arrays.asList(dataArray));
            } else if (jsonElement.isJsonObject()) {
                T dataObject = (T) gson.fromJson(jsonElement, clazz.getComponentType());
                return new ArrayList<>(Collections.singletonList(dataObject));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}