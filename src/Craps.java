import java.security.SecureRandom;

public class Craps {
	
	private static final SecureRandom aleatorio = new SecureRandom();
	private enum Estado{CONTINUA, VENCEU, PERDEU};
	private static final int SNAKE_EYES = 2;
	private static final int TREY = 3;
	private static final int SEVEN = 7;
	private static final int YO_LEVEN = 11;
	private static final int BOX_CARS = 12;
	
	
	public static void main(String[] args) {
		
		int[] rodadaVitoria = new int[21];
		int[] rodadaDerrota = new int[21];
		double rodadasTotais = 0;
		
		for(int i = 0; i < 1000000; i++){
			int minhaPontuacao = 0;
			int rodada = 0;
			Estado estadoDoJogo;
		
			int somaDosDados = jogaDados();
			rodada++;
			
			switch(somaDosDados){
			case SEVEN:
			case YO_LEVEN:
				estadoDoJogo = Estado.VENCEU;
				break;
			case SNAKE_EYES:
			case TREY:
			case BOX_CARS:
				estadoDoJogo = Estado.PERDEU;
				break;
			default:
				estadoDoJogo = Estado.CONTINUA;
				minhaPontuacao = somaDosDados;
				//System.out.printf("A pontuação é: %d%n", minhaPontuacao);
				break;
			}
			
			while(estadoDoJogo == Estado.CONTINUA){
				somaDosDados = jogaDados();
				rodada++;
				
				if(somaDosDados == minhaPontuacao){
					estadoDoJogo = Estado.VENCEU;
				}
				else if(somaDosDados == SEVEN){
					estadoDoJogo = Estado.PERDEU;
				}
			}
			
			rodadasTotais += rodada;
			
			if(estadoDoJogo == Estado.VENCEU){
				if(rodada <= 20){
					rodadaVitoria[rodada - 1]++;
				}else{
					rodadaVitoria[20]++;
				}
				//System.out.println("O Jogador venceu!");
			}else{
				if(rodada <= 20){
					rodadaDerrota[rodada - 1]++;
				}else{
					rodadaDerrota[20]++;
				}
				//System.out.println("O Jogador perdeu!");
			}
		}
		
		System.out.println("\nRodadas em que o jogador saiu como vencedor:");
		exibeEstatisticas(rodadaVitoria);
		System.out.println("\nRodadas em que o jogador saiu como perdedor");
		exibeEstatisticas(rodadaDerrota);
		exibeTotal(rodadaVitoria, rodadaDerrota);
		System.out.printf("Média de rodadas por jogo: %.1f", rodadasTotais/1000000.0);

	}
	
	public static int jogaDados(){
		int dado1 = 1 + aleatorio.nextInt(6);
		int dado2 = 1 + aleatorio.nextInt(6);
		
		int soma = dado1 + dado2;
		
		//System.out.printf("O jogador tirou nos dados %d + %d = %d%n", dado1, dado2, soma);
		
		return soma;
	}
	
	public static void exibeEstatisticas(int[] array){
		
		double porcentagem;
		
		System.out.printf("Rodadas\t");
		for(int i = 1; i<=21; i++){
			if(i == 21){
				System.out.printf("\t20+");
			}else{
				System.out.printf("\t%d", i);
			}
		}
		System.out.println();
		
		System.out.printf("Frequencia");
		for(int i = 0; i < array.length; i++){
			System.out.printf("\t%d", array[i]);
		}
		System.out.println();
		
		System.out.printf("Chances\t");
		for(int i = 0; i < array.length; i++){
			porcentagem = array[i] * 100.0 / 1000000.0;
			System.out.printf("\t%.2f%%", porcentagem);
		}
		System.out.println();
	}
	
	public static void exibeTotal(int[] arrayUm, int[] arrayDois){
		int soma = 0;
		for(int i = 0; i < arrayUm.length; i++){
			soma += arrayUm[i];
		}
		
		for(int i = 0; i < arrayDois.length; i++){
			soma += arrayDois[i];
		}
		System.out.printf("\nTotal de jogos: %d\n", soma);
	}
	

	


}
