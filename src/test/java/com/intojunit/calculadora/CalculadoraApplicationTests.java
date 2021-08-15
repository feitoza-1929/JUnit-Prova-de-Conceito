package com.intojunit.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculadoraApplicationTests {
	
// 1º Teste
	@Test
	public void testesMenosIngenuosSoma() {
		CalculadoraApplication calculadora = new CalculadoraApplication();

		boolean temErro = false;
		if(calculadora.somar(-2,2) != 0){
			temErro = true;
		}
		if(calculadora.somar(1, 1) != 2){
			temErro = true;
		}
		if(temErro){
			fail("Erro na validação da soma");
		}
		
	}

// 2º Teste 
	@Test
	public void testeSomarTresMaisTres(){
		CalculadoraApplication calculadora = new CalculadoraApplication();
		assertEquals(0.0, calculadora.somar(-2, 2));
	}

	@Test
	public void testeSomaResultarZero(){
		CalculadoraApplication calculadora = new CalculadoraApplication();
		assertEquals(2.0, calculadora.somar(1, 1));
	}

// 3º Teste
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
