package computador;

import java.util.Random;

public class Shred {

	/* Mostra o progresso */
	public String v(int operacao, int numeroOperacoes, int metodo) {
		if (metodo == 0) {
			return ("Passagem " + operacao + "/" + numeroOperacoes + "(random)\n");
		} else if (metodo == 1) {
			return ("Passagem " + operacao + "/" + numeroOperacoes + "(000000)\n");
		} else
			return "";
	}

	/* define a quantidade de operacoes de sobrescrita a serem realizadas */
	public int n() {
		return 3;
	}

	/* adiciona zeros ao conteúdo do arquivo */
	public String z(Arquivo arquivoParaExclusao) {
		String resultado = v(n() + 1, n() + 1, 1);
		arquivoParaExclusao.setConteudo("");
		return resultado;
	}

	/*
	 * Modifica o conteúdo do arquivo com valores aleatórios repetindo esse
	 * processo por padrão três vezes sem preservar seu tamanho original no
	 * final dessa etapa, depois renomeia o arquivo com vários zeros e no final
	 * exclui sua referencia na tabela de arquivos do SO.
	 */
	public String u(DiscoRigido discoRigido, Arquivo arquivo) throws Exception {
		Random gerador = new Random();
		StringBuilder conteudo = new StringBuilder();
		StringBuilder processoExclusao = new StringBuilder();

		/*
		 * multiplo FORs se fizeram necessario para que a visualizacao do
		 * arquivo seja promovida pela ligacao da fat ao ponteiro do arquivo
		 */
		for (int i = 0; i < discoRigido.getFat().size(); i++) {
			for (int y = 0; y < discoRigido.getCluster().size(); y++) {
				if (discoRigido.getFat().get(i).getLocalizacao()
						.equals(discoRigido.getCluster().get(y).getReferencia())) {
					if (discoRigido.getFat().get(i).getLocalizacao().equals(arquivo.getReferencia())) {
						Arquivo arquivoParaExclusaoCluster = discoRigido.getCluster().get(y);
						Endereco arquivoParaExclusaoFat = discoRigido.getFat().get(i);
						for (int j = 0; j < n(); j++) {
							String resultado = v(j + 1, n() + 1, 0);
							processoExclusao.append(resultado);
							conteudo.append((char) (gerador.nextInt(26) + 65) + "" + (gerador.nextInt(1000) + 1000) + ""
									+ (char) (gerador.nextInt(26) + 65));
							arquivoParaExclusaoCluster.setConteudo(conteudo.toString());
							arquivoParaExclusaoCluster.setNome("0");
							arquivoParaExclusaoCluster.setTamanho(0);
							arquivoParaExclusaoFat.setLocalizacao("null");
						}
						processoExclusao.append(z(arquivoParaExclusaoCluster));
					}
				}
			}
		}
		return processoExclusao.toString();
	}
}
