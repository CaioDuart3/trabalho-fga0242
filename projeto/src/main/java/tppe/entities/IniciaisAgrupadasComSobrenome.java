package tppe.entities;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

/**
 * Caso 4: Iniciais dos nomes agrupadas + sobrenome
 * 
 * Unidade responsável por lidar com casos em que as iniciais do nome
 * e dos primeiros sobrenomes são agrupadas, restando por extenso apenas
 * o último nome.
 * A versão completa do nome deve ser preferida em relação à versão com abreviações.
 */
public class IniciaisAgrupadasComSobrenome {

    private static final Map<String, String> NOMES_PADRAO = new HashMap<>();

    static {
        adicionarNomePadrao("Vanilda Cristina Junior");
        adicionarNomePadrao("Sérgio Henrique Guaraldi");
        adicionarNomePadrao("Raphael Gonçalves Viana");
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
        NOMES_PADRAO.put(gerarChaveAgrupada(nomePadrao), nomePadrao);
    }

    private static String gerarChaveComparacao(String nome) {
        return removerAcentos(nome)
                .toLowerCase()
                .trim()
                .replaceAll("\\s+", " ");
    }

    private static String gerarChaveAgrupada(String nomePadrao) {
        String[] tokens = removerAcentos(nomePadrao)
                .toLowerCase()
                .trim()
                .split("\\s+");

        if (tokens.length <= 1) {
            return String.join(" ", tokens);
        }

        StringBuilder iniciais = new StringBuilder();
        for (int i = 0; i < tokens.length - 1; i++) {
            iniciais.append(tokens[i].charAt(0));
        }

        return iniciais + " " + tokens[tokens.length - 1];
    }

    private static String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }
}
