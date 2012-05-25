package ufcg.si1.InfoCaronaMaven.Sistema;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import util.UtilInfo;


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
			throws LoggerException, NumeroMaximoException, ArgumentoInexistenteException {
		return sistema.abrirSessao(login, senha);
	}

	public String getAtributoUsuario(String login, String atributo)
			throws LoggerException, ArgumentoInexistenteException {
		return sistema.getAtributoUsuario(login, atributo);
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
		
		return sistema.cadastrarCarona(idSessao, origem, destino, calendario,
				vaga);
	}

	public String localizarCarona(String idSessao, String origem, String destino)
			throws CaronaException {
		LinkedList<String> retorno = new LinkedList<String>();
		List<Carona> listaCaronas = sistema.localizarCarona(origem, destino);
		for(Carona caronaTemp : listaCaronas){
			retorno.add(caronaTemp.getIdCarona());
		}
		return retorno.toString().replace("[", "{").replace("]", "}").replace(", ", ",");
	}
	
	
	public String localizarCaronaMunicipal(String idSessao, String cidade, String origem, String destino)
			throws CaronaException, CaronaException {
		LinkedList<String> retorno = new LinkedList<String>();
		List<Carona> listaCaronas = sistema.localizarCaronaMunicipal(cidade, origem, destino);
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
	
		retorno = sistema.getAtributoCarona(idCarona, atributo);
		
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
		return sistema.getTrajeto(idCarona);
	}

	public String getCarona(String idCarona) throws CaronaException {
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
			String pontos) throws CaronaException, NumeroMaximoException, ArgumentoInexistenteException {
		return sistema.sugerirPontoEncontro(idSessao, idCarona, pontos);
	}

	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos)
			throws CaronaException, ArgumentoInexistenteException{
		if(pontos.equals("")){
			throw new IllegalArgumentException("Ponto Inválido");
		}
		sistema.responderSugestaoPontoEncontro(idSessao, idCarona, idSugestao,
				pontos);
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaException, NumeroMaximoException, ArgumentoInexistenteException  {
		return sistema.solicitarVagaPontoEncontro(idSessao, idCarona, ponto);
	}

	public String solicitarVaga(String idSessao, String idCarona)
			throws CaronaException, NumeroMaximoException, ArgumentoInexistenteException  {
		return sistema.solicitarVagaPontoEncontro(idSessao, idCarona, "Default");
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo) {
		return sistema.getAtributoSolicitacao(idSolicitacao, atributo);
	}
	public void reviewVagaEmCarona (String idSessao, String idCarona, String loginCaroneiro, String review) throws CaronaException, LoggerException, ArgumentoInexistenteException{
		sistema.reviewVagaEmCarona(idSessao, idCarona, loginCaroneiro, review);
	}
	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws ArgumentoInexistenteException {
		sistema.aceitarSolicitacaoPontoEncontro(idSessao, idSolicitacao);
	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSugestao) throws CaronaException, ArgumentoInexistenteException{
		sistema.desistirRequisicao(idSessao, idCarona, idSugestao);

	}

	public String visualizarPerfil(String idSesao, String login) throws LoggerException, ArgumentoInexistenteException {
		return sistema.visualizarPerfil(idSesao, login);
	}

	public String getAtributoPerfil(String login, String atributo) throws LoggerException, ArgumentoInexistenteException {
		String retorno = sistema.getAtributoPerfil(login, atributo);
		if(retorno.equals(""))
			return "[]";
		return retorno;
	}
	
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws ArgumentoInexistenteException{
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
	
	public String getCaronaUsuario(String idSessao, int indexCarona) throws ArgumentoInexistenteException{
		return sistema.getCaronaUsuario(idSessao, indexCarona);
	}
	
	public String getTodasCaronasUsuario(String idSessao) throws ArgumentoInexistenteException{
		List<String> retorno = new LinkedList<String>(); 
		List<Carona> todasCaronas = sistema.getTodasCaronasUsuario(idSessao);
		for (Carona caronaTemp : todasCaronas) {
			retorno.add(caronaTemp.getIdCarona());
		}
		return retorno.toString().replace("[", "{").replace("]", "}").replace(", ", ",");
	}
	
	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) throws CaronaException{
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
	
	public void reviewCarona (String idSessao, String idCarona, String review) throws CaronaException, LoggerException, ArgumentoInexistenteException{
		sistema.reviewCarona(idSessao, idCarona, review);
	}
	
	public String cadastrarCaronaMunicipal(String idSessao, String origem, String destino,String cidade, String data, String hora, String vagas)
			throws CaronaException, NumeroMaximoException, ArgumentoInexistenteException, ParseException {
		int vaga = 0;
		try {
			vaga = Integer.parseInt(vagas);
		} catch (Exception e) {
			
		}
		Calendar calendario = UtilInfo.converteStringEmCalendar(data, hora);
		return sistema.cadastrarCaronaMunicipal(idSessao, origem, destino, cidade, calendario, vaga);
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
		return sistema.cadastrarInteresse(idSessao, origem, destino, calendarioInicial, calendarioFinal, caronaEhNoDia);
	}
	
	public List<String> verificarMensagensPerfil(String idSessao) throws ArgumentoInexistenteException{
		return sistema.verificarMensagensPerfil(idSessao);
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
