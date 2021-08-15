package com.intojunit.calculadora;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TesteIngenuoCalculadora {
    public static void main(String[] args) {
        CalculadoraApplication calc = new CalculadoraApplication();
        if(erroNaSoma(calc)){
            System.exit(1);
        }
        if (subtracaoEstaCerta(calc) == false) {
            System.out.println("\n\n## Erro na validação da subtração ##\n\n");
            System.exit(1);
        }
        System.out.println("\n\n==> Todos os testes passaram OK <==");
        System.exit(0);
    }

    private static boolean erroNaSoma(CalculadoraApplication calc){
        boolean temErro = false;
        if(calc.somar(-2, 2) != 0){
            temErro = true;
        }
        if(calc.somar(1, 1) != 2){
            temErro = true;
        }
        if(calc.somar(2, -2) != 0){
            temErro = true;
        }
        if(calc.somar(1, -1) != 0){
            temErro = true;
        }
        if(temErro){
            System.out.println("\n\n## Erro na validação da soma ##\n\n");
        }
        return temErro;
    }

    private static boolean subtracaoEstaCerta(CalculadoraApplication calc) {
        if (calc.subtrair(2, 2) != 0) {
            return false;
        }
        if (calc.subtrair(2, -2) != 4) {
            return false;
        }
        if (calc.subtrair(-2, 2) != -4) {
            return false;
        }
        if (calc.subtrair(-2, -2) != 0) {
            return false;
        }
        if (calc.subtrair(0, 0) != 0) {
            return false;
        }
        return true;
    }
    
}
