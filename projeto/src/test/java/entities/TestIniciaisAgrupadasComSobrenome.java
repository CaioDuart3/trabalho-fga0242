package entities;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import tppe.entities.IniciaisAgrupadasComSobrenome;

/**
 * Suite de testes para o Caso 4: Iniciais dos nomes agrupadas + sobrenome.
 */
@RunWith(Parameterized.class)
public class TestIniciaisAgrupadasComSobrenome {

    private final String nomeVariante;
    private final String nomeEsperado;
    private IniciaisAgrupadasComSobrenome iniciaisAgrupadasComSobrenome;

    public TestIniciaisAgrupadasComSobrenome(String nomeVariante, String nomeEsperado) {
        this.nomeVariante = nomeVariante;
        this.nomeEsperado = nomeEsperado;
    }

    @Before
    public void setup() {
        this.iniciaisAgrupadasComSobrenome = new IniciaisAgrupadasComSobrenome();
    }

    @Parameters(name = "{index}: {0} -> {1}")
    public static Collection<Object[]> dados() {
        return Arrays.asList(new Object[][] {
            { "VC Junior", "Vanilda Cristina Junior" },
            { "Vanilda Cristina Junior", "Vanilda Cristina Junior" },
            { "SH Guaraldi", "Sérgio Henrique Guaraldi" },
            { "Sérgio Henrique Guaraldi", "Sérgio Henrique Guaraldi" },
            { "RG Viana", "Raphael Gonçalves Viana" }
        });
    }

    @Test
    public void deveUnificarIniciaisAgrupadasComSobrenomeParaFormaCompleta() {
        String nomeUnificado = iniciaisAgrupadasComSobrenome.unificarNome(nomeVariante);
        assertEquals(nomeEsperado, nomeUnificado);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoQuandoNomeForNulo() {
        iniciaisAgrupadasComSobrenome.unificarNome(null);
    }
}