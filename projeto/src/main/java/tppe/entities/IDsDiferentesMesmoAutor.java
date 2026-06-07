package tppe.entities;

/**
 * Caso 5: IDs diferentes para o mesmo autor
 * 
 * Unidade responsável por mapear registros de publicação e autorias duplicados
 * devido às diversas fontes de dados.
 * Todos os registros deverão ser mapeados para o mesmo ID, sendo o ID de menor
 * valor eleito para ser utilizado na deduplicação.
 */
public class IDsDiferentesMesmoAutor {

    public int mapearParaMenorID(int idOriginal, int[] idsDoMesmoAutor) {
        if (idsDoMesmoAutor == null) {
            throw new IllegalArgumentException("IDs do mesmo autor nao podem ser nulos");
        }

        int menorID = idOriginal;

        for (int id : idsDoMesmoAutor) {
            if (id < menorID) {
                menorID = id;
            }
        }

        return menorID;
    }
}
