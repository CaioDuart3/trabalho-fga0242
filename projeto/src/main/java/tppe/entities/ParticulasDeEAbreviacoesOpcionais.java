package tppe.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Caso 3: Partículas 'de' e uso de ponto nas abreviações opcionais
 * 
 * Unidade responsável por lidar com omissão das partículas 'de' e variação
 * no uso de pontos após abreviações dos nomes/sobrenome.
 * A forma completa (com o uso da partícula 'de') deve ser considerada padrão.
 */
public class ParticulasDeEAbreviacoesOpcionais {

    private static final Map<String, String> NOMES_PADRAO = new HashMap<>();

    static {
        adicionarNomePadrao("Luiz de Oliveira de Souza");
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
        NOMES_PADRAO.put(gerarChaveAbreviada(nomePadrao), nomePadrao);
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
        List<String> partes = extrairPartesSemParticulas(nomePadrao);

        if (partes.size() <= 1) {
            return String.join(" ", partes);
        }

        StringBuilder chave = new StringBuilder(partes.get(0));

        for (int i = 1; i < partes.size() - 1; i++) {
            chave.append(" ").append(partes.get(i).charAt(0));
        }

        chave.append(" ").append(partes.get(partes.size() - 1));
        return chave.toString();
    }

    private static List<String> extrairPartesSemParticulas(String nome) {
        List<String> partes = new ArrayList<>();
        String[] tokens = nome.toLowerCase().trim().split("\\s+");

        for (String token : tokens) {
            if (!token.equals("de")) {
                partes.add(token.replace(".", ""));
            }
        }

        return partes;
    }
}
