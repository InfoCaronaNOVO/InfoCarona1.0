package ufcg.si1.infoCarona.model.carona;

import java.util.Calendar;

import ufcg.si1.infoCarona.model.usuario.Usuario;


public class CaronaMunicipal extends CaronaAbstract {
	
	private String cidade;
	public CaronaMunicipal(String origem, String destino, String cidade, Calendar calendario, int vagas, String idCarona, Usuario donoDaCarona)
			throws CaronaException {
		super(origem, destino, calendario, vagas, idCarona, donoDaCarona);
		this.cidade = cidade;
		super.tipoCarona = TiposCarona.MUNICIPAL;
	}

	public String getCidade() {
		return cidade;
	}
}
