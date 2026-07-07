package tppe.entities;

import java.util.HashMap;
import java.util.Map;

import tppe.repository.NomesPadraoRepository;

/**
 * Caso 2: Sobrenome + Iniciais dos nomes
 * 
 * Unidade responsável por identificar e unificar ocorrências do nome completo
 * e de sua versão abreviada (com ou sem pontos nas iniciais).
 * A versão completa deve ser preferida em relação à versão abreviada.
 */
public class SobrenomeComIniciais {

    private final Map<String, String> nomesPadrao = new HashMap<>();
    private final GeradorDeChavesSobrenomeComIniciais geradorDeChaves;

    public SobrenomeComIniciais() {
        this(new NomesPadraoRepository());
    }

    public SobrenomeComIniciais(NomesPadraoRepository repositorio) {
        this.geradorDeChaves = new GeradorDeChavesSobrenomeComIniciais();

        for (String nomePadrao : repositorio.buscarNomesPadrao()) {
            adicionarNomePadrao(nomePadrao);
        }
    }

    public String unificarNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome nao pode ser nulo");
        }

        String chave = geradorDeChaves.gerarChaveComparacao(nome);
        return nomesPadrao.getOrDefault(chave, nome);
    }

    private void adicionarNomePadrao(String nomePadrao) {
        nomesPadrao.put(geradorDeChaves.gerarChaveComparacao(nomePadrao), nomePadrao);
        nomesPadrao.put(geradorDeChaves.gerarChaveSobrenomeComIniciais(nomePadrao), nomePadrao);
    }
}
