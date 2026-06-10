package tppe.repository.dto;

import java.util.List;

public class Autor {

    private int idPadrao;
    private String nomePadrao;
    private List<Registro> registros;

    public int getIdPadrao() {
        return idPadrao;
    }

    public String getNomePadrao() {
        return nomePadrao;
    }

    public List<Registro> getRegistros() {
        return registros;
    }
}
