package com.intojunit.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Essa estrutura de testes ainda é muito ordinária, contudo ainda serve para exemplificar
 * o ponto desse caso de estudo, o porquê de usarmos uma estrutura/framework, padrões e
 * boas práticas na elaboração de um ambiente e, em si, os testes.
 * 
 * Agora, observe como a estrutura do teste faz um pouco mais de sentido,
 *
 */

@SpringBootTest
class CalculadoraApplicationTests {

	@Test
	public void testesMenosIngenuosSoma() {
		CalculadoraApplication calculadora = new CalculadoraApplication();

		boolean temErro = false;
		if(calculadora.somar(3,3) != 6){
			temErro = true;
		}
		if(calculadora.somar(2, -2) != 0){
			temErro = true;
		}
		if(temErro){
			fail("Erro na validação da soma");
		}
		
	}

	@Test
	public void testeSomarTresMaisTres(){
		CalculadoraApplication calculadora = new CalculadoraApplication();
		assertEquals(0.0, calculadora.somar(3, 3));
	}

	@Test
	public void testeSomaResultarZero(){
		CalculadoraApplication calculadora = new CalculadoraApplication();
		assertEquals(0.0, calculadora.somar(2, -2));
	}

	@BeforeAll
	public static void setup(){
		CalculadoraApplication calculadora = new CalculadoraApplication();
	}

	@DisplayName("Valida multiplas somas")
	@ParameterizedTest
	@CsvSource({"1.0, 1.0, 2.0","-2.0, 2.0, 0.0"})
	void testeMultiplasSomasCSV(double parcela1, double parcela2, double resultadoEsperado){
		assertEquals(resultadoEsperado, calculadora.somar(parcela1, parcela2));
	}
	

}
