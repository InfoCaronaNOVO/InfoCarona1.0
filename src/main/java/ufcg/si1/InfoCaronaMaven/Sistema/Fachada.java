package ufcg.si1.InfoCaronaMaven.Sistema;

import java.util.LinkedList;
import java.util.List;

import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.AtributoInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.NumeroMaximoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.UsuarioInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.UsuarioNaoPossuiVagaNaCaronaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.IDCaronaInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.ItemInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.SessaoInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.SolicitacaoInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.SugestaoInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.TrajetoInexistenteException;

public class Fachada {

	private Sistema sistema;

	public Fachada() {
		this.sistema = new Sistema();
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		sistema.criarUsuario(login, senha, nome, endereco, email);
	}

	public void zerarSistema() {
		sistema.zerarSistema();
	}

	public String abrirSessao(String login, String senha)
			throws LoggerException, UsuarioInexistenteException, NumeroMaximoException {
		return sistema.abrirSessao(login, senha);
	}

	public String getAtributoUsuario(String login, String atributo)
			throws LoggerException,
			UsuarioInexistenteException, AtributoInexistenteException {
		return sistema.getAtributoUsuario(login, atributo);
	}

	public void encerrarSistema() {
		sistema.encerrarSistema();
	}

	public String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, String vagas)
			throws SessaoInexistenteException, CaronaException,
			 NumeroMaximoException, CaronaException {
		int vaga = 0;
		try {
			vaga = Integer.parseInt(vagas);
		} catch (Exception e) {
			throw new IllegalArgumentException("Vaga inválida");
		}
		return sistema.cadastrarCarona(idSessao, origem, destino, data, hora,
				vaga);
	}

	public String localizarCarona(String idSessao, String origem, String destino)
			throws CaronaException, SessaoInexistenteException {
		LinkedList<String> retorno = new LinkedList<String>();
		List<Carona> listaCaronas = sistema.localizarCarona(origem, destino);
		for(Carona caronaTemp : listaCaronas){
			retorno.add(caronaTemp.getIdCarona());
		}
		return retorno.toString().replace("[", "{").replace("]", "}").replace(", ", ",");
	}
	
	
	public String localizarCaronaMunicipal(String idSessao, String cidade, String origem, String destino)
			throws CaronaException, SessaoInexistenteException, CaronaException {
		LinkedList<String> retorno = new LinkedList<String>();
		List<Carona> listaCaronas = sistema.localizarCaronaMunicipal(cidade, origem, destino);
		for(Carona caronaTemp : listaCaronas){
			retorno.add(caronaTemp.getIdCarona());
		}
		return retorno.toString().replace("[", "{").replace("]", "}").replace(", ", ",");
	}
	
	public String localizarCaronaMunicipal(String idSessao, String cidade) throws SessaoInexistenteException, CaronaException{
		return localizarCaronaMunicipal(idSessao, cidade, "", "");
	}
	
	public String localizarCaronaMunicipal(String idSessao, String origem, String destino) throws SessaoInexistenteException, CaronaException{
		return localizarCaronaMunicipal(idSessao, "", origem, destino);
	}

	public String getAtributoCarona(String idCarona, String atributo)
			throws ItemInexistenteException, IDCaronaInexistenteException, AtributoInexistenteException, SessaoInexistenteException, CaronaException {
		String retorno = null;
		if(ehVazioOuNull(idCarona)){
			throw new IllegalArgumentException("Identificador do carona é inválido");
		}else if(ehVazioOuNull(atributo)){
			throw new IllegalArgumentException("Atributo inválido");
		}
	
		retorno = sistema.getAtributoCarona(idCarona, atributo);
		
		if(retorno.equals("")){
	      	throw new AtributoInexistenteException();
	    }
		return retorno;
	}

	public String getTrajeto(String idCarona)
			throws TrajetoInexistenteException, SessaoInexistenteException, CaronaException {
		if(idCarona == null){
			throw new IllegalArgumentException("Trajeto Inválida");
		}
		if(idCarona.equals("")){
			throw new TrajetoInexistenteException();
		}
		return sistema.getTrajeto(idCarona);
	}

	public String getCarona(String idCarona) throws CaronaException, SessaoInexistenteException {
		if(idCarona == null){
			throw new CaronaException("Carona Inválida");
		}
		if(idCarona.equals("")){
			throw new CaronaException("Carona Inexistente");
		}
		
		Carona retorno = sistema.getCarona(idCarona);
		if(retorno == null){
			throw new CaronaException("Carona Inexistente");
		}
		return retorno.toString();
	}

	public void encerrarSessao(String login) {
		sistema.encerrarSessao(login);
	}

	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws CaronaException, SessaoInexistenteException, ItemInexistenteException, NumeroMaximoException {
		return sistema.sugerirPontoEncontro(idSessao, idCarona, pontos);
	}

	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos)
			throws CaronaException,
			SugestaoInexistenteException, SessaoInexistenteException, ItemInexistenteException{
		if(pontos.equals("")){
			throw new IllegalArgumentException("Ponto Inválido");
		}
		sistema.responderSugestaoPontoEncontro(idSessao, idCarona, idSugestao,
				pontos);
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaException, SessaoInexistenteException, ItemInexistenteException, NumeroMaximoException  {
		return sistema.solicitarVagaPontoEncontro(idSessao, idCarona, ponto);
	}

	public String solicitarVaga(String idSessao, String idCarona)
			throws CaronaException, SessaoInexistenteException, ItemInexistenteException, NumeroMaximoException  {
		return sistema.solicitarVagaPontoEncontro(idSessao, idCarona, "Default");
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo) {
		return sistema.getAtributoSolicitacao(idSolicitacao, atributo);
	}
	public void reviewVagaEmCarona (String idSessao, String idCarona, String loginCaroneiro, String review) throws SessaoInexistenteException, CaronaException, LoggerException, UsuarioInexistenteException, UsuarioNaoPossuiVagaNaCaronaException{
		sistema.reviewVagaEmCarona(idSessao, idCarona, loginCaroneiro, review);
	}
	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws SolicitacaoInexistenteException, SessaoInexistenteException {
		sistema.aceitarSolicitacaoPontoEncontro(idSessao, idSolicitacao);
	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSugestao) throws CaronaException, SessaoInexistenteException, ItemInexistenteException {
		sistema.desistirRequisicao(idSessao, idCarona, idSugestao);

	}

	public String visualizarPerfil(String idSesao, String login) throws LoggerException, SessaoInexistenteException, UsuarioInexistenteException {
		return sistema.visualizarPerfil(idSesao, login);
	}

	public String getAtributoPerfil(String login, String atributo) throws SessaoInexistenteException, LoggerException, UsuarioInexistenteException {
		String retorno = sistema.getAtributoPerfil(login, atributo);
		if(retorno.equals(""))
			return "[]";
		return retorno;
	}
	
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws SolicitacaoInexistenteException, SessaoInexistenteException{
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
	
	public String getCaronaUsuario(String idSessao, int indexCarona) throws  SessaoInexistenteException{
		return sistema.getCaronaUsuario(idSessao, indexCarona);
	}
	
	public String getTodasCaronasUsuario(String idSessao) throws SessaoInexistenteException{
		List<String> retorno = new LinkedList<String>(); 
		List<Carona> todasCaronas = sistema.getTodasCaronasUsuario(idSessao);
		for (Carona caronaTemp : todasCaronas) {
			retorno.add(caronaTemp.getIdCarona());
		}
		return retorno.toString().replace("[", "{").replace("]", "}").replace(", ", ",");
	}
	
	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) throws SessaoInexistenteException, CaronaException{
		List<String> retorno = new LinkedList<String>(); 
		List<SolicitacaoDeVaga> todasSolicitacoes = sistema.getSolicitacoesConfirmadas(idCarona);
		for (SolicitacaoDeVaga solicitacao : todasSolicitacoes) {
			retorno.add(solicitacao.getIdSolicitacao());
		}
		return retorno.toString().replace("[", "{").replace("]", "}").replace(", ", ",");
		
	}
	
	public String getSolicitacoesPendentes(String idSessao, String idCarona) throws CaronaException{
		return sistema.getSolicitacoesPendentes(idCarona).toString().replace("[", "{").replace("]", "}").replace(", ", ",");
	}                  
	
	public String getPontosEncontro(String idSessao, String idCarona) throws CaronaException{
		return sistema.getPontosEncontro(idCarona).toString().replace(", ", ";");
	}
	
	public String getPontosSugeridos(String idSessao, String idCarona) throws CaronaException{
		return sistema.getPontosSugeridos(idCarona).toString().replace(", ", ";");
	}
	
	//metodos do user 9 pra frente
	
	public void reviewCarona (String idSessao, String idCarona, String review) throws  SessaoInexistenteException, CaronaException, LoggerException, UsuarioInexistenteException, UsuarioNaoPossuiVagaNaCaronaException{
		sistema.reviewCarona(idSessao, idCarona, review);
	}
	
	public String cadastrarCaronaMunicipal(String idSessao, String origem, String destino,String cidade, String data, String hora, String vagas)
			throws SessaoInexistenteException, CaronaException, NumeroMaximoException {
		int vaga = 0;
		try {
			vaga = Integer.parseInt(vagas);
		} catch (Exception e) {
			
		}
		return sistema.cadastrarCaronaMunicipal(idSessao, origem, destino, cidade, data, hora, vaga);
	}
	
	public String cadastrarInteresse(String idSessao, String origem, String destino, String data, String horaInicio, String horaFim) throws SessaoInexistenteException, NumeroMaximoException, CaronaException{
		return sistema.cadastrarInteresse(idSessao, origem, destino, data, horaInicio, horaFim);
	}
	
	public List<String> verificarMensagensPerfil(String idSessao) throws SessaoInexistenteException{
		return sistema.verificarMensagensPerfil(idSessao);
	}
	
	public String enviarEmail(String idSessao, String destino, String message) throws SessaoInexistenteException{
		String retorno = "";
		if(sistema.enviarEmail(idSessao, destino, message)){
			retorno = "true";
		}else{
			retorno = "false";
		}
		return retorno;
	}
}
