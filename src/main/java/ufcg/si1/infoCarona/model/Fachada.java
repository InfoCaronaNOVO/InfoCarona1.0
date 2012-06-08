package ufcg.si1.infoCarona.model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import ufcg.si1.infoCarona.model.carona.Carona;
import ufcg.si1.infoCarona.model.carona.CaronaException;
import ufcg.si1.infoCarona.model.negociacao.SolicitacaoDeVaga;
import ufcg.si1.infoCarona.model.sistema.*;
import util.UtilInfo;


public class Fachada {

	private SistemaRaiz sistema;
	private SistemaUsuarioNaoLogado sistemaNaoLogado;
	private SistemaNegociacao sistemaNegociacao;
	private SistemaCarona sistemaCarona;
	private SistemaUsuario sistemaUsuario;
	
	public Fachada() {
		this.sistema = new SistemaRaiz();
		this.sistemaNaoLogado = new SistemaUsuarioNaoLogado();
		this.sistemaNegociacao = new SistemaNegociacao();
		this.sistemaCarona = new SistemaCarona();
		this.sistemaUsuario = new SistemaUsuario();
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		sistemaNaoLogado.criarUsuario(login, senha, nome, endereco, email);
	}

	public void zerarSistema() {
		sistema.zerarSistema();
	}

	public String abrirSessao(String login, String senha)
			throws LoggerException, NumeroMaximoException, ArgumentoInexistenteException {
		return sistemaNaoLogado.abrirSessao(login, senha);
	}

	public String getAtributoUsuario(String login, String atributo)
			throws LoggerException, ArgumentoInexistenteException {
		return sistemaUsuario.getAtributoUsuario(login, atributo);
	}

	public void encerrarSistema() {
		sistema.encerrarSistema();
	}

