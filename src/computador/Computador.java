package computador;

import java.util.Date;
import java.util.Random;

import javax.swing.DefaultListModel;

public class Computador {

	private static DiscoRigido hd = new DiscoRigido();

	public DiscoRigido getHd() {
		return hd;
	}

	public void setHd(DiscoRigido hd) {
		Computador.hd = hd;
	}

	public static String geraRefeferencia() {
		Random gerador = new Random();
		int codigo;
		codigo = gerador.nextInt(26) + 65;
		char c1 = (char) (codigo);
		codigo = gerador.nextInt(26) + 65;
		char c2 = (char) (codigo);
		return c1 + "" + (gerador.nextInt(1000) + 1000) + "" + c2;
	}

	public void criarArquivo(String nome, String conteudo) {
		Arquivo arquivo = new Arquivo();
		arquivo.setReferencia(geraRefeferencia());
		arquivo.setDataDeCriacao(new Date());
		arquivo.setNome(nome);
		arquivo.setConteudo(conteudo);
		arquivo.setTamanho(conteudo.length());
		hd.salvarArquivo(arquivo);
	}

	public DefaultListModel<String> mostrarConteudoDoDisco() {
		DefaultListModel<String> arquivosNoDisco = new DefaultListModel<String>();

		int z = 0;
		for (int i = 0; i < hd.getFat().size(); i++) {
			for (int y = 0; y < hd.getCluster().size(); y++) {
				if (hd.getFat().get(i).getLocalizacao().equals(hd.getCluster().get(y).getReferencia())) {

					arquivosNoDisco.addElement(z + 1 + " | Arquivo: " + hd.getCluster().get(y).getNome());
					z++;
				}
			}
		}
		return arquivosNoDisco;
	}

	public String exibirDetalhesArquivo(int obj) {
		StringBuilder detalhesArquivo = new StringBuilder();
		Arquivo arquivo = null;
		int z = 0;

		for (int i = 0; i < hd.getFat().size(); i++) {
			for (int y = 0; y < hd.getCluster().size(); y++) {
				if (hd.getFat().get(i).getLocalizacao().equals(hd.getCluster().get(y).getReferencia())) {
					if (z == (obj)) {
						arquivo = hd.getCluster().get(i);
					}
					z++;
				}
			}
		}

		detalhesArquivo.append("---------------------------------------------" + "\n");
		detalhesArquivo.append("Nome: " + arquivo.getNome() + "\n");
		detalhesArquivo.append("Data Criacao: " + arquivo.getDataDeCriacao() + "\n");
		detalhesArquivo.append("Tamanho: " + arquivo.getTamanho() + " byte (s)" + "\n");
		detalhesArquivo.append("Conteudo: " + arquivo.getConteudo() + "\n");
		detalhesArquivo.append("Referência: " + arquivo.getReferencia() + "\n");
		detalhesArquivo.append("---------------------------------------------" + "\n");
		return detalhesArquivo.toString();
	}

	public String excluirArquivo(MetodoExclusao metodo, int arquivoParaExclusao) throws Exception {
		Arquivo arquivo = null;
		int z = 0;
		for (int i = 0; i < hd.getFat().size(); i++) {
			for (int y = 0; y < hd.getCluster().size(); y++) {
				if (hd.getFat().get(i).getLocalizacao().equals(hd.getCluster().get(y).getReferencia())) {
					if (z == (arquivoParaExclusao)) {
						arquivo = hd.getCluster().get(i);
					}
					z++;
				}
			}
		}

		if (metodo.equals(MetodoExclusao.NATIVA)) {
			String nomeArquivo = arquivo.getNome();
			hd.removeArquivo(arquivo);
			return nomeArquivo + " excluido com sucesso";
		} else if (metodo.equals(MetodoExclusao.SHRED)) {
			StringBuilder resultado = new StringBuilder();
			Shred shred = new Shred();
			String nomeArquivo = arquivo.getNome();
			resultado.append(shred.u(hd, arquivo));
			resultado.append("Arquivo: " + nomeArquivo + " excluido com sucesso");
			return resultado.toString();
		}
		return null;
	}

	public DefaultListModel<String> exibirParaRecuperar() {
		DefaultListModel<String> arquivoParaRecuperar = new DefaultListModel<String>();
		int z = 0;
		for (int i = 0; i < hd.getCluster().size(); i++) {
			if (hd.getFat().get(i).getLocalizacao().equals("null")) {
				arquivoParaRecuperar.addElement(z + 1 + " | Arquivo: " + hd.getCluster().get(i).getNome()
						+ " - Referencia: " + hd.getFat().get(i).getLocalizacao() + "\n");
				z++;
			}
		}
		return arquivoParaRecuperar;
	}

	public Arquivo recuperarArquivo(int obj) throws Exception {
		Arquivo arquivo = null;
		int z = 0;
		RecuperaArquivo recupera = new RecuperaArquivo();
		for (int i = 0; i < hd.getCluster().size(); i++) {
			if (hd.getFat().get(i).getLocalizacao().equals("null")) {
				if (z == obj) {
					arquivo = hd.getCluster().get(i);
				}
				z++;
			}
		}
		recupera.restaurarArquivo(hd, arquivo);
		return arquivo;
	}

}
