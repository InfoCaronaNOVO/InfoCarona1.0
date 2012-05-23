package ufcg.si1.InfoCaronaMaven.Sistema;


public class CaronaMunicipal extends CaronaAbstract {
	
	private String cidade;
	public CaronaMunicipal(String origem, String destino, String cidade, String data, String hora, int vagas, String idCarona, Usuario donoDaCarona)
			throws CaronaException {
		super(origem, destino, data, hora, vagas, idCarona, donoDaCarona);
		this.cidade = cidade;
		super.tipoCarona = TiposCarona.MUNICIPAL;
	}

	public String getCidade() {
		return cidade;
	}
	

}
