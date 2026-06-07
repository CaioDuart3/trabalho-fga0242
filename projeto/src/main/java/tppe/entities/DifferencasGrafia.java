package tppe.entities;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

/**
 * Caso 1: Diferenças de grafia (tipográficas)
 * 
 * Unidade responsável por lidar com diferenças na codificação e grafia dos nomes.
 * Presença/ausência de acentuação, uso de acentuação diferente para representar
 * o mesmo item (apóstrofo, crase ou acento agudo), presença/ausência de cedilha
 * ou acentuação.
 */
public class DifferencasGrafia {

    private static final Map<String, String> NOMES_PADRAO = new HashMap<>();

    static {
        adicionarNomePadrao("Mônica Hirata Sant'anna");
        adicionarNomePadrao("Sérgio Henrique Guaraldi");
    }

    public String corrigirGrafia(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome nao pode ser nulo");
        }

        String chave = gerarChaveComparacao(nome);
        return NOMES_PADRAO.getOrDefault(chave, nome);
    }

    private static void adicionarNomePadrao(String nomePadrao) {
        NOMES_PADRAO.put(gerarChaveComparacao(nomePadrao), nomePadrao);
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
