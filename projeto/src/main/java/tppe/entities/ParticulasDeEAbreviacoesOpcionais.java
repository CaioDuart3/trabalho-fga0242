package tppe.entities;

import java.util.HashMap;
import java.util.Map;

import tppe.repository.NomesPadraoRepository;

/**
 * Caso 3: Partículas 'de' e uso de ponto nas abreviações opcionais
 * 
 * Unidade responsável por lidar com omissão das partículas 'de' e variação
 * no uso de pontos após abreviações dos nomes/sobrenome.
 * A forma completa (com o uso da partícula 'de') deve ser considerada padrão.
 */
public class ParticulasDeEAbreviacoesOpcionais {

    private final Map<String, String> nomesPadrao = new HashMap<>();

    public ParticulasDeEAbreviacoesOpcionais() {
        this(new NomesPadraoRepository());
    }

    public ParticulasDeEAbreviacoesOpcionais(NomesPadraoRepository repositorio) {
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
        nomesPadrao.put(gerarChaveAbreviada(nomePadrao), nomePadrao);
    }

    private static String gerarChaveComparacao(String nome) {
        return nome
                .toLowerCase()
                .trim()
                .replace(".", "")
                .replaceAll("\\bde\\b", "")
                .replaceAll("\\s+", " ")
                .trim();
    }

    private static String gerarChaveAbreviada(String nomePadrao) {
        return new GerarChaveAbreviada(nomePadrao).compute();
    }
}
