package tppe.entities;

import java.util.HashMap;
import java.util.Map;
import tppe.utils.GeradorChaveDeNome;

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

        String chave = GeradorChaveDeNome.gerarChaveComparacao(nome);
        return NOMES_PADRAO.getOrDefault(chave, nome);
    }

    private static void adicionarNomePadrao(String nomePadrao) {
        NOMES_PADRAO.put(GeradorChaveDeNome.gerarChaveComparacao(nomePadrao), nomePadrao);
        NOMES_PADRAO.put(GeradorChaveDeNome.gerarChaveAbreviada(nomePadrao), nomePadrao);
    }
}
