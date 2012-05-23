package ufcg.si1.InfoCaronaMaven.Sistema;

import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.DataInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.HoraInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.SessaoInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.VagaInvalidaException;

public class CaronaMunicipal extends CaronaAbstract {
	
	private String cidade;
	public CaronaMunicipal(String origem, String destino, String cidade, String data, String hora, int vagas, String idCarona, Usuario donoDaCarona)
			throws SessaoInvalidaException, 
			CaronaException, DataInvalidaException,
			HoraInvalidaException, VagaInvalidaException {
		super(origem, destino, data, hora, vagas, idCarona, donoDaCarona);
		this.cidade = cidade;
		super.tipoCarona = TiposCarona.MUNICIPAL;
	}

	public String getCidade() {
		return cidade;
	}
	

}
