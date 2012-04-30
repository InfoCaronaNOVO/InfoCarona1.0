package ufcg.si1.InfoCaronaMaven.Sistema;

import java.util.LinkedList;
import java.util.List;

import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.AtributoInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.AtributoInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.LoginExistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.LoginInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.OpcaoInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.UsuarioInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.UsuarioNaoPossuiVagaNaCaronaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.numeroMaximoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.CaronaInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.CaronaInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.DataInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.DestinoInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.HoraInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.IDCaronaInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.IDCaronaInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.ItemInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.OrigemInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.PontoInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.SessaoInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.SessaoInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.SolicitacaoInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.SugestaoInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.TrajetoInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.TrajetoInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.VagaInvalidaException;

public class Fachada {

	private Sistema sistema;
	private String argInvalido = "invalido";

	public Fachada() {
		this.sistema = new Sistema();
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		sistema.criarUsuario(login, senha, nome, endereco, email);
	}

	public void criarUsuario(String login, String nome, String endereco,
			String email) throws Exception {
		sistema.criarUsuario(login, argInvalido, nome, endereco, email);
	}

	public void criarUsuario(String login, String nome, String endereco)
			throws Exception {
		sistema.criarUsuario(login, argInvalido, nome, endereco, argInvalido);
	}

	public void zerarSistema() {
		sistema.zerarSistema();
	}

	public String abrirSessao(String login, String senha)
			throws LoginInvalidoException, UsuarioInexistenteException,
			LoginExistenteException, numeroMaximoException {
		return sistema.abrirSessao(login, senha);
	}

	public String getAtributoUsuario(String login, String atributo)
			throws LoginInvalidoException, AtributoInvalidoException,
			UsuarioInexistenteException, AtributoInexistenteException,
			LoginExistenteException {
		return sistema.getAtributoUsuario(login, atributo);
	}

	public void encerrarSistema() {
		sistema.encerrarSistema();
	}

