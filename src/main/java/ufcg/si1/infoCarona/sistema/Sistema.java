package ufcg.si1.infoCarona.sistema;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import util.UtilInfo;

public class Sistema {
	/**
	 * Classe Sistema utilizada incialmente para a logica do sistema todos os
	 * metodos da fachada, s�o chamadas por ele.
	 */

	private ControlerRepositorio controleRepositorio;
	private Id id;
	private Map<String, Usuario> usuariosLogados;
	UtilInfo util;

	public Sistema() {
		id = new Id(5);
		this.criaSistema();
		util = new UtilInfo();
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
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws LoggerException{

		if (controleRepositorio.checaExisteLogin(login)) {
			throw new LoggerException("Já existe um usuário com este login");
		}

		if (controleRepositorio.checaExisteEmail(email)) {
			throw new LoggerException("Já existe um usuário com este email");
		}

		Usuario novoUsuario = new Usuario(nome, email, endereco, senha, login);
		controleRepositorio.addUsuario(novoUsuario);

	}

	public String abrirSessao(String login, String senha)
			throws LoggerException, NumeroMaximoException, ArgumentoInexistenteException {

		String idSessao = id.gerarId();

		if (!(checaLogin(login))) {
			throw new LoggerException("Login inválido");
		}

		Usuario usuarioTemp = controleRepositorio.buscarUsuarioPorLogin(login);

		if (usuarioTemp.getSenha().equals(senha)) {
			usuariosLogados.put(idSessao, usuarioTemp);
		} else {
			throw new LoggerException("Login inválido");
		}

		return idSessao;
	}

	public void encerrarSessao(String login) {
		Collection<String> listaUsuariosLogados = usuariosLogados.keySet();
		Usuario removeUsuario = null;
		for (String idSessao : listaUsuariosLogados) {
			if (usuariosLogados.get(idSessao).getLogin().equals(login)) {
				removeUsuario = usuariosLogados.get(idSessao);
				break;
			}
		}
		listaUsuariosLogados.remove(removeUsuario);
	}

	public String getAtributoUsuario(String login, String atributo)
			throws LoggerException, ArgumentoInexistenteException {

		String retorno = "";

		if (!checaLogin(login)) {
			throw new LoggerException("Login inválido");
		}

		if (checaAtributo(atributo)) {
			throw new IllegalArgumentException("Atributo inválido");
		}
		if (!checaAtributoValido(atributo)) {
			throw new ArgumentoInexistenteException("Atributo inexistente");
		}

		retorno = controleRepositorio.getAtributoUsuario(login, atributo);

		return retorno;
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

		return controleRepositorio.localizarCarona(origem, destino);
	}

	public String getAtributoCarona(String idCarona, String atributo) throws 
			CaronaException, ArgumentoInexistenteException {

		return controleRepositorio.getAtributoCarona(idCarona, atributo);
	}

	public String cadastrarCarona(String idSessao, String origem,
			String destino, Calendar calendario, int vagas)
			throws CaronaException, NumeroMaximoException, CaronaException, ArgumentoInexistenteException, ParseException {

		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		String idCarona = usuarioTemp.cadastrarCarona(origem, destino, calendario, vagas, id.gerarId());
		
		Carona caronaTemp = controleRepositorio.localizaCaronaPorId(idCarona);
		enviaMsgAInteressadosEmCarona(caronaTemp);
		
		return idCarona;
	}

	public Carona getCarona(String idCarona) throws CaronaException {
		if (ehVazioOuNull(idCarona)) {
			throw new CaronaException("Carona Inválida");
		}
		return controleRepositorio.localizaCaronaPorId(idCarona);
	}

	public String getTrajeto(String idCarona)
			throws
			CaronaException, ArgumentoInexistenteException {

		return controleRepositorio.getTrajeto(idCarona);
	}

	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws CaronaException,
			NumeroMaximoException, ArgumentoInexistenteException {
		if (pontos == null || pontos.equals("")) {
			throw new IllegalArgumentException("Ponto Inválido");
		}
		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		Carona caronaTemp = this.getCarona(idCarona);
		if(usuarioJahEstahNaCarona(usuarioTemp, caronaTemp)){
			throw new IllegalArgumentException("Ponto Inválido");
		}
		
		return usuarioTemp.sugerirPontoEncontro(pontos, caronaTemp,
				id.gerarId(), usuarioTemp);

	}

	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos)
			throws CaronaException, ArgumentoInexistenteException{

		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		SugestaoDePontoDeEncontro sugestaoTemp = controleRepositorio.getSugestaoId(idSugestao, idCarona);
		Carona caronaTemp = controleRepositorio.localizaCaronaPorId(idCarona);

		usuarioTemp.responderSugestaoPontoEncontro(sugestaoTemp, pontos, caronaTemp);
	}

