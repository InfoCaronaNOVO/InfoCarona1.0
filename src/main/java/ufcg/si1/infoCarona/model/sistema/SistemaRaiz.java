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
import ufcg.si1.infoCarona.model.negociacao.EstadoSolicitacao;
import ufcg.si1.infoCarona.model.negociacao.SolicitacaoDeVaga;
import ufcg.si1.infoCarona.model.negociacao.SugestaoDePontoDeEncontro;
import ufcg.si1.infoCarona.model.usuario.Observer;
import ufcg.si1.infoCarona.model.usuario.Usuario;
import util.EnviarEmail;
import util.UtilInfo;

public class SistemaRaiz {
	/**
	 * Classe Sistema utilizada incialmente para a logica do sistema todos os
	 * metodos da fachada, s�o chamadas por ele.
	 */

	private ControlerRepositorio controleRepositorio;
	private Id id;
	protected static Observer observer;
	protected static Map<String, Usuario> usuariosLogados;
	
	public SistemaRaiz() {
		this.criaSistema();
	}

	private void criaSistema() {
		id = id.getInstance(5);
		controleRepositorio = new ControlerRepositorio();
		usuariosLogados = new HashMap<String, Usuario>();
		observer = new Observer();
	}

	/**
	 *  Metodo utilizado para zerar o sistema.
	 *  criando um novo sistema e limpando os dados do repositorio 
	 */
	public void zerarSistema() {
		this.criaSistema();
		controleRepositorio.zerarSistema();
	}

	
	/**
	 * Encerra o Sistema atual, salvando todos os dados em um arquivo .xml
	 */
	public void encerrarSistema() {
		usuariosLogados = new HashMap<String, Usuario>();
		controleRepositorio.encerrarSistema();		
	}

	
	protected static Usuario procuraUsuarioLogado(String idSessao)
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
	/**
	 * Metodo para o dono da carona adcionar um comentario em alguma pessoa que pegou carona com ele
	 * @param idSessao - id do usuario 
	 * @param idCarona - id da carona que ele foi dono
	 * @param loginCaroneiro - id do usuario que participou da carona
	 * @param review - comentario da carona
	 * @throws CaronaException 
	 * @throws LoggerException 
	 * @throws ArgumentoInexistenteException
	 */
	public void reviewVagaEmCarona(String idSessao, String idCarona, String loginCaroneiro, String review) throws CaronaException, LoggerException, ArgumentoInexistenteException {
		
		boolean achou = false;
		Usuario usuarioTemp2 = controleRepositorio.buscarUsuarioPorLogin(loginCaroneiro);
		
		Carona caronaTemp = controleRepositorio.localizaCaronaPorId(idCarona);
		
			for (SolicitacaoDeVaga solicitacao : usuarioTemp2.getListaDeSolicitacaoDeVagas()) {
				if (solicitacao.getCarona().getIdCarona().equals(idCarona)) {
						if(solicitacao.getEstado().equals(EstadoSolicitacao.ACEITA)){
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
	/**
	 * Metodo para reiniciar o sistema criando outro sistema com os dados salvos.
	 */
	public void reiniciarSistema() {
		this.criaSistema();
	}
	
	public static boolean usuarioJahEstahNaCarona(Usuario usuario, Carona carona){
		for (SolicitacaoDeVaga solicitacao : carona.getListaDeSolicitacao()) {
			if(solicitacao.getDonoSolicitacao().equals(usuario)){
				if(solicitacao.getEstado().equals(EstadoSolicitacao.ACEITA)){
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Metodo para enviar um email 
	 * @param idSessao - id do usuario
	 * @param destino - email destinatário
	 * @param message - mensagem do email
	 * @return - confirmação de enviou
	 * @throws ArgumentoInexistenteException
	 */
	public boolean enviarEmail(String idSessao, String destino, String message) throws ArgumentoInexistenteException{
		return EnviarEmail.sendMail(destino, destino, "Info Carona", message);
	}
}
