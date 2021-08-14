package com.intojunit.calculadora;

/* *
* Classe Calculadora com operações básicas,
* podemos considerar como a biblioteca em que faremos os testes ingênuos e menos ingênuos.
* 
* Estudo de caso (2021) realizado com base nas orientações do prof. Rui Rodrigues
*/


public class CalculadoraApplication {
	public double somar(double parcela1, double parcela2){
		return parcela1 + parcela2;
	}

	public double subtrair(double minuendo, double subtraendo){
		return minuendo - subtraendo;
	}

	public double multiplicar(double fator1, double fator2){
		return fator1 * fator2;
	}

	public double dividir(double dividendo, double divisor){
		return dividendo/divisor;
	}

}
