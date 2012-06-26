package ufcg.si1.infoCarona.model.sistema;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import ufcg.si1.infoCarona.controller.ControlerRepositorio;
import ufcg.si1.infoCarona.model.ArgumentoInexistenteException;
import ufcg.si1.infoCarona.model.Id;
import ufcg.si1.infoCarona.model.NumeroMaximoException;
import ufcg.si1.infoCarona.model.carona.Carona;
import ufcg.si1.infoCarona.model.carona.CaronaException;
import ufcg.si1.infoCarona.model.usuario.Usuario;

public class SistemaCarona {
	
	private Id id;
	private ControlerRepositorio controler;
	
	public SistemaCarona() {
		id = Id.getInstance(5);
		controler = new ControlerRepositorio();
	}

	public List<Carona> localizarCarona(String origem, String destino)
			throws CaronaException {

		if ((origem == null)
				|| (origem.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%�&*0-9].*"))) {
			throw new CaronaException("Origem inválida");
		}
		if ((destino == null)
				|| (destino
						.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%�&*0-9].*"))) {
			throw new CaronaException("Destino inválido");
		}

		return controler.localizarCarona(origem, destino);
	}

	public String getAtributoCarona(String idCarona, String atributo)
			throws CaronaException, ArgumentoInexistenteException {

		return controler.getAtributoCarona(idCarona, atributo);
	}
	
	public String cadastrarCarona(String idSessao, String origem,
			String destino, Calendar calendario, int vagas)
			throws CaronaException, NumeroMaximoException, CaronaException, ArgumentoInexistenteException, ParseException {

		Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		String idCarona = usuarioTemp.cadastrarCarona(origem, destino, calendario, vagas, id.gerarId());
		
		Carona caronaTemp = controler.localizaCaronaPorId(idCarona);
		
		SistemaRaiz.observer.cadastrouCarona(caronaTemp);
		
		return idCarona;
	}
	
	public String getTrajeto(String idCarona)
			throws
			CaronaException, ArgumentoInexistenteException {

		return controler.getTrajeto(idCarona);
	}
	
	public String getCaronaUsuario(String idSessao, int indexCarona) throws ArgumentoInexistenteException {
		Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		return usuarioTemp.getCaronas().get(indexCarona - 1).getIdCarona();
	}
	
	public List<Carona> getTodasCaronasUsuario(String idSessao) throws ArgumentoInexistenteException {
		Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		return usuarioTemp.getCaronas();
	}
	
	public String cadastrarCaronaMunicipal(String idSessao, String origem, String destino, String cidade, Calendar calendario, int vagas)
			throws CaronaException, NumeroMaximoException, ArgumentoInexistenteException {

			Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		String idCarona = usuarioTemp.cadastrarCaronaMunicipal(origem, destino, cidade, calendario, vagas, id.gerarId());
		
		return idCarona;
	}

	public List<Carona> localizarCaronaMunicipal(String cidade, String origem, String destino) throws CaronaException {
		if ((cidade == null)
				|| (cidade.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%�&*0-9].*"))) {
			throw new CaronaException("Cidade inexistente");
		}
		if ((origem == null)
				|| (origem.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%�&*0-9].*"))) {
			throw new CaronaException("Origem inválida");
		}
		if ((destino == null)
				|| (destino
						.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%�&*0-9].*"))) {
			throw new CaronaException("Destino inválido");
		}

		return controler.localizarCaronaMunicipal(cidade, origem, destino);
	}
	
	public void reviewCarona(String idSessao, String idCarona, String review) throws CaronaException, ArgumentoInexistenteException {
		Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		Carona caronaTemp = controler.localizaCaronaPorId(idCarona);
		if( !((review.equals("segura e tranquila")) || (review.equals("não funcionou")))){
			throw new IllegalArgumentException("Opção inválida.");
		}
		if(SistemaRaiz.usuarioJahEstahNaCarona(usuarioTemp, caronaTemp)){
			caronaTemp.addReviewCarona(usuarioTemp, review);
		}else{
			throw new CaronaException("Usuário não possui vaga na carona.");
		}	
	}
}
