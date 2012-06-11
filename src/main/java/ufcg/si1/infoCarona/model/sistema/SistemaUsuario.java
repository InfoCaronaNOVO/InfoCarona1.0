package ufcg.si1.infoCarona.model.sistema;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ufcg.si1.infoCarona.controller.ControlerRepositorio;
import ufcg.si1.infoCarona.model.ArgumentoInexistenteException;
import ufcg.si1.infoCarona.model.Id;
import ufcg.si1.infoCarona.model.LoggerException;
import ufcg.si1.infoCarona.model.NumeroMaximoException;
import ufcg.si1.infoCarona.model.carona.Carona;
import ufcg.si1.infoCarona.model.carona.CaronaException;
import ufcg.si1.infoCarona.model.negociacao.SolicitacaoDeVaga;
import ufcg.si1.infoCarona.model.usuario.Usuario;
import util.UtilInfo;

public class SistemaUsuario {
	
	private Id id;
	private ControlerRepositorio controler;
	
	public SistemaUsuario(){
		id = Id.getInstance(5);
		controler = new ControlerRepositorio();
	}
	
	/**
	 * Metodo que encerra a sessão atual do usuario, removendo-o do mapa de usuarios logados. 
	 * @param login - login do usuario
	 */
	public void encerrarSessao(String login) {
		Collection<String> listaUsuariosLogados = SistemaRaiz.usuariosLogados.keySet();
		Usuario removeUsuario = null;
		for (String idSessao : listaUsuariosLogados) {
			if (SistemaRaiz.usuariosLogados.get(idSessao).getLogin().equals(login)) {
				removeUsuario = SistemaRaiz.usuariosLogados.get(idSessao);
				break;
			}
		}
		listaUsuariosLogados.remove(removeUsuario);
	}
	
	/**
	 * Metodo para retorna um atributo de um objeto do tipo usuario
	 * @param login - Login do usuario
	 * @param atributo - Nome do atributo que queremos saber
	 * @return - Retorna o atributo requerido
	 * @throws LoggerException - referentes a problemas com a localização do objeto do usuario cadastrado
	 * @throws ArgumentoInexistenteException - caso os argumentos passados pelo usuario, seja invalido
	 */
	public String getAtributoUsuario(String login, String atributo)
			throws LoggerException, ArgumentoInexistenteException {

		String retorno = "";

		if (!UtilInfo.checaLogin(login)) {
			throw new LoggerException("Login inválido");
		}

		if (UtilInfo.checaAtributo(atributo)) {
			throw new IllegalArgumentException("Atributo inválido");
		}
		if (!UtilInfo.checaAtributoValido(atributo)) {
			throw new ArgumentoInexistenteException("Atributo inexistente");
		}

		retorno = controler.getAtributoUsuario(login, atributo);

		return retorno;
	}
	
	/**
	 * Metodo para retorna um atributo de um usuario
	 * @param login - login do usuario que queremos saber o atributo
	 * @param atributo - o atributo procurado
	 * @return - o atributo procurado
	 * @throws LoggerException
	 * @throws ArgumentoInexistenteException
	 */
	public String getAtributoPerfil(String login, String atributo)
			throws
			LoggerException, ArgumentoInexistenteException {
		
		if (!atributo.equals("historico de vagas em caronas")) {
			return controler.getAtributoUsuario(login, atributo);
		}
		Usuario usuarioTemp = controler.buscarUsuarioPorLogin(login);
			List<String> lista = new LinkedList<String>();
			for (Carona caronaTemp : usuarioTemp.getSolicitacaoAceitas()) {
				lista.add(caronaTemp.getIdCarona());				
			}
			if(lista.size() == 0){
				return "";
			}
			return lista.toString().replace("{", "[").replace("}", "]").replace(", ", ",");
			
		}
	
	/**
	 * Metodo para que um usuario cadastre um interesse sobre uma carona que esteja procurando em um intervalo de tempo
	 * @param idSessao - id do usuario que irá cadastrar este interesse
	 * @param origem - origem da carona que esta interessado
	 * @param destino - destino da carona que esta interessado
	 * @param calendarioInicial - dia e e horario do inicial do interesse em pegar uma carona
	 * @param calendarioFinal - dia e horario do final do interesse em pegar uma carona
	 * @param caronaEhNoDia - boolean pra saber se a carona que busca é no dia que esta interessado
	 * @return - id do interesse cadastrado 
	 * @throws NumeroMaximoException
	 * @throws CaronaException
	 * @throws ArgumentoInexistenteException - data invalida
	 */
	public String cadastrarInteresse(String idSessao, String origem, String destino, Calendar calendarioInicial, Calendar calendarioFinal, boolean caronaEhNoDia) throws NumeroMaximoException, CaronaException, ArgumentoInexistenteException {
		Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		String idInteresse = usuarioTemp.cadastrarInteresse(origem, destino, calendarioInicial, calendarioFinal, id.gerarId(), caronaEhNoDia);
		
		SistemaRaiz.observer.addInteressado(usuarioTemp);
		return idInteresse;
		
	}
	
	/**
	 * Metodo para verificar mensagens não lidas pelo usuario
	 * @param idSessao - id do usuario
	 * @return retorna uma lsita de mensagens do usuario
	 * @throws ArgumentoInexistenteException - sessão invalida
	 */
	public List<String> verificarMensagensPerfil(String idSessao) throws ArgumentoInexistenteException {
		return SistemaRaiz.procuraUsuarioLogado(idSessao).getListaDeMensagens();
	}
	
	/**
	 * Metodo para visualização do perfil de um usuario
	 * @param idSesao - id do usuario logado
	 * @param login - login do usuario a ser vizualizado perfil
	 * @return - nome e login do usuario solicitado
	 * @throws LoggerException
	 * @throws ArgumentoInexistenteException
	 */
	public String visualizarPerfil(String idSessao, String login)
			throws LoggerException, ArgumentoInexistenteException {
		Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		Usuario usuarioProcurado = null;
		try {
			usuarioProcurado = controler.buscarUsuarioPorLogin(login);
		} catch (Exception e) {
			throw new LoggerException("Login inválido");
		}

		return usuarioTemp.visualizarPerfil(usuarioProcurado);
	}
}
