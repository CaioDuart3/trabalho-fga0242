package entities;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import tppe.entities.ParticulasDeEAbreviacoesOpcionais;

/**
 * Suite de testes para o Caso 3: Partículas 'de' e uso de ponto nas abreviações opcionais.
 */
@RunWith(Parameterized.class)
public class TestParticulasDeEAbreviacoesOpcionais {

    private final String nomeVariante;
    private final String nomeEsperado;
    private ParticulasDeEAbreviacoesOpcionais particulasDeEAbreviacoesOpcionais;

    public TestParticulasDeEAbreviacoesOpcionais(String nomeVariante, String nomeEsperado) {
        this.nomeVariante = nomeVariante;
        this.nomeEsperado = nomeEsperado;
    }

    @Before
    public void setup() {
        this.particulasDeEAbreviacoesOpcionais = new ParticulasDeEAbreviacoesOpcionais();
    }

    @Parameters(name = "{index}: {0} -> {1}")
    public static Collection<Object[]> dados() {
        return Arrays.asList(new Object[][] {
            { "Luiz Oliveira Souza", "Luiz de Oliveira de Souza" },
            { "Luiz de O. de Souza", "Luiz de Oliveira de Souza" },
            { "Luiz de O de Souza", "Luiz de Oliveira de Souza" },
            { "Luiz de Oliveira de Souza", "Luiz de Oliveira de Souza" },
            { "Ana Mattos Seabra", "Ana de Mattos Seabra" },
            { "Cassius Souza", "Cassius de Souza" }
        });
    }

    @Test
    public void deveUnificarParticulasDeEAbreviacoesOpcionaisParaFormaCompleta() {
        String nomeUnificado = particulasDeEAbreviacoesOpcionais.unificarNome(nomeVariante);
        assertEquals(nomeEsperado, nomeUnificado);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoQuandoNomeForNulo() {
        particulasDeEAbreviacoesOpcionais.unificarNome(null);
    }
}
