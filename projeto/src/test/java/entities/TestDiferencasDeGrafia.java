package entities;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import tppe.entities.DiferencasDeGrafia;

/**
 * Suite de testes para o Caso 1: Diferencas de grafia (tipograficas).
 */
@RunWith(Parameterized.class)
public class TestDiferencasDeGrafia {

    private final String nomeComDiferencaGrafica;
    private final String nomeEsperado;
    private DiferencasDeGrafia diferencasDeGrafia;

    public TestDiferencasDeGrafia(String nomeComDiferencaGrafica, String nomeEsperado) {
        this.nomeComDiferencaGrafica = nomeComDiferencaGrafica;
        this.nomeEsperado = nomeEsperado;
    }

    @Before
    public void setup() {
        this.diferencasDeGrafia = new DiferencasDeGrafia();
    }

    @Parameters(name = "{index}: {0} -> {1}")
    public static Collection<Object[]> dados() {
        return Arrays.asList(new Object[][] {
            { "Monica Hirata Sant`anna", "Mônica Hirata Sant'anna" },
            { "Mônica Hirata Sant’anna", "Mônica Hirata Sant'anna" },
            { "Sergio Henrique Guaraldi", "Sérgio Henrique Guaraldi" },
            { "Sérgio Henrique Guaraldi", "Sérgio Henrique Guaraldi" },
            { "Veronica de Oliveira Moreira", "Verônica de Oliveira Moreira" }
        });
    }

    @Test
    public void deveUnificarDiferencasTipograficasParaGrafiaCorreta() {
        String nomeCorrigido = diferencasDeGrafia.corrigirGrafia(nomeComDiferencaGrafica);
        assertEquals(nomeEsperado, nomeCorrigido);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoQuandoNomeForNulo() {
        diferencasDeGrafia.corrigirGrafia(null);
    }
}
