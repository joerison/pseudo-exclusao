package computador;

import java.util.ArrayList;

public class DiscoRigido {

	private ArrayList<Arquivo> cluster = new ArrayList<Arquivo>();
	private ArrayList<Endereco> fat = new ArrayList<Endereco>();

	public void salvarArquivo(Arquivo arquivo) {
		Endereco dado = new Endereco();
		cluster.add(arquivo);
		dado.setArquivo(arquivo);
		dado.setLocalizacao(arquivo.getReferencia());
		fat.add(dado);
	}

	public void recuperaArquivo(Arquivo arquivo) {
		cluster.add(arquivo);
	}

	public ArrayList<Endereco> getFat() {
		return fat;
	}

	public void setFat(ArrayList<Endereco> fat) {
		this.fat = fat;
	}

	public void removeArquivo(Arquivo arquivo) {
		for (int i = 0; i < fat.size(); i++) {
			if (fat.get(i).getLocalizacao().equals(arquivo.getReferencia())) {
				fat.get(i).setLocalizacao("null");
			}
		}
	}

	public ArrayList<Arquivo> getCluster() {
		return cluster;
	}

	public void setCluster(ArrayList<Arquivo> cluster) {
		this.cluster = cluster;
	}

}
