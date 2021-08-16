“Tools amplify your talent. The better your tools, and the better you know how to use them, the more productive you can be.”  
― Andrew Hunt



###  *Sumário*
- [Pré-requisitos](#pré-requisitos)
- [Introdução](#introdução)
- [Um teste ingênuo](#um-teste-ingênuo)
- [Um teste menos ingênuo](#um-teste-menos-ingênuo)
- [Possibilidades de extensão](#possibilidades-de-extensão)


## *Pré-requisitos*
É aconselhável acompanhar o texto junto ao código, tornando o consumo dessas dissertação mais interessante. Primeiro você precisará da [JDK 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html) e o [Maven](https://maven.apache.org/download.cgi), agora podemos prosseguir para os próximos passos:
```
#Clone este repositório
$ git clone https://github.com/hirusunshine/JUnit-Prova-de-Conceito.git

OBS: Caso você não tenha uma IDE com suporte nativo pra
desenvolvimento JAVA e esteja usando um editor de texto,
como o VSCode, poderá estar instalando extensões que irão
facilitar seu desenvolvimento.
```

##  *Introdução*
Framework, ambiente que irá dar estrutura/suporte para construir algo, podendo ser uma ferramenta simples ou todo um ecossistema próprio. Dito isto, ficará inteligível o papel do JUnit 5 (framework/ferramenta) em tornar o desenvolvimento de testes unitários com maior otimização, legibilidade e confiabilidade.

Mas para atestar os benefícios que usar as ferramentas certas para os problemas certos trazem, maneiras menos eficientes, contudo sem deslegitima-las,  serão abordadas.

## *Um teste ingênuo*
Esse titulo, de fato, não é autoexplicativo, porém logo você entenderá seu significado, observe:
```
public  class  TesteIngenuoCalculadora {
	public  static  void  main(String[] args) {

	CalculadoraApplication  calc = new  CalculadoraApplication();
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

	  

	private  static  boolean  erroNaSoma(CalculadoraApplication  calc){
		boolean  temErro = false;

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

		return  temErro;

	}

		  

	private  static  boolean  subtracaoEstaCerta(CalculadoraApplication  calc) {
		if (calc.subtrair(2, 2) != 0) {
			return  false;
		}

		if (calc.subtrair(2, -2) != 4) {
			return  false;
		}

		if (calc.subtrair(-2, 2) != -4) {
			return  false;
		}

		if (calc.subtrair(-2, -2) != 0) {
			return  false;
		}

		if (calc.subtrair(0, 0) != 0) {
			return  false;
		}
		return  true;
	}

}
```
O que esse código te diz ? Não muita coisa, não é mesmo ? A culpa não é sua e nem devido a complexidade do algoritmo, porque não há, e, sim, a maneira com que foi escrito. E, sim, são testes, contudo irregulares. Mas por quê? Má nomenclatura, cadeia de métodos, falta de um padrão, afinal, será `true` ou `false` para emitir essa exceção\erro ? O método de verificação que irá emitir o  `erro` ou o método `main` ? E essa cadeia de verificações não pode criar um déficit de performance e otimização ? Tenho que ficar sempre instanciando a mesma `class CalculadoraApplication` toda vez que crio um novo método de teste ?
O teste tem em objetivo verificar a qualidade e funcionalidade de uma porção pequena da aplicação/biblioteca `Calculadora`, todavia não levando em conta esses questionamentos acima, com a ingenuidade de que conseguirá cumprir tal papel. Mas ele ainda consegue fazer verificações, correto ?

Sim, contudo foi testado apenas uma porção da porção da biblioteca `Calculadora`,  agora imagine escalar esses testes para todas as operações que estão contida na `Calculadora`.
```
public  class  CalculadoraApplication {

	public  double  somar(double  parcela1, double  parcela2){
		return  parcela1 + parcela2;
	}
 
	public  double  subtrair(double  minuendo, double  subtraendo){
		return  minuendo - subtraendo;
	}	  

	public  double  multiplicar(double  fator1, double  fator2){
		return  fator1 * fator2;
	}

	public  double  dividir(double  dividendo, double  divisor){
		return  dividendo/divisor;
	} 

}
```
Conseguiu ver  o problema ?

## *Um teste menos ingênuo*

Anteriormente você compreendeu o sentido de testes ingênuos, agora irá ser mais fácil ver o abismo entre ele e um menos ingênuo. Obviamente, esses testes não cobrem uma verdadeira estrutura, mas, sim, para exemplificar o ponto que está sendo apresentado. Para isso, será utilizado o JUnit. Agora, novamente, observe:

```
@Test
public  void  testesMenosIngenuosDeSoma() {
	CalculadoraApplication  calculadora = new  CalculadoraApplication();
	boolean  temErro = false;

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
```

O código está claro ? É visível a intenção de seus métodos ? Como dito, ele é menos ingênuo, se notar verá que ainda é um teste com cadeias de `ifs` que poderão tirar a performance, atrasando os demais processos, e minar futuros acréscimos ao teste, pois o acoplamento das verificações é demasiado ridículo. Mas qual seria a solução para esses problemas ? Torna-lo menos ingênuo:
```
@Test
public  void  testeSomarTresMaisTres(){
	CalculadoraApplication  calculadora = new  CalculadoraApplication();
	assertEquals(0.0, calculadora.somar(-2, 2));
}

@Test
public  void  testeSomaResultarEmZero(){
	CalculadoraApplication  calculadora = new  CalculadoraApplication();
	assertEquals(2.0, calculadora.somar(1, 1));
}
```
Métodos específicos, fácil legibilidade e baixíssimo acoplamento entre as validações, pois pode-se aplicar comportamentos específicos ou desativa-los sem afetar os demais. Tudo certo, né ? Não, há redundância de código ao instanciar a biblioteca `Calculadora` e ainda não é tão performático, pois os métodos consomem recursos demasiadamente. 
Contudo, tudo tem um custo, certo ? Então, como não consumir recursos ? Não irá deixar de consumir, apenas consumir o necessário evitando grande perda de performance, essa biblioteca é muito simples, agora imagine um sistema mais complexo e cheio de  camadas de funcionalidades para serem testadas, o controle do consumo dos recursos será essencial. Observe:

```
@BeforeAll
public  static  void  setup(){
	calculadora = new  CalculadoraApplication();
}

@DisplayName("Valida multiplas somas")
@ParameterizedTest
@CsvSource({"1.0, 1.0, 2.0" , "-2.0, 2.0, 0.0"})
void  testeMultiplasSomasCSV(double  parcela1, double  parcela2, double  resultadoEsperado){
	assertEquals(resultadoEsperado, calculadora.somar(parcela1, parcela2));
}
```

Agora há anotações do JUnit, como o `@BeforeALL`  onde podem serem pré-estabelecidas configurações antes de todos os métodos inicializarem, diminuindo a redundância do código e facilitando a leitura. Também há a possibilidade de fazer um teste com múltiplas validações, com o `@ParametizedTests`, sim, parecido com aquele teste ingênuo com cadeias de `ifs` que você viu anteriormente. Contudo, é um código mais limpo e padronizado. Será a reinvenção da roda apenas mais uma tolice ?


## *Possibilidades de extensão*
Basta as ferramentas certas para cada problema e entender essas ferramentas , de forma profunda, é a chave.
Você só não consegue criar, porque não entende aquilo. Quer um exemplo ? Lembra do `@BeforeALL` ?  Bem, há varias outras notações do JUnit - não se apegue a ferramenta, mas, sim, a ideia que está sendo passada - uma delas é o `@BeforeEach` ,  estabelecendo uma configuração antes de cada teste, como `cleanUp` , que pode limpar o método anterior e liberar recursos que irão ser usados pelo próximo, aumentando a performance dos testes.
Apenas foque em conhecer algo bem, quando for usar uma ferramenta, ela será apenas uma ferramenta e não o cerne do que você.
 











