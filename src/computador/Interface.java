package computador;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interface {

	private static Scanner teclado;
	static Computador dell = new Computador();

	public static void main(String[] args) throws Exception {
		telaPrincipal();
	}

	static void telaPrincipal() throws Exception {

		teclado = new Scanner(System.in);

		Integer opcaoEscolhida = 0;
		try {
			do {
				System.out.println("Selecione uma opcao:");
				System.out.println("1 - Criar arquivo");
				System.out.println("2 - Exibir arquivos na HD");
				System.out.println("3 - Remover arquivo");
				System.out.println("4 - Restaurar arquivos");
				System.out.println("0 - Desligar o Computador");
				try {
					opcaoEscolhida = teclado.nextInt();
				} catch (InputMismatchException e) {
					teclado.next();
					System.out.println("Informe um numero! \n");
					telaPrincipal();
				}

				switch (opcaoEscolhida) {
				case 1:
					viewCriarArquivo();
					break;
				case 2:
					viewExibirArquivos();
					break;
				case 3:
					viewExclusao();
					break;
				case 4:
					viewParaRecuperar();
					break;
				case 0:
					limpatela();
					System.out.println("Desligando o computador...");
					break;
				default:
					limpatela();
					System.out.println("Opcao invalida\n");
					break;
				}
			} while (opcaoEscolhida != 0);
		} catch (NullPointerException f) {
		}
	}

	public static void viewCriarArquivo() {
		teclado = new Scanner(System.in);
		System.out.print("Informe o nome do arquivo.");
		String nome = teclado.nextLine();
		System.out.print("Informe o conteudo do arquivo.");
		String conteudo = teclado.nextLine();
		dell.criarArquivo(nome, conteudo);
	}

	public static void viewExibirArquivos() {
		teclado = new Scanner(System.in);
		System.out.println(dell.mostrarConteudoDoDisco());
		if (dell.mostrarConteudoDoDisco().isEmpty()) {
			System.out.println("Nao ha arquivos para exibir.");
		} else {
			int obj = 0;
			try {
				do {
					obj = teclado.nextInt();
					if (obj != 0) {
						System.out.println(dell.exibirDetalhesArquivo(obj));
					}
				} while (obj != 0);
			} catch (IndexOutOfBoundsException e) {
				System.out.print("Selecione um arquivo que esteja listado ou 0 (zero) para voltar:\n");
				viewExibirArquivos();
			} catch (InputMismatchException e) {
				System.out.print("Informe um numero ou 0 (zero) para voltar.\n");
				viewExibirArquivos();
			}
		}
	}

	public static void viewExclusao() throws Exception {
		teclado = new Scanner(System.in);
		Integer opcaoRemocao = 0;
		String resultadoDaExclusao;
		int arquivoParaExclusao;
		if (dell.mostrarConteudoDoDisco().isEmpty()) {
			System.out.println("Nao ha arquivos para exibir.");
		} else {
			System.out.println("Qual metodo de exclusao voce deseja? ");
			System.out.println("1 - Exclusao SIMPLES");
			System.out.println("2 - Exclusao SHRED");
			try {
				opcaoRemocao = teclado.nextInt();
			} catch (java.util.InputMismatchException e) {
				teclado.next();
				System.out.println("Informe um numero! \n");
				viewExclusao();
			}
			switch (opcaoRemocao) {
			case 1:
				System.out.println(dell.mostrarConteudoDoDisco());
				System.out.print("Informe o arquivo.");
				arquivoParaExclusao = teclado.nextInt();
				resultadoDaExclusao = dell.excluirArquivo(MetodoExclusao.NATIVA, arquivoParaExclusao);
				System.out.println(resultadoDaExclusao);
				break;
			case 2:
				System.out.println(dell.mostrarConteudoDoDisco());
				System.out.print("Informe o arquivo.");
				arquivoParaExclusao = teclado.nextInt();
				resultadoDaExclusao = dell.excluirArquivo(MetodoExclusao.SHRED, arquivoParaExclusao);
				System.out.println(resultadoDaExclusao);
				break;
			default:
				System.out.println("Opcao invalida\n");
				viewExclusao();
				break;
			}

		}
	}

	public static void viewParaRecuperar() throws Exception {
		teclado = new Scanner(System.in);
		if (dell.exibirParaRecuperar().isEmpty()) {
			System.out.println("Nao ha arquivos para exibir.");
		} else {
			System.out.println(dell.exibirParaRecuperar());
			int obj = 0;
			try {
				obj = teclado.nextInt();
				if (obj != 0) {
					Arquivo arquivoRecuperado = dell.recuperarArquivo(obj);
					System.out.println(arquivoRecuperado.getNome() + " RECUPERADO com sucesso!");
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.print("Selecione um arquivo que esteja listado.\n");
				viewParaRecuperar();
			} catch (InputMismatchException e) {
				System.out.print("Informe um numero.\n");
				viewParaRecuperar();
			}
		}
	}

	public static void limpatela() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

}
