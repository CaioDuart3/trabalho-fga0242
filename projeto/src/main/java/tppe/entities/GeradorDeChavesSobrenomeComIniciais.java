package tppe.entities;

import java.text.Normalizer;

class GeradorDeChavesSobrenomeComIniciais {

    private static final String PARTICULA_DE = "de";

    String gerarChaveComparacao(String nome) {
        return removerAcentos(nome)
                .toLowerCase()
                .trim()
                .replace(".", "")
                .replace(",", "")
                .replaceAll("\\s+", " ");
    }

    String gerarChaveSobrenomeComIniciais(String nomePadrao) {
        String[] tokens = extrairTokensSemParticulas(nomePadrao);

        if (tokens.length <= 1) {
            return String.join(" ", tokens);
        }

        return montarChaveComSobrenomeEIniciais(tokens);
    }

    private String[] extrairTokensSemParticulas(String nome) {
        return gerarChaveComparacao(nome)
                .replaceAll("\\b" + PARTICULA_DE + "\\b", "")
                .replaceAll("\\s+", " ")
                .trim()
                .split("\\s+");
    }

    private String montarChaveComSobrenomeEIniciais(String[] tokens) {
        StringBuilder chave = new StringBuilder(tokens[tokens.length - 1]);

        for (int i = 0; i < tokens.length - 1; i++) {
            chave.append(" ").append(tokens[i].charAt(0));
        }

        return chave.toString();
    }

    private String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }
}
