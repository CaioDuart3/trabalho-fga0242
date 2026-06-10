package tppe.entities;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

/**
 * Caso 2: Sobrenome + Iniciais dos nomes
 * 
 * Unidade responsável por identificar e unificar ocorrências do nome completo
 * e de sua versão abreviada (com ou sem pontos nas iniciais).
 * A versão completa deve ser preferida em relação à versão abreviada.
 */
public class SobrenomeComIniciais {

    private static final String PARTICULA_DE = "de";
    private static final Map<String, String> NOMES_PADRAO = new HashMap<>();

    static {
        adicionarNomePadrao("Ana de Mattos Seabra");
        adicionarNomePadrao("Cassius de Souza");
    }

    public String unificarNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome nao pode ser nulo");
        }

        String chave = gerarChaveComparacao(nome);
        return NOMES_PADRAO.getOrDefault(chave, nome);
    }

    private static void adicionarNomePadrao(String nomePadrao) {
        NOMES_PADRAO.put(gerarChaveComparacao(nomePadrao), nomePadrao);
        NOMES_PADRAO.put(gerarChaveSobrenomeComIniciais(nomePadrao), nomePadrao);
    }

    private static String gerarChaveComparacao(String nome) {
        return removerAcentos(nome)
                .toLowerCase()
                .trim()
                .replace(".", "")
                .replace(",", "")
                .replaceAll("\\s+", " ");
    }

    private static String gerarChaveSobrenomeComIniciais(String nomePadrao) {
        String[] tokens = extrairTokensSemParticulas(nomePadrao);

        if (tokens.length <= 1) {
            return String.join(" ", tokens);
        }

        return montarChaveComSobrenomeEIniciais(tokens);
    }

    private static String[] extrairTokensSemParticulas(String nome) {
        return gerarChaveComparacao(nome)
                .replaceAll("\\b" + PARTICULA_DE + "\\b", "")
                .replaceAll("\\s+", " ")
                .trim()
                .split("\\s+");
    }

    private static String montarChaveComSobrenomeEIniciais(String[] tokens) {
        StringBuilder chave = new StringBuilder(tokens[tokens.length - 1]);

        for (int i = 0; i < tokens.length - 1; i++) {
            chave.append(" ").append(tokens[i].charAt(0));
        }

        return chave.toString();
    }

    private static String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }
}
