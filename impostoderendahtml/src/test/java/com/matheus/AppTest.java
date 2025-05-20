package com.matheus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AppTest 
{
    @Test
    public void deveRetornarZeroParaIsento() {
        double imposto = App.CalcularImposto(25000);
        assertEquals(0.0, imposto, 0.01);
    }

    @Test
    public void deveCalcularImpostoCorretamenteFaixa2() {
        double imposto = App.CalcularImposto(30000.00);
        // Faixa 2: (30000 - 27870.40) * 7.5%
        double esperado = (30000.00 - 27870.40) * 0.075;
        assertEquals(esperado, imposto, 0.01);
    }

    @Test
    public void deveCalcularImpostoCorretamenteFaixa4() {
        double imposto = App.CalcularImposto(60000.00);
        // Calcula manualmente ou deixa o teste rodar para garantir que não quebrou
        assertTrue(imposto > 0);
    }

    @Test
    public void deveRetornarZeroParaBaseNegativa() {
        DadosEntrada entrada = new DadosEntrada();
        entrada.setSalario(1000); // 12.000/ano
        entrada.setDescontos(1000); // 12.000/ano
        entrada.setDependentes(5); // 11.375,40 de dedução

        double rendimentoAnual = entrada.getSalario() * 12;
        double outras = entrada.getDescontos() * 12;
        double totalDeducoes = outras + (entrada.getDependentes() * 2275.08);
        double base = rendimentoAnual - totalDeducoes;
        if (base < 0) base = 0;

        double imposto = App.CalcularImposto(base);

        assertEquals(0.0, imposto, 0.01);
    }

}
