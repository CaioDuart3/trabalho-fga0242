package tppe.entities;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

import tppe.repository.NomesPadraoRepository;

/**
 * Caso 4: Iniciais dos nomes agrupadas + sobrenome
 * 
 * Unidade responsável por lidar com casos em que as iniciais do nome
 * e dos primeiros sobrenomes são agrupadas, restando por extenso apenas
 * o último nome.
 * A versão completa do nome deve ser preferida em relação à versão com abreviações.
 */
public class IniciaisAgrupadasComSobrenome {

    private final Map<String, String> nomesPadrao = new HashMap<>();

    public IniciaisAgrupadasComSobrenome() {
        this(new NomesPadraoRepository());
    }

    public IniciaisAgrupadasComSobrenome(NomesPadraoRepository repositorio) {
        for (String nomePadrao : repositorio.buscarNomesPadrao()) {
            adicionarNomePadrao(nomePadrao);
        }
    }

    public String unificarNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome nao pode ser nulo");
        }

        String chave = gerarChaveComparacao(nome);
        return nomesPadrao.getOrDefault(chave, nome);
    }

    private void adicionarNomePadrao(String nomePadrao) {
        nomesPadrao.put(gerarChaveComparacao(nomePadrao), nomePadrao);
        nomesPadrao.put(gerarChaveAgrupada(nomePadrao), nomePadrao);
    }

    private static String gerarChaveComparacao(String nome) {
        return removerAcentos(nome)
                .toLowerCase()
                .trim()
                .replaceAll("\\s+", " ");
    }

    private static String gerarChaveAgrupada(String nomePadrao) {
        String[] tokens = getTokens(nomePadrao);

        if (tokens.length <= 1) {
            return String.join(" ", tokens);
        }

        StringBuilder iniciais = getIniciais(tokens);

        return iniciais + " " + tokens[tokens.length - 1];
    }

    private static StringBuilder getIniciais(String[] tokens) {
        StringBuilder iniciais = new StringBuilder();
        for (int i = 0; i < tokens.length - 1; i++) {
            iniciais.append(tokens[i].charAt(0));
        }
        return iniciais;
    }

    private static String[] getTokens(String nomePadrao) {
        String[] tokens = removerAcentos(nomePadrao)
                .toLowerCase()
                .trim()
                .split("\\s+");
        return tokens;
    }

    private static String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }
}
