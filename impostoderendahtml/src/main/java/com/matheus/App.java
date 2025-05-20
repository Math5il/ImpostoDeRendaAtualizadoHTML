package com.matheus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class App 
{
    @PostMapping("/calcular-ir")
    public Resultado calcularIR(@RequestBody DadosEntrada dados) {
        double rendimentoAnual = dados.getSalario() * 12;
        double outras = dados.getDescontos() * 12;
        int dependentes = dados.getDependentes();

        double totalDeducoes = outras + (dependentes * 2275.08);
        double baseCalculo = rendimentoAnual - totalDeducoes;

        if (baseCalculo < 0) baseCalculo = 0;

        double imposto = CalcularImposto(baseCalculo);

        return new Resultado(imposto);
    }

    public static double CalcularImposto(double renda) {
        double imposto;

        if (renda <= 27870.40) {
            imposto = 0;
        } else if (renda <= 37751.55) {
            imposto = (renda - 27870.40) * 0.075;
        } else if (renda <= 50148.25) {
            imposto = (renda - 37751.55) * 0.15 +
                      (37751.55 - 27870.40) * 0.075;
        } else if (renda <= 64259.59) {
            imposto = (renda - 50148.25) * 0.225 +
                      (50148.25 - 37751.55) * 0.15 +
                      (37751.55 - 27870.40) * 0.075;
        } else {
            imposto = (renda - 64259.59) * 0.275 +
                      (64259.59 - 50148.25) * 0.225 +
                      (50148.25 - 37751.55) * 0.15 +
                      (37751.55 - 27870.40) * 0.075;
        }

        return imposto;
    }
}
