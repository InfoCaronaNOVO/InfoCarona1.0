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

	/**
	 * Construtor do Objeto Fachada ,utilizado para rodar os testes do
	 * easyaccept. Nele Inicializamos os diversos objetos de sistemas do nosso
	 * programa,
	 */
	public Fachada() {
		this.sistema = new SistemaRaiz();
		this.sistemaNaoLogado = new SistemaUsuarioNaoLogado();
		this.sistemaNegociacao = new SistemaNegociacao();
		this.sistemaCarona = new SistemaCarona();
		this.sistemaUsuario = new SistemaUsuario();
	}

	/**
	 * Metodo para criar um objeto do tipo Usuario
	 * 
	 * @param login
	 *            - Login do Usuario
	 * @param senha
	 *            - Senha do Usuario
	 * @param nome
	 *            - Nome do Usuario
	 * @param endereco
	 *            - Endereço do Usuario
	 * @param email
	 *            - Email do Usuario
	 * @throws LoggerException
	 *             - Exceção referente a erros na criação do Objeto do tipo
	 *             Usuario
	 */
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws LoggerException {
		sistemaNaoLogado.criarUsuario(login, senha, nome, endereco, email);
	}

	/**
	 * Metodo utilizado para zerar o sistema.
	 */
	public void zerarSistema() {
		sistema.zerarSistema();
	}

	/**
	 * Metodo para Abrir uma sessão de um usuario
	 * 
	 * @param login
	 *            - Nome do Usuario
	 * @param senha
	 *            - Senha do Usuario
	 * @return ID da Sessão do Usuario
	 * @throws LoggerException
	 *             - referentes a problemas com a localização do objeto do
	 *             usuario cadastrado
	 * @throws NumeroMaximoException
	 *             - caso o numero maximo de conexções paralelas do servidor,
	 *             tenha sido alcançado
	 * @throws ArgumentoInexistenteException
	 *             - caso os argumentos passados pelo usuario, seja invalido
	 */
	public String abrirSessao(String login, String senha)
			throws LoggerException, NumeroMaximoException,
			ArgumentoInexistenteException {
		return sistemaNaoLogado.abrirSessao(login, senha);
	}

	/**
	 * Metodo para retorna um atributo de um objeto do tipo usuario
	 * 
	 * @param login
	 *            - Login do usuario
	 * @param atributo
	 *            - Nome do atributo que queremos saber
	 * @return - Retorna o atributo requerido
	 * @throws LoggerException
	 *             - referentes a problemas com a localização do objeto do
	 *             usuario cadastrado
	 * @throws ArgumentoInexistenteException
	 *             - caso os argumentos passados pelo usuario, seja invalido
	 */
	public String getAtributoUsuario(String login, String atributo)
			throws LoggerException, ArgumentoInexistenteException {
		return sistemaUsuario.getAtributoUsuario(login, atributo);
	}

	/**
	 * Encerra o Sistema atual, salvando todos os dados em um arquivo .xml
	 */
	public void encerrarSistema() {
		sistema.encerrarSistema();
	}

	/**
	 * Metodo para cadastrar um objeto do tipo carona no Usuario.
	 * 
	 * @param idSessao
	 *            - id do usuario logado
	 * @param origem
	 *            - Origem da carona
	 * @param destino
	 *            - destino da carona
	 * @param data
	 *            - dia que ocorrerá
	 * @param hora
	 *            - hora que ocorrerá
	 * @param vagas
	 *            - numero de vagas disponiveis
	 * @return - o ID da carona
	 * @throws CaronaException
	 *             - referente a problemas no objeto da carona
	 * @throws NumeroMaximoException
	 * @throws ArgumentoInexistenteException
	 *             - quando um dos argumentos passados pelo Sistema não existe
	 * @throws ParseException
	 *             - referente a problemas quando tentasse mudar o tipo do
	 *             objeto
	 */
	public String cadastrarCarona(String idSessao, String origem,
			String destino, String data, String hora, String vagas)
			throws CaronaException, NumeroMaximoException,
			ArgumentoInexistenteException, ParseException {
		int vaga = 0;
		try {
			vaga = Integer.parseInt(vagas);
		} catch (Exception e) {
			throw new IllegalArgumentException("Vaga inválida");
		}

		Calendar calendario = UtilInfo.converteStringEmCalendar(data, hora);

		return sistemaCarona.cadastrarCarona(idSessao, origem, destino,
				calendario, vaga);
	}

	/**
	 * Metodo para localizar um objeto do tipo carona no repositorio
	 * 
	 * @param idSessao
	 *            - id da sessão do usuario logado
	 * @param origem
	 *            - origem da carona a ser localizada
	 * @param destino
	 *            - destino da carona a ser localizada
	 * @return - retorna da(s) carona(s) localizada(s)
	 * @throws CaronaException
	 *             - referentes a problemas com a localização do objeto do tipo
	 *             carona
	 */
	public String localizarCarona(String idSessao, String origem, String destino)
			throws CaronaException {
		LinkedList<String> retorno = new LinkedList<String>();
		List<Carona> listaCaronas = sistemaCarona.localizarCarona(origem,
				destino);
		for (Carona caronaTemp : listaCaronas) {
			retorno.add(caronaTemp.getIdCarona());
		}
		return retorno.toString().replace("[", "{").replace("]", "}")
				.replace(", ", ",");
	}

	/**
	 * Metodo para localizar uma carona municipal
	 * 
	 * @param idSessao
	 *            - Id do usuario logado
	 * @param cidade
	 *            - Cidade da carona municipal
	 * @param origem
	 *            - Origem da carona municipal
	 * @param destino
	 *            - Destino da carona municipal
	 * @return - retorno da(s) carona(s) localizada(s)
	 * @throws CaronaException
	 *             - referentes a problemas com a localização do objeto do tipo
	 *             carona
	 */
	public String localizarCaronaMunicipal(String idSessao, String cidade,
			String origem, String destino) throws CaronaException {
		LinkedList<String> retorno = new LinkedList<String>();
		List<Carona> listaCaronas = sistemaCarona.localizarCaronaMunicipal(
				cidade, origem, destino);
		for (Carona caronaTemp : listaCaronas) {
			retorno.add(caronaTemp.getIdCarona());
		}
		return retorno.toString().replace("[", "{").replace("]", "}")
				.replace(", ", ",");
	}

	/**
	 * Metodo para localizar todos os objetos do tipo caronaMunicipal no
	 * repositorio, que foram criadas com a mesma cidade passada pelo sistema.
	 * 
	 * @param idSessao
	 *            - Id do usuario logado
	 * @param cidade
	 *            - Cidade da carona municipal
	 * @return - retorno da(s) carona(s) localizada(s)
	 * @throws CaronaException
	 *             - referentes a problemas com a localização do objeto do tipo
	 *             carona
	 */
	public String localizarCaronaMunicipal(String idSessao, String cidade)
			throws CaronaException {
		return localizarCaronaMunicipal(idSessao, cidade, "", "");
	}

	/**
	 * Metodo para localizar todos os objetos do tipo caronaMunicipal no
	 * repositorio, que foram criadas com a mesma origem e destino.
	 * 
	 * @param idSessao
	 *            - Id do usuario logado
	 * @param origem
	 *            - Origem da carona municipal
	 * @param destino
	 *            - Destino da carona municipal
	 * @return - retorno da(s) carona(s) localizada(s)
	 * @throws CaronaException
	 *             - referentes a problemas com a localização do objeto do tipo
	 *             carona
	 */
	public String localizarCaronaMunicipal(String idSessao, String origem,
			String destino) throws CaronaException {
		return localizarCaronaMunicipal(idSessao, "", origem, destino);
	}

	/**
	 * Metodo responsavel para retornar um atributo da carona em questão.
	 * 
	 * @param idCarona
	 *            - id da carona que queremos saber algum atributo
	 * @param atributo
	 *            - Atributo que iremos retornar da carona
	 * @return o atributo requeridó
	 * @throws CaronaException
	 *             - referentes a problemas com a localização do objeto do tipo
	 *             carona
	 * @throws ArgumentoInexistenteException
	 *             - quando não existe esse atributo no objeto carona
	 */
	public String getAtributoCarona(String idCarona, String atributo)
			throws CaronaException, ArgumentoInexistenteException {
		String retorno = null;
		if (ehVazioOuNull(idCarona)) {
			throw new IllegalArgumentException(
					"Identificador do carona é inválido");
		} else if (ehVazioOuNull(atributo)) {
			throw new IllegalArgumentException("Atributo inválido");
		}

		retorno = sistemaCarona.getAtributoCarona(idCarona, atributo);

		if (retorno.equals("")) {
			throw new ArgumentoInexistenteException("Atributo inexistente");
		}
		return retorno;
	}

	/**
	 * Metodo para receber o trajeto de uma Carona
	 * 
	 * @param idCarona
	 *            - Id da carona que queremos saber o projeto
	 * @return - retorna o trajeto da carona da forma: Origem - Destino
	 * @throws CaronaException
	 *             - referentes a problemas com a localização do objeto do tipo
	 *             carona
	 * @throws ArgumentoInexistenteException
	 *             - quando for passado um id de carona vazio.
	 */
	public String getTrajeto(String idCarona) throws CaronaException,
			ArgumentoInexistenteException {
		if (idCarona == null) {
			throw new IllegalArgumentException("Trajeto Inválida");
		}
		if (idCarona.equals("")) {
			throw new ArgumentoInexistenteException("Trajeto Inexistente");
		}
		return sistemaCarona.getTrajeto(idCarona);
	}

	/**
	 * Metodo que retorna um objeto do tipo carona cadastrada no repositorio,
	 * procurando a mesma somente pelo o idcarona
	 * 
	 * @param idCarona
	 *            - o id da carona
	 * @return a carona procurada
	 * @throws CaronaException
	 *             - referentes a problemas com a localização do objeto do tipo
	 *             carona
	 */
	public String getCarona(String idCarona) throws CaronaException {
		if (idCarona == null) {
			throw new CaronaException("Carona Inválida");
		}
		if (idCarona.equals("")) {
			throw new CaronaException("Carona Inexistente");
		}

		Carona retorno = sistemaNegociacao.getCarona(idCarona);
		if (retorno == null) {
			throw new CaronaException("Carona Inexistente");
		}
		return retorno.toString();
	}

	/**
	 * Metodo que encerra a sessão atual do usuario.
	 * 
	 * @param login
	 *            - login do usuario
	 */
	public void encerrarSessao(String login) {
		sistemaUsuario.encerrarSessao(login);
	}

	/**
	 * Metodo utilizado para sugerir ponto de encontro em uma carona
	 * 
	 * @param idSessao
	 *            - id do usuario logado que irá sugerir os pontos
	 * @param idCarona
	 *            - id da carona no qual será sugerido os pontos
	 * @param pontos
	 *            - os pontos de encontro
	 * @return - o id da sugestão
	 * @throws CaronaException
	 *             - referentes a problemas com a localização do objeto do tipo
	 *             carona
	 * @throws NumeroMaximoException
	 *             - referentes a problemas na criação do id da sugestçao
	 * @throws ArgumentoInexistenteException
	 *             - quando os pontos são passados vazios.
	 */
	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws CaronaException, NumeroMaximoException,
			ArgumentoInexistenteException {
		return sistemaNegociacao.sugerirPontoEncontro(idSessao, idCarona,
				pontos);
	}

	/**
	 * Metodo utilizado para o usuario dono da carona, responda a uma sugestão
	 * de ponto de encontro
	 * 
	 * @param idSessao
	 *            - id do usuario logado e dono da carona
	 * @param idCarona
	 *            - id da carona do usuario e que tem um ponto de encontro para
	 *            responder
	 * @param idSugestao
	 *            - id da sugestão no qual o usuario irá responder
	 * @param pontos
	 *            - os pontos com o qual o dono da carona responde em relação a
	 *            sugestão
	 * @throws CaronaException
	 *             - referentes a problemas com a localização do objeto do tipo
	 *             carona
	 * @throws ArgumentoInexistenteException
	 *             - quando os pontos são passados vazios.
	 */
	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos)
			throws CaronaException, ArgumentoInexistenteException {
		if (pontos.equals("")) {
			throw new IllegalArgumentException("Ponto Inválido");
		}
		sistemaNegociacao.responderSugestaoPontoEncontro(idSessao, idCarona,
				idSugestao, pontos);
	}

	/**
	 * Metodo utilizado para um usuario solicitar vaga em uma carona
	 * 
	 * @param idSessao
	 *            - id do usuario solicitante
	 * @param idCarona
	 *            - id da carona em que o usuario solicita vaga
	 * @param ponto
	 *            - pontos referentes ao encontro na carona
	 * @return - id da solicitação de vaga na carona
	 * @throws CaronaException
	 *             - referentes a problemas com o objeto do tipo carona
	 * @throws NumeroMaximoException
	 *             -
	 * @throws ArgumentoInexistenteException
	 */
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaException, NumeroMaximoException,
			ArgumentoInexistenteException {
		return sistemaNegociacao.solicitarVagaPontoEncontro(idSessao, idCarona,
				ponto);
	}

	/**
	 * Metodo utilizado para um usuario solicitar vaga em uma carona, aceitando
	 * o ponto de encontro que o dono da carona sugerio
	 * 
	 * @param idSessao
	 *            - id do usuario solicitante
	 * @param idCarona
	 *            - id da carona em que o usuario solicita vaga
	 * @return - id da solicitação de vaga na carona
	 * @throws CaronaException
	 *             - referentes a problemas com o objeto do tipo carona
	 * @throws NumeroMaximoException
	 *             -
	 * @throws ArgumentoInexistenteException
	 */
	public String solicitarVaga(String idSessao, String idCarona)
			throws CaronaException, NumeroMaximoException,
			ArgumentoInexistenteException {
		return sistemaNegociacao.solicitarVagaPontoEncontro(idSessao, idCarona,
				"Default");
	}

	/**
	 * Metodo para retorna algum atributo de um objeto do tipo solicitação
	 * 
	 * @param idSolicitacao
	 *            - id da solicitação
	 * @param atributo
	 *            - atributo procurado
	 * @return - o atributo procurado
	 */
	public String getAtributoSolicitacao(String idSolicitacao, String atributo) {
		return sistemaNegociacao
				.getAtributoSolicitacao(idSolicitacao, atributo);
	}

	/**
	 * Metodo para o dono da carona adcionar um comentario em alguma pessoa que
	 * pegou carona com ele
	 * 
	 * @param idSessao
	 *            - id do usuario
	 * @param idCarona
	 *            - id da carona que ele foi dono
	 * @param loginCaroneiro
	 *            - id do usuario que participou da carona
	 * @param review
	 *            - comentario da carona
	 * @throws CaronaException
	 * @throws LoggerException
	 * @throws ArgumentoInexistenteException
	 */
	public void reviewVagaEmCarona(String idSessao, String idCarona,
			String loginCaroneiro, String review) throws CaronaException,
			LoggerException, ArgumentoInexistenteException {
		sistema.reviewVagaEmCarona(idSessao, idCarona, loginCaroneiro, review);
	}

	/**
	 * Metodo para que o usuario aceite os pontos que outro usuario sugerio
	 * 
	 * @param idSessao
	 *            - id do usuario
	 * @param idSolicitacao
	 *            - id da solicitação que o mesmo irá aceitar
	 * @throws ArgumentoInexistenteException
	 */
	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws ArgumentoInexistenteException {
		sistemaNegociacao.aceitarSolicitacaoPontoEncontro(idSessao,
				idSolicitacao);
	}

	/**
	 * Metodo utilizado para que um usuario desista de uma solicitação de vaga
	 * em uma determinada carona
	 * 
	 * @param idSessao
	 *            - id do usuario
	 * @param idCarona
	 *            - id da carona que o mesmo solicitou vaga
	 * @param idSugestao
	 *            - id da sugestão da vaga
	 * @throws CaronaException
	 * @throws ArgumentoInexistenteException
	 */
	public void desistirRequisicao(String idSessao, String idCarona,
			String idSugestao) throws CaronaException,
			ArgumentoInexistenteException {
		sistemaNegociacao.desistirRequisicao(idSessao, idCarona, idSugestao);

	}

	/**
	 * Metodo para visualização do perfil de um usuario
	 * 
	 * @param idSesao
	 *            - id do usuario logado
	 * @param login
	 *            - login do usuario a ser vizualizado perfil
	 * @return - nome e login do usuario solicitado
	 * @throws LoggerException
	 * @throws ArgumentoInexistenteException
	 */
	public String visualizarPerfil(String idSesao, String login)
			throws LoggerException, ArgumentoInexistenteException {
		return sistemaUsuario.visualizarPerfil(idSesao, login);
	}

	/**
	 * Metodo para retorna um atributo de um usuario
	 * 
	 * @param login
	 *            - login do usuario que queremos saber o atributo
	 * @param atributo
	 *            - o atributo procurado
	 * @return - o atributo procurado
	 * @throws LoggerException
	 * @throws ArgumentoInexistenteException
	 */
	public String getAtributoPerfil(String login, String atributo)
			throws LoggerException, ArgumentoInexistenteException {
		String retorno = sistemaUsuario.getAtributoPerfil(login, atributo);
		if (retorno.equals(""))
			return "[]";
		return retorno;
	}

	/**
	 * Metodo utilizado para que o dono da carona recuse uma solicitação de vaga
	 * em uma carona que seja dono.
	 * 
	 * @param idSessao
	 *            - id do usuario logado
	 * @param idSolicitacao
	 *            - solicitação que sera recusada
	 * @throws ArgumentoInexistenteException
	 *             - quando não existe esta solicitação
	 */
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao)
			throws ArgumentoInexistenteException {
		sistemaNegociacao.rejeitarSolicitacao(idSessao, idSolicitacao);
	}

	/**
	 * Metodo para testar se um determinado atributo foi passado como vazio ou
	 * como Null
	 * 
	 * @param atributo
	 *            - atributo a ser testado
	 * @return: true caso afirmativo, false caso negativo
	 */
	public boolean ehVazioOuNull(String atributo) {
		if (atributo == null || atributo.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * Metodo para reiniciar o sistema buscando todos os dados salvos no
	 * servidor.
	 */
	public void reiniciarSistema() {
		sistema.reiniciarSistema();
	}

	/**
	 * Metodo para retornar uma carona dentre todas caronas criadas do usuario
	 * 
	 * @param idSessao
	 *            - id do usuario
	 * @param indexCarona
	 *            - a posição da carona dentro da lista de caronas do usuario
	 * @return - id da carona
	 * @throws ArgumentoInexistenteException
	 */
	public String getCaronaUsuario(String idSessao, int indexCarona)
			throws ArgumentoInexistenteException {
		return sistemaCarona.getCaronaUsuario(idSessao, indexCarona);
	}

	/**
	 * Metodo que retorna todas as caronas do usuario em questão
	 * 
	 * @param idSessao
	 *            - id do usuario logado
	 * @return - lista de caronas do usuario em string
	 * @throws ArgumentoInexistenteException
	 */
	public String getTodasCaronasUsuario(String idSessao)
			throws ArgumentoInexistenteException {
		List<String> retorno = new LinkedList<String>();
		List<Carona> todasCaronas = sistemaCarona
				.getTodasCaronasUsuario(idSessao);
		for (Carona caronaTemp : todasCaronas) {
			retorno.add(caronaTemp.getIdCarona());
		}
		return retorno.toString().replace("[", "{").replace("]", "}")
				.replace(", ", ",");
	}

	/**
	 * Metodo que retorna todas as solicitações da carona que foram confirmadas
	 * 
	 * @param idSessao
	 *            - id da seção do usuario
	 * @param idCarona
	 *            - id da carona em questão
	 * @return - solicitações confirmadas pelo dono da carona
	 * @throws CaronaException
	 */
	public String getSolicitacoesConfirmadas(String idSessao, String idCarona)
			throws CaronaException {
		List<String> retorno = new LinkedList<String>();
		List<SolicitacaoDeVaga> todasSolicitacoes = sistemaNegociacao
				.getSolicitacoesConfirmadas(idCarona);
		for (SolicitacaoDeVaga solicitacao : todasSolicitacoes) {
			retorno.add(solicitacao.getIdSolicitacao());
		}
		return retorno.toString().replace("[", "{").replace("]", "}")
				.replace(", ", ",");

	}

	/**
	 * Metodo que retorna as solicitações pendentes de uma determinada carona
	 * 
	 * @param idSessao
	 *            - id do usuario logado
	 * @param idCarona
	 *            - id da carona em questão
	 * @return - solicitações pendentes
	 * @throws CaronaException
	 */
	public String getSolicitacoesPendentes(String idSessao, String idCarona)
			throws CaronaException {
		return sistemaNegociacao.getSolicitacoesPendentes(idCarona).toString()
				.replace("[", "{").replace("]", "}").replace(", ", ",");
	}

	/**
	 * Metodo que retorna todos os pontos de encontro que estão nesta carona
	 * 
	 * @param idSessao
	 *            - id do a seção
	 * @param idCarona
	 *            - id da carona em questão
	 * @return - retorno dos pontos de encontro da carona
	 * @throws CaronaException
	 */
	public String getPontosEncontro(String idSessao, String idCarona)
			throws CaronaException {
		return sistemaNegociacao.getPontosEncontro(idCarona).toString()
				.replace(", ", ";");
	}

	/**
	 * Metodo que retorna todos os pontos sugeridos por caroneiros
	 * 
	 * @param idSessao
	 *            - id do usuario logado
	 * @param idCarona
	 *            - id da carona
	 * @return lista de sugestão de pontos de encontro
	 * @throws CaronaException
	 */
	public String getPontosSugeridos(String idSessao, String idCarona)
			throws CaronaException {
		return sistemaNegociacao.getPontosSugeridos(idCarona).toString()
				.replace(", ", ";");
	}

	// metodos do user 9 pra frente

	/**
	 * Metodo para o usuario adcionar um comentario em uma carona que o mesmo
	 * participou
	 * 
	 * @param idSessao
	 *            - id do usuario
	 * @param idCarona
	 *            - id da carona que sera posta um comentario
	 * @param review
	 *            - comentario
	 * @throws CaronaException
	 *             - quando o usuario não possuio vaga nesta carona
	 * @throws LoggerException
	 * @throws ArgumentoInexistenteException
	 *             - quando o comentario for diferente dos comentarios aceitos
	 *             pelo sistema
	 */
	public void reviewCarona(String idSessao, String idCarona, String review)
			throws CaronaException, LoggerException,
			ArgumentoInexistenteException {
		sistemaCarona.reviewCarona(idSessao, idCarona, review);
	}

	/**
	 * Metodo que cadastra uma carona municipal
	 * 
	 * @param idSessao
	 *            - id do usuario logado que esta cadastrando
	 * @param origem
	 *            - origem da carona
	 * @param destino
	 *            - destino da carona
	 * @param cidade
	 *            - cidade da carona
	 * @param data
	 *            - data da carona
	 * @param hora
	 *            - hora da carona
	 * @param vagas
	 *            - numero de vagas disponiveis
	 * @return id da carona
	 * @throws CaronaException
	 * @throws NumeroMaximoException
	 * @throws ArgumentoInexistenteException
	 * @throws ParseException
	 *             - quando o arumento vagas não for do tipo inteiro
	 */
	public String cadastrarCaronaMunicipal(String idSessao, String origem,
			String destino, String cidade, String data, String hora,
			String vagas) throws CaronaException, NumeroMaximoException,
			ArgumentoInexistenteException, ParseException {
		int vaga = 0;
		try {
			vaga = Integer.parseInt(vagas);
		} catch (Exception e) {

		}
		Calendar calendario = UtilInfo.converteStringEmCalendar(data, hora);
		return sistemaCarona.cadastrarCaronaMunicipal(idSessao, origem,
				destino, cidade, calendario, vaga);
	}

	/**
	 * Metodo para que um usuario cadastre um interesse sobre uma carona que
	 * esteja procurando em um intervalo de tempo
	 * 
	 * @param idSessao
	 *            - id do usuario que irá cadastrar este interesse
	 * @param origem
	 *            - origem da carona que esta interessado
	 * @param destino
	 *            - destino da carona que esta interessado
	 * @param data
	 *            - data da carona que esta interessado
	 * @param horaInicio
	 *            - inicio do intervalo do horario que esta interessado em pegar
	 *            uma carona
	 * @param horaFim
	 *            - final do intervalo do horario que esta interessado em pegar
	 *            uma carona
	 * @return - id do interesse cadastrado
	 * @throws NumeroMaximoException
	 * @throws CaronaException
	 * @throws ArgumentoInexistenteException
	 *             - data invalida
	 * @throws ParseException
	 */
	public String cadastrarInteresse(String idSessao, String origem,
			String destino, String data, String horaInicio, String horaFim)
			throws NumeroMaximoException, CaronaException,
			ArgumentoInexistenteException, ParseException {
		if (data == null) { // coloquei isso pq vai quebrar no equals aki, mas
							// depois nos vê como ajeita
			throw new IllegalArgumentException("Data inválida");
		}

		boolean caronaEhNoDia = true;
		if (data.equals("")) {
			Calendar novaData = new GregorianCalendar();
			novaData.add(Calendar.YEAR, 10);
			data = UtilInfo.converteCalendarEmStringData(novaData);
			caronaEhNoDia = false;
		}
		if (horaInicio.equals("")) {
			horaInicio = "00:00";
		}
		if (horaFim.equals("")) {
			horaFim = "11:59";
		}

		Calendar calendarioInicial = UtilInfo.converteStringEmCalendar(data,
				horaInicio);
		Calendar calendarioFinal = UtilInfo.converteStringEmCalendar(data,
				horaFim);
		return sistemaUsuario.cadastrarInteresse(idSessao, origem, destino,
				calendarioInicial, calendarioFinal, caronaEhNoDia);
	}

	/**
	 * Metodo para verificar mensagens não lidas pelo usuario
	 * 
	 * @param idSessao
	 *            - id do usuario
	 * @return retorna uma lista de mensagens do usuario
	 * @throws ArgumentoInexistenteException
	 *             - sessão invalida
	 */
	public List<String> verificarMensagensPerfil(String idSessao)
			throws ArgumentoInexistenteException {
		return sistemaUsuario.verificarMensagensPerfil(idSessao);
	}

	/**
	 * Metodo para enviar um email
	 * 
	 * @param idSessao
	 *            - id do usuario
	 * @param destino
	 *            - email destinatário
	 * @param message
	 *            - mensagem do email
	 * @return - confirmação de enviou
	 * @throws ArgumentoInexistenteException
	 */
	public String enviarEmail(String idSessao, String destino, String message)
			throws ArgumentoInexistenteException {
		String retorno = "";
		if (sistema.enviarEmail(idSessao, destino, message)) {
			retorno = "true";
		} else {
			retorno = "false";
		}
		return retorno;
	}
}
