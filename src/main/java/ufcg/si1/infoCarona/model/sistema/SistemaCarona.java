package ufcg.si1.infoCarona.model.sistema;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import ufcg.si1.infoCarona.controller.ControlerRepositorio;
import ufcg.si1.infoCarona.model.ArgumentoInexistenteException;
import ufcg.si1.infoCarona.model.Id;
import ufcg.si1.infoCarona.model.LoggerException;
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
	
	/**
	 * Metodo para localizar um objeto do tipo carona no repositorio
	 * @param origem - origem da carona a ser localizada
	 * @param destino - destino da carona a ser localizada
	 * @return - retorna da(s) carona(s) localizada(s)
	 * @throws CaronaException - referentes a problemas com a localização do objeto do tipo carona
	 */
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

	/**
	 * Metodo responsavel para retornar um atributo da carona em questão.
	 * @param idCarona - id da carona que queremos saber algum atributo
	 * @param atributo - Atributo que iremos retornar da carona
	 * @return o atributo requeridó
	 * @throws CaronaException - referentes a problemas com a localização do objeto do tipo carona
	 * @throws ArgumentoInexistenteException - quando não existe esse atributo no objeto carona
	 */
	public String getAtributoCarona(String idCarona, String atributo)
			throws CaronaException, ArgumentoInexistenteException {

		return controler.getAtributoCarona(idCarona, atributo);
	}
	
	/**
	 * Metodo para cadastrar um objeto do tipo carona no Usuario.
	 * 
	 * @param idSessao - id do usuario logado 
	 * @param origem - Origem da carona
	 * @param destino - destino da carona
	 * @param calendar - um objeto do tipo calendar com a data e hora da carona
	 * @param vagas - numero de vagas disponiveis
	 * @return - o ID da carona
	 * @throws CaronaException - referente a problemas no objeto da carona 
	 * @throws NumeroMaximoException
	 * @throws ArgumentoInexistenteException - quando um dos argumentos passados pelo Sistema não existe
	 * @throws ParseException - referente a problemas quando tentasse mudar o tipo do objeto
	 */
	public String cadastrarCarona(String idSessao, String origem,
			String destino, Calendar calendario, int vagas)
			throws CaronaException, NumeroMaximoException, ArgumentoInexistenteException, ParseException {

		Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		String idCarona = usuarioTemp.cadastrarCarona(origem, destino, calendario, vagas, id.gerarId());
		
		Carona caronaTemp = controler.localizaCaronaPorId(idCarona);
		
		SistemaRaiz.observer.cadastrouCarona(caronaTemp);
		
		return idCarona;
	}
	
	/**
	 * Metodo para receber o trajeto de uma Carona
	 * @param idCarona - Id da carona que queremos saber o projeto
	 * @return - retorna o trajeto da carona da forma: Origem - Destino
	 * @throws CaronaException - referentes a problemas com a localização do objeto do tipo carona
	 * @throws ArgumentoInexistenteException - quando for passado um id de carona vazio.
	 */
	public String getTrajeto(String idCarona)
			throws
			CaronaException, ArgumentoInexistenteException {

		return controler.getTrajeto(idCarona);
	}
	/**
	 * Metodo para retornar uma carona dentre todas caronas criadas do usuario
	 * @param idSessao - id do usuario
	 * @param indexCarona - a posição da carona dentro da lista de caronas do usuario
	 * @return - id da carona
	 * @throws ArgumentoInexistenteException
	 */
	public String getCaronaUsuario(String idSessao, int indexCarona) throws ArgumentoInexistenteException {
		Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		return usuarioTemp.getCaronas().get(indexCarona - 1).getIdCarona();
	}
	
	/**
	 * Metodo que retorna todas as caronas do usuario em questão
	 * @param idSessao - id do usuario logado
	 * @return - a lista de caronas do usuario
	 * @throws ArgumentoInexistenteException - quando não existe esta id
	 */
	public List<Carona> getTodasCaronasUsuario(String idSessao) throws ArgumentoInexistenteException {
		Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		return usuarioTemp.getCaronas();
	}
	
	/**
	 * Metodo que cadastra uma carona municipal
	 * @param idSessao - id do usuario logado que esta cadastrando
	 * @param origem - origem da carona
	 * @param destino - destino da carona
	 * @param cidade - cidade da carona
	 * @param calendario - objeto do tipo calendar com a data e hora da carona
	 * @param vagas - numero de vagas disponiveis
	 * @return id da carona
	 * @throws CaronaException
	 * @throws NumeroMaximoException
	 * @throws ArgumentoInexistenteException quando a id da sessão for invalida 
	 */
	public String cadastrarCaronaMunicipal(String idSessao, String origem, String destino, String cidade, Calendar calendario, int vagas)
			throws CaronaException, NumeroMaximoException, ArgumentoInexistenteException {

			Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		String idCarona = usuarioTemp.cadastrarCaronaMunicipal(origem, destino, cidade, calendario, vagas, id.gerarId());
		
		return idCarona;
	}

	/**
	 * Metodo para localizar uma carona municipal
	 * @param cidade - Cidade da carona municipal
	 * @param origem - Origem da carona municipal
	 * @param destino - Destino da carona municipal
	 * @return - retorno da(s) carona(s) localizada(s)
	 * @throws CaronaException - referentes a problemas com a localização do objeto do tipo carona
	 */
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
	
	/**
	 * Metodo para o usuario adcionar um comentario em uma carona que o mesmo participou
	 * @param idSessao - id do usuario
	 * @param idCarona - id da carona que sera posta um comentario
	 * @param review - comentario
	 * @throws CaronaException - quando o usuario não possuio vaga nesta carona
	 * @throws LoggerException
	 * @throws ArgumentoInexistenteException - quando o comentario for diferente dos comentarios aceitos pelo sistema
	 */
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