	public String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, String vagas)
			throws SessaoInvalidaException, SessaoInexistenteException,
			OrigemInvalidaException, DestinoInvalidoException,
			DataInvalidaException, HoraInvalidaException, VagaInvalidaException, numeroMaximoException {
		int vaga = 0;
		try {
			vaga = Integer.parseInt(vagas);
		} catch (Exception e) {
			
		}
		return sistema.cadastrarCarona(idSessao, origem, destino, data, hora,
				vaga);
	}

	public String localizarCarona(String idSessao, String origem, String destino)
			throws OrigemInvalidaException, DestinoInvalidoException, SessaoInvalidaException, SessaoInexistenteException {
		LinkedList<String> retorno = new LinkedList<String>();
		List<Carona> listaCaronas = sistema.localizarCarona(origem, destino);
		for(Carona caronaTemp : listaCaronas){
			retorno.add(caronaTemp.getIdCarona());
		}
		return retorno.toString().replace("[", "{").replace("]", "}").replace(", ", ",");
	}

	public String getAtributoCarona(String idCarona, String atributo)
			throws ItemInexistenteException, IDCaronaInexistenteException,
			AtributoInvalidoException, AtributoInexistenteException, SessaoInvalidaException, SessaoInexistenteException, CaronaInexistenteException, CaronaInvalidaException, IDCaronaInvalidoException {
		if(ehVazioOuNull(idCarona)){
			throw new IDCaronaInvalidoException();
		}else if(ehVazioOuNull(atributo)){
			throw new AtributoInvalidoException();
		}
		String retorno = sistema.getAtributoCarona(idCarona, atributo);
		  if(retorno.equals("")){
	        	throw new AtributoInexistenteException();
	    }
		return retorno;
	}

	public String getTrajeto(String idCarona)
			throws TrajetoInexistenteException, TrajetoInvalidoException, SessaoInvalidaException, SessaoInexistenteException, CaronaInexistenteException, CaronaInvalidaException {
		if(idCarona == null){
			throw new TrajetoInvalidoException();
		}
		if(idCarona.equals("")){
			throw new TrajetoInexistenteException();
		}
		return sistema.getTrajeto(idCarona);
	}

	public String getCarona(String idCarona) throws CaronaInexistenteException,
			CaronaInvalidaException, SessaoInvalidaException, SessaoInexistenteException {
		if(idCarona == null){
			throw new CaronaInvalidaException();
		}
		if(idCarona.equals("")){
			throw new CaronaInexistenteException();
		}
		
		Carona retorno = sistema.getCarona(idCarona);
		if(retorno == null){
			throw new CaronaInexistenteException();
		}
		return retorno.toString();
	}

	public void encerrarSessao(String login) {
		sistema.encerrarSessao(login);
	}

	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws CaronaInexistenteException,
			CaronaInvalidaException, PontoInvalidoException, SessaoInvalidaException, SessaoInexistenteException, IDCaronaInvalidoException, ItemInexistenteException, numeroMaximoException {
		return sistema.sugerirPontoEncontro(idSessao, idCarona, pontos);
	}

	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos)
			throws CaronaInexistenteException, CaronaInvalidaException,
			SugestaoInexistenteException, SessaoInvalidaException, SessaoInexistenteException, IDCaronaInvalidoException, ItemInexistenteException, PontoInvalidoException {
		if(pontos.equals("")){
			throw new PontoInvalidoException();
		}
		sistema.responderSugestaoPontoEncontro(idSessao, idCarona, idSugestao,
				pontos);
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaInexistenteException,
			CaronaInvalidaException, SessaoInvalidaException, SessaoInexistenteException, IDCaronaInvalidoException, ItemInexistenteException, numeroMaximoException {
		return sistema.solicitarVagaPontoEncontro(idSessao, idCarona, ponto);
	}

	public String solicitarVaga(String idSessao, String idCarona)
			throws CaronaInexistenteException, CaronaInvalidaException, SessaoInvalidaException, SessaoInexistenteException, IDCaronaInvalidoException, ItemInexistenteException, numeroMaximoException {
		return sistema.solicitarVagaPontoEncontro(idSessao, idCarona, "Default");
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo) {
		return sistema.getAtributoSolicitacao(idSolicitacao, atributo);
	}
	public void reviewVagaEmCarona (String idSessao, String idCarona, String loginCaroneiro, String review) throws SessaoInvalidaException, SessaoInexistenteException, CaronaInexistenteException, CaronaInvalidaException, LoginInvalidoException, UsuarioInexistenteException, OpcaoInvalidaException, UsuarioNaoPossuiVagaNaCaronaException{
		sistema.reviewVagaEmCarona(idSessao, idCarona, loginCaroneiro, review);
	}
	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws SolicitacaoInexistenteException, SessaoInvalidaException, SessaoInexistenteException, VagaInvalidaException {
		sistema.aceitarSolicitacaoPontoEncontro(idSessao, idSolicitacao);
	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSugestao) throws CaronaInexistenteException,
			CaronaInvalidaException, SessaoInvalidaException, SessaoInexistenteException, IDCaronaInvalidoException, ItemInexistenteException {
		sistema.desistirRequisicao(idSessao, idCarona, idSugestao);

	}

	public String visualizarPerfil(String idSesao, String login) throws LoginInvalidoException, SessaoInvalidaException, SessaoInexistenteException, UsuarioInexistenteException {
		return sistema.visualizarPerfil(idSesao, login);
	}

	public String getAtributoPerfil(String login, String atributo) throws SessaoInvalidaException, SessaoInexistenteException, LoginInvalidoException, UsuarioInexistenteException {
		return sistema.getAtributoPerfil(login, atributo);
	}
	
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws SolicitacaoInexistenteException, SessaoInvalidaException, SessaoInexistenteException{
		sistema.rejeitarSolicitacao(idSessao, idSolicitacao);
	}
	
	public boolean ehVazioOuNull(String atributo){
		if(atributo == null || atributo.equals("")){
			return true;
		}
		return false;
	}
	
	public void reiniciarSistema(){
		sistema.reiniciarSistema();
	}
	
	public String getCaronaUsuario(String idSessao, int indexCarona) throws SessaoInvalidaException, SessaoInexistenteException{
		return sistema.getCaronaUsuario(idSessao, indexCarona);
	}
	
	public String getTodasCaronasUsuario(String idSessao) throws SessaoInvalidaException, SessaoInexistenteException{
		List<String> retorno = new LinkedList<String>(); 
		List<Carona> todasCaronas = sistema.getTodasCaronasUsuario(idSessao);
		for (Carona caronaTemp : todasCaronas) {
			retorno.add(caronaTemp.getIdCarona());
		}
		return retorno.toString().replace("[", "{").replace("]", "}").replace(", ", ",");
	}
	
	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) throws SessaoInvalidaException, SessaoInexistenteException, CaronaInexistenteException, CaronaInvalidaException{
		List<String> retorno = new LinkedList<String>(); 
		List<SolicitacaoDeVaga> todasSolicitacoes = sistema.getSolicitacoesConfirmadas(idCarona);
		for (SolicitacaoDeVaga solicitacao : todasSolicitacoes) {
			retorno.add(solicitacao.getIdSolicitacao());
		}
		return retorno.toString().replace("[", "{").replace("]", "}").replace(", ", ",");
		
	}
	
	public String getSolicitacoesPendentes(String idCarona) throws CaronaInexistenteException, CaronaInvalidaException{
		return sistema.getSolicitacoesPendentes(idCarona).toString().replace("[", "{").replace("]", "}").replace(", ", ",");
	}
	
	public String getPontosEncontro(String idSessao, String idCarona) throws CaronaInexistenteException, CaronaInvalidaException{
		return sistema.getPontosEncontro(idCarona).toString().replace(", ", ";");
	}
	
	public String getPontosSugeridos(String idSessao, String idCarona) throws CaronaInexistenteException, CaronaInvalidaException{
		return sistema.getPontosSugeridos(idCarona).toString().replace(", ", ";");
	}
}
