package com.intojunit.calculadora;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/* *
* Iremos testar nossa biblioteca calculadora, para isso iremos usar de testes ingênuos.
* Por que teste ingênuos ? Bem, eles simplesmentes são testes feitos sem a estrutura,
* sendo redundante, de um framework, onde há padrões para melhor otimização, legibilidade e
* confiabilidade dos testes, no caso, unitários. Veja o código abaixo onde testamos apenas dois metódos
* da nossa biblioteca. 
*/


@SpringBootApplication
public class TesteIngenuoCalculadora {
    public static void main(String[] args) {
        CalculadoraApplication calculadora = new CalculadoraApplication();

        if(erroNaSoma(calculadora)){
            System.exit(1);
        }

        if(corretudeNaSubtracao(calculadora) == false){
            System.out.println("\n\n## Erro na Validação da subtração");
            System.exit(1);
        }
        System.out.println("\n\n==> Todos os testes passaram OK <==");
        System.exit(0);

        /**
        *Repare como a qualidade desse código é duvidosa, anote essa observação.
        */
    }

    private static boolean erroNaSoma(CalculadoraApplication calculadora){
        boolean temErro = false;
        if(calculadora.somar(4, 4) != 8){
            temErro = true;
        }
        if(calculadora.somar(6, -6) != 0){
            temErro = true;
        }
        if(calculadora.somar(-6, 6) != 0){
            temErro = true;
        }
        if(calculadora.somar(0, 0) != 0){
            temErro = true;
        }
        if(temErro){
            System.out.println("\n\n## Erro na validação da soma ##\n\n");
        }
        return temErro;
    }
    
    /**
     * Má nomeclatura, não está na cara que realmente é um teste da nossa biblioteca,
     * inconstância em como validar e notificar o erro, deu pra entender ?
     * A qualidade do código irá depender muito do programador, ou seja,
     * reinventando a roda a cada escrita e abrindo incriveis brechas que irão
     * ruir toda estrutura de testes desse software.
     */

    private static boolean corretudeNaSubtracao(CalculadoraApplication  calculadora){
        boolean corretude = true;
        if(calculadora.subtrair(7, 4) != 3){
            corretude = false;
        }
        if(calculadora.subtrair(6, -6) != 12){
            corretude = false;
        }
        if(calculadora.subtrair(-6, 6) != -12){
            corretude = false;
        }
        if(calculadora.subtrair(2, 1) != 1){
            corretude = false;
        }
        return corretude;
    }

    
}
