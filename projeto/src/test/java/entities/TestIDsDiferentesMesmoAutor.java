package entities;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import tppe.entities.IDsDiferentesMesmoAutor;

/**
 * Suite de testes para o Caso 5: IDs diferentes para o mesmo autor
 */
@RunWith(Parameterized.class)
public class TestIDsDiferentesMesmoAutor {

    private final int[] idsDuplicadosDoAutor;
    private final int idUnificadoEsperado;
    private IDsDiferentesMesmoAutor idsDiferentesMesmoAutor;

    public TestIDsDiferentesMesmoAutor(int[] idsDuplicadosDoAutor, int idUnificadoEsperado) {
        this.idsDuplicadosDoAutor = idsDuplicadosDoAutor;
        this.idUnificadoEsperado = idUnificadoEsperado;
    }

    @Before
    public void setup() {
        this.idsDiferentesMesmoAutor = new IDsDiferentesMesmoAutor();
    }

    @Parameters(name = "{index}: menor ID esperado = {1}")
    public static Collection<Object[]> dados() {
        return Arrays.asList(new Object[][] {
            { new int[] { 31298, 433094, 549243, 608297, 746938 }, 31298 },
            { new int[] { 28372, 243349, 582585 }, 28372 }
        });
    }

    @Test
    public void deveMapearIDsDiferentesDoMesmoAutorParaMenorID() {
        for (int idOriginal : idsDuplicadosDoAutor) {
            int idMapeado = idsDiferentesMesmoAutor.mapearParaMenorID(idOriginal, idsDuplicadosDoAutor);
            assertEquals(idUnificadoEsperado, idMapeado);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoQuandoArrayDeIDsForNulo() {
        idsDiferentesMesmoAutor.mapearParaMenorID(31298, null);
    }
}
