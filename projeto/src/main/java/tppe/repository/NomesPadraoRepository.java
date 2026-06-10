package tppe.repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import tppe.repository.dto.Autor;
import tppe.repository.dto.BaseDeNomes;

public class NomesPadraoRepository {

    private static final String RECURSO_PADRAO = "/nomes-padrao.json";

    private final BaseDeNomes base;

    public NomesPadraoRepository() {
        this(RECURSO_PADRAO);
    }

    public NomesPadraoRepository(String recurso) {
        this.base = carregar(recurso);
    }

    public List<String> buscarNomesPadrao() {
        return base.getAutores().stream()
                .map(Autor::getNomePadrao)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Autor> buscarAutores() {
        return base.getAutores();
    }

    private static BaseDeNomes carregar(String recurso) {
        try (InputStream entrada = NomesPadraoRepository.class.getResourceAsStream(recurso)) {
            if (entrada == null) {
                throw new IllegalStateException("Arquivo de dados nao encontrado no classpath: " + recurso);
            }

            Reader leitor = new InputStreamReader(entrada, StandardCharsets.UTF_8);
            BaseDeNomes dados = new Gson().fromJson(leitor, BaseDeNomes.class);

            if (dados == null || dados.getAutores() == null) {
                throw new IllegalStateException("Conteudo invalido no arquivo de dados: " + recurso);
            }

            return dados;
        } catch (IOException e) {
            throw new IllegalStateException("Falha ao carregar os dados de " + recurso, e);
        }
    }
}