	public String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, String vagas)
			throws  CaronaException,
			 NumeroMaximoException, CaronaException, ArgumentoInexistenteException, ParseException {
		int vaga = 0;
		try {
			vaga = Integer.parseInt(vagas);
		} catch (Exception e) {
			throw new IllegalArgumentException("Vaga inválida");
		}
		
		Calendar calendario = UtilInfo.converteStringEmCalendar(data, hora);
		
		return sistemaCarona.cadastrarCarona(idSessao, origem, destino, calendario,
				vaga);
	}

	public String localizarCarona(String idSessao, String origem, String destino)
			throws CaronaException {
		LinkedList<String> retorno = new LinkedList<String>();
		List<Carona> listaCaronas = sistemaCarona.localizarCarona(origem, destino);
		for(Carona caronaTemp : listaCaronas){
			retorno.add(caronaTemp.getIdCarona());
		}
		return retorno.toString().replace("[", "{").replace("]", "}").replace(", ", ",");
	}
	
	
	public String localizarCaronaMunicipal(String idSessao, String cidade, String origem, String destino)
			throws CaronaException, CaronaException {
		LinkedList<String> retorno = new LinkedList<String>();
		List<Carona> listaCaronas = sistemaCarona.localizarCaronaMunicipal(cidade, origem, destino);
		for(Carona caronaTemp : listaCaronas){
			retorno.add(caronaTemp.getIdCarona());
		}
		return retorno.toString().replace("[", "{").replace("]", "}").replace(", ", ",");
	}
	
	public String localizarCaronaMunicipal(String idSessao, String cidade) throws CaronaException{
		return localizarCaronaMunicipal(idSessao, cidade, "", "");
	}
	
	public String localizarCaronaMunicipal(String idSessao, String origem, String destino) throws CaronaException{
		return localizarCaronaMunicipal(idSessao, "", origem, destino);
	}

	public String getAtributoCarona(String idCarona, String atributo)
			throws CaronaException, ArgumentoInexistenteException {
		String retorno = null;
		if(ehVazioOuNull(idCarona)){
			throw new IllegalArgumentException("Identificador do carona é inválido");
		}else if(ehVazioOuNull(atributo)){
			throw new IllegalArgumentException("Atributo inválido");
		}
	
		retorno = sistemaCarona.getAtributoCarona(idCarona, atributo);
		
		if(retorno.equals("")){
	      	throw new ArgumentoInexistenteException("Atributo inexistente");
	    }
		return retorno;
	}

	public String getTrajeto(String idCarona)
			throws CaronaException, ArgumentoInexistenteException {
		if(idCarona == null){
			throw new IllegalArgumentException("Trajeto Inválida");
		}
		if(idCarona.equals("")){
			throw new ArgumentoInexistenteException("Trajeto Inexistente");
		}
		return sistemaCarona.getTrajeto(idCarona);
	}

	public String getCarona(String idCarona) throws CaronaException {
		if(idCarona == null){
			throw new CaronaException("Carona Inválida");
		}
		if(idCarona.equals("")){
			throw new CaronaException("Carona Inexistente");
		}
		
		Carona retorno = sistemaNegociacao.getCarona(idCarona);
		if(retorno == null){
			throw new CaronaException("Carona Inexistente");
		}
		return retorno.toString();
	}

	public void encerrarSessao(String login) {
		sistemaUsuario.encerrarSessao(login);
	}

	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws CaronaException, NumeroMaximoException, ArgumentoInexistenteException {
		return sistemaNegociacao.sugerirPontoEncontro(idSessao, idCarona, pontos);
	}

	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos)
			throws CaronaException, ArgumentoInexistenteException{
		if(pontos.equals("")){
			throw new IllegalArgumentException("Ponto Inválido");
		}
		sistemaNegociacao.responderSugestaoPontoEncontro(idSessao, idCarona, idSugestao,
				pontos);
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaException, NumeroMaximoException, ArgumentoInexistenteException  {
		return sistemaNegociacao.solicitarVagaPontoEncontro(idSessao, idCarona, ponto);
	}

	public String solicitarVaga(String idSessao, String idCarona)
			throws CaronaException, NumeroMaximoException, ArgumentoInexistenteException  {
		return sistemaNegociacao.solicitarVagaPontoEncontro(idSessao, idCarona, "Default");
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo) {
		return sistemaNegociacao.getAtributoSolicitacao(idSolicitacao, atributo);
	}
	public void reviewVagaEmCarona (String idSessao, String idCarona, String loginCaroneiro, String review) throws CaronaException, LoggerException, ArgumentoInexistenteException{
		sistema.reviewVagaEmCarona(idSessao, idCarona, loginCaroneiro, review);
	}
	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws ArgumentoInexistenteException {
		sistemaNegociacao.aceitarSolicitacaoPontoEncontro(idSessao, idSolicitacao);
	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSugestao) throws CaronaException, ArgumentoInexistenteException{
		sistemaNegociacao.desistirRequisicao(idSessao, idCarona, idSugestao);

	}

	public String visualizarPerfil(String idSesao, String login) throws LoggerException, ArgumentoInexistenteException {
		return sistemaUsuario.visualizarPerfil(idSesao, login);
	}

	public String getAtributoPerfil(String login, String atributo) throws LoggerException, ArgumentoInexistenteException {
		String retorno = sistemaUsuario.getAtributoPerfil(login, atributo);
		if(retorno.equals(""))
			return "[]";
		return retorno;
	}
	
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws ArgumentoInexistenteException{
		sistemaNegociacao.rejeitarSolicitacao(idSessao, idSolicitacao);
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
	
	public String getCaronaUsuario(String idSessao, int indexCarona) throws ArgumentoInexistenteException{
		return sistemaCarona.getCaronaUsuario(idSessao, indexCarona);
	}
	
	public String getTodasCaronasUsuario(String idSessao) throws ArgumentoInexistenteException{
		List<String> retorno = new LinkedList<String>(); 
		List<Carona> todasCaronas = sistemaCarona.getTodasCaronasUsuario(idSessao);
		for (Carona caronaTemp : todasCaronas) {
			retorno.add(caronaTemp.getIdCarona());
		}
		return retorno.toString().replace("[", "{").replace("]", "}").replace(", ", ",");
	}
	
	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) throws CaronaException{
		List<String> retorno = new LinkedList<String>(); 
		List<SolicitacaoDeVaga> todasSolicitacoes = sistemaNegociacao.getSolicitacoesConfirmadas(idCarona);
		for (SolicitacaoDeVaga solicitacao : todasSolicitacoes) {
			retorno.add(solicitacao.getIdSolicitacao());
		}
		return retorno.toString().replace("[", "{").replace("]", "}").replace(", ", ",");
		
	}
	
	public String getSolicitacoesPendentes(String idSessao, String idCarona) throws CaronaException{
		return sistemaNegociacao.getSolicitacoesPendentes(idCarona).toString().replace("[", "{").replace("]", "}").replace(", ", ",");
	}                  
	
	public String getPontosEncontro(String idSessao, String idCarona) throws CaronaException{
		return sistemaNegociacao.getPontosEncontro(idCarona).toString().replace(", ", ";");
	}
	
	public String getPontosSugeridos(String idSessao, String idCarona) throws CaronaException{
		return sistemaNegociacao.getPontosSugeridos(idCarona).toString().replace(", ", ";");
	}
	
	//metodos do user 9 pra frente
	
	public void reviewCarona (String idSessao, String idCarona, String review) throws CaronaException, LoggerException, ArgumentoInexistenteException{
		sistemaCarona.reviewCarona(idSessao, idCarona, review);
	}
	
	public String cadastrarCaronaMunicipal(String idSessao, String origem, String destino,String cidade, String data, String hora, String vagas)
			throws CaronaException, NumeroMaximoException, ArgumentoInexistenteException, ParseException {
		int vaga = 0;
		try {
			vaga = Integer.parseInt(vagas);
		} catch (Exception e) {
			
		}
		Calendar calendario = UtilInfo.converteStringEmCalendar(data, hora);
		return sistemaCarona.cadastrarCaronaMunicipal(idSessao, origem, destino, cidade, calendario, vaga);
	}
	
	public String cadastrarInteresse(String idSessao, String origem, String destino, String data, String horaInicio, String horaFim) throws NumeroMaximoException, CaronaException, ArgumentoInexistenteException, ParseException{
		if(data == null){ //coloquei isso pq vai quebrar no equals aki, mas depois nos vê como ajeita
			throw new IllegalArgumentException("Data inválida");
		}
		
		boolean caronaEhNoDia = true;
		if(data.equals("")){
			Calendar novaData = new GregorianCalendar();
			novaData.add(Calendar.YEAR, 10);
			data = UtilInfo.converteCalendarEmStringData(novaData);
			caronaEhNoDia = false;
		}
		if(horaInicio.equals("")){
			horaInicio = "00:00";
		}
		if(horaFim.equals("")){
			horaFim = "11:59";
		}
		
		Calendar calendarioInicial = UtilInfo.converteStringEmCalendar(data, horaInicio);
		Calendar calendarioFinal = UtilInfo.converteStringEmCalendar(data, horaFim);
		return sistemaUsuario.cadastrarInteresse(idSessao, origem, destino, calendarioInicial, calendarioFinal, caronaEhNoDia);
	}
	
	public List<String> verificarMensagensPerfil(String idSessao) throws ArgumentoInexistenteException{
		return sistemaUsuario.verificarMensagensPerfil(idSessao);
	}
	
	public String enviarEmail(String idSessao, String destino, String message) throws ArgumentoInexistenteException{
		String retorno = "";
		if(sistema.enviarEmail(idSessao, destino, message)){
			retorno = "true";
		}else{
			retorno = "false";
		}
		return retorno;
	}
}
