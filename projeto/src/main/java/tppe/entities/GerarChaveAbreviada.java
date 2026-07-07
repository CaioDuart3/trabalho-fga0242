package tppe.entities;

import java.util.List;
import java.util.ArrayList;


public class GerarChaveAbreviada {

    private final String nome;

    public GerarChaveAbreviada(String nome) {
        this.nome = nome;
    }

    public String compute() {
        List<String> partes = extrairPartesSemParticulas(nome);

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
