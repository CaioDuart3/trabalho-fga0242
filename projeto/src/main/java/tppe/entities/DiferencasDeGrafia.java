package tppe.entities;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

import tppe.repository.NomesPadraoRepository;

/**
 * Caso 1: Diferenças de grafia (tipográficas)
 * 
 * Unidade responsável por lidar com diferenças na codificação e grafia dos nomes.
 * Presença/ausência de acentuação, uso de acentuação diferente para representar
 * o mesmo item (apóstrofo, crase ou acento agudo), presença/ausência de cedilha
 * ou acentuação.
 */
public class DiferencasDeGrafia {

    private final Map<String, String> nomesPadrao = new HashMap<>();

    public DiferencasDeGrafia() {
        this(new NomesPadraoRepository());
    }

    public DiferencasDeGrafia(NomesPadraoRepository repositorio) {
        for (String nomePadrao : repositorio.buscarNomesPadrao()) {
            adicionarNomePadrao(nomePadrao);
        }
    }

    public String corrigirGrafia(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome nao pode ser nulo");
        }

        String chave = gerarChaveComparacao(nome);
        return nomesPadrao.getOrDefault(chave, nome);
    }

    private void adicionarNomePadrao(String nomePadrao) {
        nomesPadrao.put(gerarChaveComparacao(nomePadrao), nomePadrao);
    }

    private static String gerarChaveComparacao(String nome) {
        String semAcentos = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return semAcentos
                .replace('`', '\'')
                .replace('’', '\'')
                .toLowerCase()
                .trim()
                .replaceAll("\\s+", " ");
    }
}
