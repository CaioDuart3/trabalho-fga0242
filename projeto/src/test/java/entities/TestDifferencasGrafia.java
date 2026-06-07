package entities;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import tppe.entities.DifferencasGrafia;

/**
 * Suite de testes para o Caso 1: Diferencas de grafia (tipograficas).
 */
@RunWith(Parameterized.class)
public class TestDifferencasGrafia {

    private final String nomeComDiferencaGrafica;
    private final String nomeEsperado;
    private DifferencasGrafia differencasGrafia;

    public TestDifferencasGrafia(String nomeComDiferencaGrafica, String nomeEsperado) {
        this.nomeComDiferencaGrafica = nomeComDiferencaGrafica;
        this.nomeEsperado = nomeEsperado;
    }

    @Before
    public void setup() {
        this.differencasGrafia = new DifferencasGrafia();
    }

    @Parameters(name = "{index}: {0} -> {1}")
    public static Collection<Object[]> dados() {
        return Arrays.asList(new Object[][] {
            { "Monica Hirata Sant`anna", "Mônica Hirata Sant'anna" },
            { "Mônica Hirata Sant’anna", "Mônica Hirata Sant'anna" },
            { "Sergio Henrique Guaraldi", "Sérgio Henrique Guaraldi" },
            { "Sérgio Henrique Guaraldi", "Sérgio Henrique Guaraldi" }
        });
    }

    @Test
    public void deveUnificarDiferencasTipograficasParaGrafiaCorreta() {
        String nomeCorrigido = differencasGrafia.corrigirGrafia(nomeComDiferencaGrafica);
        assertEquals(nomeEsperado, nomeCorrigido);
    }
}
