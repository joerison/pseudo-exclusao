package computador;

public class RecuperaArquivo {

	/* Metodo para recuperacao de arquivos */
	public void restaurarArquivo(DiscoRigido discoRigido, Arquivo arquivo) throws Exception {
		for (int i = 0; i < discoRigido.getCluster().size(); i++) {
			if (discoRigido.getCluster().get(i).getReferencia().equals(arquivo.getReferencia())) {
				discoRigido.getFat().get(i).setLocalizacao(arquivo.getReferencia());
			}
		}
	}

}
