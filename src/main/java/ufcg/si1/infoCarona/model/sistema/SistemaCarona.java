package ufcg.si1.infoCarona.model.sistema;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import ufcg.si1.infoCarona.model.ArgumentoInexistenteException;
import ufcg.si1.infoCarona.model.NumeroMaximoException;
import ufcg.si1.infoCarona.model.carona.Carona;
import ufcg.si1.infoCarona.model.carona.CaronaException;
import ufcg.si1.infoCarona.model.usuario.Usuario;

public class SistemaCarona {

	private SistemaRaiz sistema;

	public SistemaCarona() {
		sistema = SistemaRaiz.getInstance();
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

		return sistema.controleRepositorio.localizarCarona(origem, destino);
	}

	public String getAtributoCarona(String idCarona, String atributo)
			throws CaronaException, ArgumentoInexistenteException {

		return sistema.controleRepositorio.getAtributoCarona(idCarona, atributo);
	}
	
	public String cadastrarCarona(String idSessao, String origem,
			String destino, Calendar calendario, int vagas)
			throws CaronaException, NumeroMaximoException, CaronaException, ArgumentoInexistenteException, ParseException {

		Usuario usuarioTemp = sistema.procuraUsuarioLogado(idSessao);
		String idCarona = usuarioTemp.cadastrarCarona(origem, destino, calendario, vagas, sistema.id.gerarId());
		
		Carona caronaTemp = sistema.controleRepositorio.localizaCaronaPorId(idCarona);
		sistema.enviaMsgAInteressadosEmCarona(caronaTemp);
		
		return idCarona;
	}
	
	public String getTrajeto(String idCarona)
			throws
			CaronaException, ArgumentoInexistenteException {

		return sistema.controleRepositorio.getTrajeto(idCarona);
	}
	
	public String getCaronaUsuario(String idSessao, int indexCarona) throws ArgumentoInexistenteException {
		Usuario usuarioTemp = sistema.procuraUsuarioLogado(idSessao);
		return usuarioTemp.getCaronas().get(indexCarona - 1).getIdCarona();
	}
	
	public List<Carona> getTodasCaronasUsuario(String idSessao) throws ArgumentoInexistenteException {
		Usuario usuarioTemp = sistema.procuraUsuarioLogado(idSessao);
		return usuarioTemp.getCaronas();
	}
	
	public String cadastrarCaronaMunicipal(String idSessao, String origem, String destino, String cidade, Calendar calendario, int vagas)
			throws CaronaException, NumeroMaximoException, ArgumentoInexistenteException {

			Usuario usuarioTemp = sistema.procuraUsuarioLogado(idSessao);
		String idCarona = usuarioTemp.cadastrarCaronaMunicipal(origem, destino, cidade, calendario, vagas, sistema.id.gerarId());

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

		return sistema.controleRepositorio.localizarCaronaMunicipal(cidade, origem, destino);
	}
	
	public void reviewCarona(String idSessao, String idCarona, String review) throws CaronaException, ArgumentoInexistenteException {
		Usuario usuarioTemp = sistema.procuraUsuarioLogado(idSessao);
		Carona caronaTemp = sistema.controleRepositorio.localizaCaronaPorId(idCarona);
		if( !((review.equals("segura e tranquila")) || (review.equals("não funcionou")))){
			throw new IllegalArgumentException("Opção inválida.");
		}
		if(sistema.usuarioJahEstahNaCarona(usuarioTemp, caronaTemp)){
			caronaTemp.addReviewCarona(usuarioTemp, review);
		}else{
			throw new CaronaException("Usuário não possui vaga na carona.");
		}	
	}
}
