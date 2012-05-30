package ufcg.si1.infoCarona.model.sistema;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ufcg.si1.infoCarona.controller.ControlerRepositorio;
import ufcg.si1.infoCarona.model.ArgumentoInexistenteException;
import ufcg.si1.infoCarona.model.Id;
import ufcg.si1.infoCarona.model.LoggerException;
import ufcg.si1.infoCarona.model.NumeroMaximoException;
import ufcg.si1.infoCarona.model.carona.Carona;
import ufcg.si1.infoCarona.model.carona.CaronaException;
import ufcg.si1.infoCarona.model.negociacao.SolicitacaoDeVaga;
import ufcg.si1.infoCarona.model.negociacao.SugestaoDePontoDeEncontro;
import ufcg.si1.infoCarona.model.usuario.Usuario;
import util.EnviarEmail;
import util.UtilInfo;

public class SistemaRaiz {
	/**
	 * Classe Sistema utilizada incialmente para a logica do sistema todos os
	 * metodos da fachada, s�o chamadas por ele.
	 */

	public ControlerRepositorio controleRepositorio;
	public Id id;
	public Map<String, Usuario> usuariosLogados;
	
	private static SistemaRaiz instance;
	
	protected SistemaRaiz() {
		id = new Id(5);
		this.criaSistema();
	}
	
	public static SistemaRaiz getInstance(){
		if (instance == null){
			instance = new SistemaRaiz();
		}
		return instance;
	}

	private void criaSistema() {
		controleRepositorio = new ControlerRepositorio();
		usuariosLogados = new HashMap<String, Usuario>();
	}

	/**
	 * Metodo para Zerar as configura��es do Usuario
	 */
	public void zerarSistema() {
		this.criaSistema();
		controleRepositorio.zerarSistema();
	}

	/**
	 * criarUsuario metodo para criar um novo objeto da Classe Usuario.
	 * 
	 * @param login
	 *            - login a ser cadastrado no usuario.
	 * @param senha
	 *            - senha de acesso do usuario.
	 * @param nome
	 *            - nome do usuario a ser cadastrado.
	 * @param endereco
	 *            - endereco do usuario.
	 * @param email
	 *            - email do usuario
	 * @throws EnderecoInvalidoException
	 * @throws SenhaInvalidoException
	 * @throws LoginInvalidoException
	 * @throws NomeInvalidoException
	 * @throws EmailInvalidoException
	 * @throws LoginExistenteException
	 * @throws EmailExistenteException
	 * 
	 * */
	public Carona getCarona(String idCarona) throws CaronaException {
		if (UtilInfo.ehVazioOuNull(idCarona)) {
			throw new CaronaException("Carona Inválida");
		}
		return controleRepositorio.localizaCaronaPorId(idCarona);
	}

	public void encerrarSistema() {
		usuariosLogados = new HashMap<String, Usuario>();
		controleRepositorio.encerrarSistema();		
	}

	
	protected Usuario procuraUsuarioLogado(String idSessao)
			throws ArgumentoInexistenteException {
		Usuario retorno = null;
		if (!UtilInfo.checaIdSessao(idSessao)) {
			throw new IllegalArgumentException("Sessão inválida");
		}

		retorno = usuariosLogados.get(idSessao);

		if (retorno == null) {
			throw new ArgumentoInexistenteException("Sessão inexistente");
		}

		return retorno;
	}

	public void reviewVagaEmCarona(String idSessao, String idCarona, String loginCaroneiro, String review) throws CaronaException, LoggerException, ArgumentoInexistenteException {
		
		boolean achou = false;
		Usuario usuarioTemp2 = controleRepositorio.buscarUsuarioPorLogin(loginCaroneiro);
		
		Carona caronaTemp = controleRepositorio.localizaCaronaPorId(idCarona);
		
			for (SolicitacaoDeVaga solicitacao : usuarioTemp2.getListaDeSolicitacaoDeVagas()) {
				if (solicitacao.getCarona().getIdCarona().equals(idCarona)) {
						if(solicitacao.isSolicitacaoAceita()){
							if (!(review.equals("não faltou") || review.equals("faltou"))) {
								throw new IllegalArgumentException("Opção inválida.");
							}
							usuarioTemp2.adicionaReview(caronaTemp, review);
							achou = true;
						}
				}
			}
			if(!achou){
				throw new CaronaException("Usuário não possui vaga na carona.");
			}
	}

	public void reiniciarSistema() {
		this.criaSistema();
	}
	
	public boolean usuarioJahEstahNaCarona(Usuario usuario, Carona carona){
		for (SolicitacaoDeVaga solicitacao : carona.getListaDeSolicitacao()) {
			if(solicitacao.getDonoSolicitacao().equals(usuario)){
				if(solicitacao.isSolicitacaoAceita()){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean enviarEmail(String idSessao, String destino, String message) throws ArgumentoInexistenteException{
		return EnviarEmail.sendMail(destino, destino, "Info Carona", message);
	}
	
	public void enviaMsgAInteressadosEmCarona(Carona carona){
		List<Usuario> listaDeInteressados = controleRepositorio.localizaInteressados(carona);
		for (Usuario usuario : listaDeInteressados) {
			String novaMensagem = "Carona cadastrada no dia " + UtilInfo.converteCalendarEmStringData(carona.getCalendario()) + ", às " + UtilInfo.converteCalendarEmStringHora(carona.getCalendario()) + " de acordo com os seus interesses registrados. Entrar em contato com " + carona.getDonoDaCarona().getEmail();
			usuario.addMensagen(novaMensagem);
		}
	}
}