package tppe.repository.dto;

import java.util.List;

public class BaseDeNomes {

    private String publicacao;
    private List<Autor> autores;

    public String getPublicacao() {
        return publicacao;
    }

    public List<Autor> getAutores() {
        return autores;
    }
}