	public void encerrarSistema() {
		usuariosLogados = new HashMap<String, Usuario>();
		controleRepositorio.encerrarSistema();		
	}

	// metodo pra ver se o atributo passado existe
	private boolean checaAtributoValido(String atributo) {
		return (atributo.equals("nome") || atributo.equals("endereco") || atributo
				.equals("email"));
	}

	// Metodos abaixo servem para checar se os atributos para criar s�o passados
	// como null ou vazio

	private boolean checaLogin(String login) throws LoggerException {
		return (!(login == null || login.equals("")));

	}

	private boolean checaAtributo(String atributo) {
		return (atributo == null || atributo.equals(""));
	}

	private boolean checaIdSessao(String idSessao) {
		return (!(idSessao == null || idSessao.equals("")));
	}

	private Usuario procuraUsuarioLogado(String idSessao)
			throws ArgumentoInexistenteException {
		Usuario retorno = null;
		if (!checaIdSessao(idSessao)) {
			throw new IllegalArgumentException("Sessão inválida");
		}

		retorno = usuariosLogados.get(idSessao);

		if (retorno == null) {
			throw new ArgumentoInexistenteException("Sessão inexistente");
		}

		return retorno;
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaException,NumeroMaximoException, ArgumentoInexistenteException {

		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		Carona carona = controleRepositorio.localizaCaronaPorId(idCarona);
		if (ponto.equals("")) {
			ponto = null; // subtende-se que o usuario aceita os pontos que o
							// dono da carona indicou
		}

		return usuarioTemp.solicitarVagaPontoEncontro(ponto, carona,
				id.gerarId(), usuarioTemp);
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo) {
		return controleRepositorio.getAtributoSolicitacao(idSolicitacao,
				atributo);
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws ArgumentoInexistenteException  {

		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		SolicitacaoDeVaga solicitacao = controleRepositorio
				.localizaSolicitacaoPorId(idSolicitacao);

		usuarioTemp.aceitarSolicitacaoPontoEncontro(solicitacao);
	}

	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws ArgumentoInexistenteException{
		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		SolicitacaoDeVaga solicitacao = controleRepositorio
				.localizaSolicitacaoPorId(idSolicitacao);
		usuarioTemp.rejeitarSolicitacao(solicitacao);

	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws CaronaException, ArgumentoInexistenteException {

		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		Carona caronaTemp = controleRepositorio.localizaCaronaPorId(idCarona);
		SolicitacaoDeVaga solicitacaoTemp = controleRepositorio.localizaSolicitacaoPorId(idSolicitacao);
		usuarioTemp.desistirRequisicao(solicitacaoTemp, caronaTemp);

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

	public String visualizarPerfil(String idSessao, String login)
			throws LoggerException, ArgumentoInexistenteException {
		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		Usuario usuarioProcurado = null;
		try {
			usuarioProcurado = controleRepositorio.buscarUsuarioPorLogin(login);
		} catch (Exception e) {
			throw new LoggerException("Login inválido");
		}

		return usuarioTemp.visualizarPerfil(usuarioProcurado);
	}

	public String getAtributoPerfil(String login, String atributo)
			throws
			LoggerException, ArgumentoInexistenteException {
		
		if (!atributo.equals("historico de vagas em caronas")) {
			return controleRepositorio.getAtributoUsuario(login, atributo);
		}
		Usuario usuarioTemp = controleRepositorio.buscarUsuarioPorLogin(login);
			List<String> lista = new LinkedList<String>();
			for (Carona caronaTemp : usuarioTemp.getSolicitacaoAceitas()) {
				lista.add(caronaTemp.getIdCarona());				
			}
			if(lista.size() == 0){
				return "";
			}
			return lista.toString().replace("{", "[").replace("}", "]").replace(", ", ",");
			
		}
	

	public boolean ehVazioOuNull(String atributo) {
		if (atributo == null || atributo.equals("")) {
			return true;
		}
		return false;
	}

	public void reiniciarSistema() {
		this.criaSistema();
	}

	public String getCaronaUsuario(String idSessao, int indexCarona) throws ArgumentoInexistenteException {
		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		return usuarioTemp.getCaronas().get(indexCarona - 1).getIdCarona();
	}

	public List<Carona> getTodasCaronasUsuario(String idSessao) throws ArgumentoInexistenteException {
		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		return usuarioTemp.getCaronas();
	}

	public List<SolicitacaoDeVaga> getSolicitacoesConfirmadas(String idCarona) throws CaronaException {
		return controleRepositorio.localizaCaronaPorId(idCarona).getSolicitacoesConfirmadas();
	}

	public List<SolicitacaoDeVaga> getSolicitacoesPendentes(String idCarona)
			throws CaronaException {
		return controleRepositorio.localizaCaronaPorId(idCarona)
				.getSolicitacoesPendentes();
	}

	public List<String> getPontosEncontro(String idCarona)
			throws CaronaException {
		List<String> retorno = new LinkedList<String>();
        List<SugestaoDePontoDeEncontro> sugestoes = controleRepositorio.localizaCaronaPorId(idCarona)
                .getListaDeSugestoes();
        
        for (int i = 0; i < sugestoes.size(); i++) {
            List<String> pontos = sugestoes.get(i).getListaDeSugestaoDePontosDeEncontro();
            for (int j = 0; j < pontos.size(); j++) {
                String ponto = pontos.get(j);
                if (!retorno.contains(ponto)) {
                    retorno.add(ponto);
                }
            }
        }
        
        return retorno;
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

	public LinkedList<String> getPontosSugeridos(String idCarona) throws CaronaException {
		LinkedList<String> retorno = new LinkedList<String>();
		Carona caronaTemp = controleRepositorio.localizaCaronaPorId(idCarona);
		List<SugestaoDePontoDeEncontro> listaSugestoes = caronaTemp.getListaDeSugestoes();
		for(SugestaoDePontoDeEncontro sugestao : listaSugestoes){
			for (String ponto : sugestao.getListaDeSugestaoDePontosDeEncontro()) {
				retorno.add(ponto);
			}
		}
		return retorno;
	}
	
	public void reviewCarona(String idSessao, String idCarona, String review) throws CaronaException, ArgumentoInexistenteException {
		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		Carona caronaTemp = controleRepositorio.localizaCaronaPorId(idCarona);
		if( !((review.equals("segura e tranquila")) || (review.equals("não funcionou")))){
			throw new IllegalArgumentException("Opção inválida.");
		}
		if(usuarioJahEstahNaCarona(usuarioTemp, caronaTemp)){
			caronaTemp.addReviewCarona(usuarioTemp, review);
		}else{
			throw new CaronaException("Usuário não possui vaga na carona.");
		}	
	}
	
	public String cadastrarCaronaMunicipal(String idSessao, String origem, String destino, String cidade, Calendar calendario, int vagas)
			throws CaronaException, NumeroMaximoException, ArgumentoInexistenteException {

			Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
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

		return controleRepositorio.localizarCaronaMunicipal(cidade, origem, destino);
	}

	public String cadastrarInteresse(String idSessao, String origem, String destino, Calendar calendarioInicial, Calendar calendarioFinal, boolean caronaEhNoDia) throws NumeroMaximoException, CaronaException, ArgumentoInexistenteException {
		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		return usuarioTemp.cadastrarInteresse(origem, destino, calendarioInicial, calendarioFinal, id.gerarId(), caronaEhNoDia);
		
	}
	
	public void enviaMsgAInteressadosEmCarona(Carona carona){
		List<Usuario> listaDeInteressados = controleRepositorio.localizaInteressados(carona);
		for (Usuario usuario : listaDeInteressados) {
			String novaMensagem = "Carona cadastrada no dia " + UtilInfo.converteCalendarEmStringData(carona.getCalendario()) + ", às " + UtilInfo.converteCalendarEmStringHora(carona.getCalendario()) + " de acordo com os seus interesses registrados. Entrar em contato com " + carona.getDonoDaCarona().getEmail();
			usuario.addMensagen(novaMensagem);
		}
	}
	
	public List<String> verificarMensagensPerfil(String idSessao) throws ArgumentoInexistenteException {
		return procuraUsuarioLogado(idSessao).getListaDeMensagens();
	}

	public boolean enviarEmail(String idSessao, String destino, String message) throws ArgumentoInexistenteException{
		Usuario usuarioTemp = procuraUsuarioLogado(idSessao);
		return usuarioTemp.enviarEmail(destino, message);
	}

}
