package entities;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import tppe.entities.SobrenomeComIniciais;

/**
 * Suite de testes para o Caso 2: Sobrenome + Iniciais dos nomes
 */
@RunWith(Parameterized.class)
public class TestSobrenomeComIniciais {

    private final String nomeVariante;
    private final String nomeEsperado;
    private SobrenomeComIniciais sobrenomeComIniciais;

    public TestSobrenomeComIniciais(String nomeVariante, String nomeEsperado) {
        this.nomeVariante = nomeVariante;
        this.nomeEsperado = nomeEsperado;
    }

    @Before
    public void setup() {
        this.sobrenomeComIniciais = new SobrenomeComIniciais();
    }

    @Parameters(name = "{index}: {0} -> {1}")
    public static Collection<Object[]> dados() {
        return Arrays.asList(new Object[][] {
            { "Seabra A M", "Ana de Mattos Seabra" },
            { "Seabra A. M.", "Ana de Mattos Seabra" },
            { "Ana de Mattos Seabra", "Ana de Mattos Seabra" },
            { "Souza C.", "Cassius de Souza" },
            { "Souza C", "Cassius de Souza" }
        });
    }

    @Test
    public void deveUnificarSobrenomeComIniciaisParaFormaCompleta() {
        String nomeUnificado = sobrenomeComIniciais.unificarNome(nomeVariante);
        assertEquals(nomeEsperado, nomeUnificado);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoQuandoNomeForNulo() {
        sobrenomeComIniciais.unificarNome(null);
    }
}
