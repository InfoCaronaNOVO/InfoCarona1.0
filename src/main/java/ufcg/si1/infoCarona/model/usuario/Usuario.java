package ufcg.si1.infoCarona.model.usuario;

import java.text.ParseException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import ufcg.si1.infoCarona.model.ArgumentoInexistenteException;
import ufcg.si1.infoCarona.model.Interesse;
import ufcg.si1.infoCarona.model.LoggerException;
import ufcg.si1.infoCarona.model.NumeroMaximoException;
import ufcg.si1.infoCarona.model.carona.Carona;
import ufcg.si1.infoCarona.model.carona.CaronaComum;
import ufcg.si1.infoCarona.model.carona.CaronaException;
import ufcg.si1.infoCarona.model.carona.CaronaMunicipal;
import ufcg.si1.infoCarona.model.negociacao.EstadoSolicitacao;
import ufcg.si1.infoCarona.model.negociacao.SolicitacaoDeVaga;
import ufcg.si1.infoCarona.model.negociacao.SugestaoDePontoDeEncontro;


public class Usuario implements Interessado{

	private List<Carona> listaDeCaronas;
	private List<SolicitacaoDeVaga> listaDeSolicitacaoDeVagas;
	private List<String> Reviews;
	private List<Interesse> listaDeInteresses;
	private List<String> listaDeMensagens;

	private int caronasSeguras, caronaNaoFuncionaram, faltasEmVagas, presencaEmVagas;
	private String nome, email, endereco, senha, login;

	public Usuario(String nome, String email, String endereco, String senha,String login) throws LoggerException{
		setNome(nome);
		setEmail(email);
		setEndereco(endereco);
		setSenha(senha);
		setLogin(login);
		this.listaDeCaronas = new LinkedList<Carona>();
		this.listaDeSolicitacaoDeVagas = new LinkedList<SolicitacaoDeVaga>();
		this.listaDeInteresses = new LinkedList<Interesse>();
		this.listaDeMensagens = new LinkedList<String>();
		this.Reviews = new LinkedList<String>();
		this.caronasSeguras = 0;
		this.caronaNaoFuncionaram = 0;
		this.faltasEmVagas = 0;
		this.presencaEmVagas = 0;
	}

	public List<Interesse> getListaDeInteresses() {
		return listaDeInteresses;
	}

	/**
	 * Metodo para cadastrar um objeto do tipo carona no Usuario.
	 * 
	 * @param origem - Origem da carona
	 * @param destino - destino da carona
	 * @param calendar - um objeto do tipo calendar com data e hora da carona
	 * @param vagas - numero de vagas disponiveis
	 * @param idCarona - o id da carona
	 * @return - o ID da carona
	 * @throws CaronaException - referente a problemas no objeto da carona 
	 * @throws NumeroMaximoException
	 * @throws ArgumentoInexistenteException - quando um dos argumentos passados pelo Sistema não existe
	 */
	public String cadastrarCarona(String origem, String destino, Calendar calendar, int vagas, String idCarona)
			throws CaronaException, NumeroMaximoException {

		Carona carona = new CaronaComum(origem, destino, calendar, vagas,
				idCarona, this);
		listaDeCaronas.add(carona);
		return idCarona;
	}

	public String toString() {
		return "Nome: " + nome + " Login: " + login;
	}

	/**
	 * Metodo utilizado para sugerir ponto de encontro em uma carona
	 * @param pontos - os pontos de encontro
	 * @param carona - a carona que será sugerido um ponto de encontro
	 * @return -  o id da sugestão
	 * @throws CaronaException - referentes a problemas com a localização do objeto do tipo carona
	 * @throws NumeroMaximoException - referentes a problemas na criação do id da sugestçao
	 */
	public String sugerirPontoEncontro(String pontos, Carona carona,
			String idSugestao, Usuario usuarioQueSugeriu)
			throws CaronaException, NumeroMaximoException {
		SugestaoDePontoDeEncontro sugestao = new SugestaoDePontoDeEncontro(
				idSugestao, usuarioQueSugeriu);

		String[] locais = pontos.split(";");// sugestao de locais(ponto) de
											// encontro
		for (String local : locais) {
			if (!carona.getListaPontosDeEncontroPermitidos().contains(local)) {
				sugestao.getListaDeSugestaoDePontosDeEncontro().add(local);
			} else {
				throw new IllegalArgumentException("Ponto inválido");
			}

		}

		carona.getListaDeSugestoes().add(sugestao);
		return idSugestao;
	}

	public List<SolicitacaoDeVaga> getListaDeSolicitacaoDeVagas() {
		return listaDeSolicitacaoDeVagas;
	}

	/**
	 * Metodo utilizado para o usuario dono da carona, responda a uma sugestão de ponto de encontro
	 * @param sugestao - a sugestão que irá ser respondida
	 * @param pontos - os pontos com o qual o dono da carona responde em relação a sugestão
	 * @param carona - a carona que possui a sugestão
	 * @throws CaronaException - referentes a problemas com a localização do objeto do tipo carona 
	 */
	public void responderSugestaoPontoEncontro(
			SugestaoDePontoDeEncontro sugestao, String pontos, Carona carona)
			throws CaronaException {
		String[] locais = pontos.split(";");
		for (String local : locais) {
			if (!carona.getListaPontosDeEncontroPermitidos().contains(local)) {
				sugestao.getlistaDeRespostasDePontosDeEncontro().add(local);
				carona.addPontoEncontroPermitido(local);
			} else {
				throw new IllegalArgumentException("Ponto inválido");
			}
		}
	}

	/**
	 * Metodo utilizado para um usuario solicitar vaga em uma carona
	 * @param ponto - pontos referentes ao encontro na carona
	 * @param carona - a carona que o usuario irá solicitar vaga
	 * @param IdSolicitacao - o id da solicição
	 * @param donoSolcitacao - usuario que fez a solicitação
	 * @return - id da solicitação de vaga na carona
	 * @throws CaronaException - referentes a problemas com o objeto do tipo carona 
	 * @throws NumeroMaximoException - 
	 * @throws ArgumentoInexistenteException
	 */
	public String solicitarVagaPontoEncontro(String ponto, Carona carona,
			String IdSolicitacao, Usuario donoSolcitacao)
			throws CaronaException,
			NumeroMaximoException {
		if (carona.getVagas() > 0) {
			SolicitacaoDeVaga novaSolicitacao = new SolicitacaoDeVaga(carona,
					ponto, IdSolicitacao, donoSolcitacao);
			listaDeSolicitacaoDeVagas.add(novaSolicitacao);
			carona.addNovaSolicitacao(novaSolicitacao);
		}else{
			throw new CaronaException("Carona ja está completa.");
		}
		return IdSolicitacao;
	}

	/**
	 * Metodo para que o usuario aceite os pontos que outro usuario sugerio
	 * @param solicitacao - solicitação que sera aceita
	 * @throws ArgumentoInexistenteException
	 */
	public void aceitarSolicitacaoPontoEncontro(SolicitacaoDeVaga solicitacao) throws ArgumentoInexistenteException {
		if (solicitacao.getEstado().equals(EstadoSolicitacao.ACEITA)) {
			throw new ArgumentoInexistenteException("Solicitação inexistente");
		}
		solicitacao.solicitacaoAceita();
	}

	/**
	 * Metodo utilizado para que um usuario desista de uma solicitação de vaga em uma determinada carona
	 * @param solicitacao - a solicitação que sera removida da carona
	 * @param caronaTemp - a carona que terá a solicitação removida
	 */
	public void desistirRequisicao(SolicitacaoDeVaga solicitacao,
			Carona caronaTemp) {
		listaDeSolicitacaoDeVagas.remove(solicitacao);
		caronaTemp.removeSolicitacao(solicitacao);
	}

	public int getCaronasSeguras() {
		return this.caronasSeguras;
	}

	public void setCaronasSeguras() {
		this.caronasSeguras++;
	}

	public int getCaronasNaoFuncionaram() {
		return this.caronaNaoFuncionaram;
	}

	public void setCaronasNaoFuncionaram() {
		this.caronaNaoFuncionaram++;
	}

	public int getFaltasEmVagas() {
		return this.faltasEmVagas;
	}

	public void setFaltasEmVagas() {
		this.faltasEmVagas++;
	}

	public int getPresencaEmVagas() {
		return this.presencaEmVagas;
	}

	public void setPresencaEmVagas() {
		this.presencaEmVagas++;
	}

	// aki come�a os metodos do usuario

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws LoggerException {
		if ((email == null || email.trim().equals("") || email.contains(" "))) {
			throw new LoggerException("Email inválido");
		}
		this.email = email.trim();
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco.trim();
	}

	public String getSenha() {
		return this.senha;
	}

	public List<String> getListaDeMensagens() {
		return this.listaDeMensagens;
	}

	public void setSenha(String senha) throws LoggerException {
		if ((senha == null || senha.trim().equals(""))) {
			throw new LoggerException("Senha inválido");
		}
		this.senha = senha.trim();
	}

	public String getNome() {
		return nome;
	}

	public String getLogin() {
		return login;
	}

	private void setLogin(String login) throws LoggerException {
		if ((login == null || login.trim().equals(""))) {
			throw new LoggerException("Login inválido");
		}
		this.login = login.trim();
	}

	public void setNome(String nome) throws LoggerException {
		if (nome == null || nome.trim().equals("")) {
			throw new LoggerException("Nome inválido");
		}
		this.nome = nome.trim();
	}
	
	/**
	 * Metodo utilizado para que o dono da carona recuse uma solicitação de vaga em uma carona que seja dono.
	 * @param idSessao - id do usuario logado
	 * @param idSolicitacao - solicitação que sera recusada
	 * @throws ArgumentoInexistenteException - quando não existe esta solicitação
	 */
	public void rejeitarSolicitacao(SolicitacaoDeVaga solicitacao)
			throws ArgumentoInexistenteException{
		if (solicitacao.getEstado().equals(EstadoSolicitacao.REJEITADA)) {
			throw new ArgumentoInexistenteException("Solicitação inexistente");
		}
		solicitacao.solicitacaoRejeitada();

	}

	public List<Carona> getCaronas() {
		return listaDeCaronas;
	}

	/**
	 * Metodo para visualização do perfil de um usuario
	 * @param idSesao - id do usuario logado
	 * @param login - login do usuario a ser vizualizado perfil
	 * @return - nome e login do usuario solicitado
	 * @throws LoggerException
	 * @throws ArgumentoInexistenteException
	 */
	public String visualizarPerfil(Usuario usuarioProcurado) {
		return usuarioProcurado.toString();
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Usuario)) {
			return false;
		}

		if (!(((Usuario) obj).getLogin().equals(this.login))) {
			return false;
		}

		return true;
	}

	public void adicionaReview(Carona caronaTemp, String review) {
		if (review.equals("não faltou")) {
			this.setPresencaEmVagas();
		} else {
			this.setFaltasEmVagas();
		}
		Reviews.add(review + " na Carona de " + caronaTemp.toString());
	}

	public List<Carona> getSolicitacaoAceitas() {
		LinkedList<Carona> listaCaronaAceitas = new LinkedList<Carona>();
		for (SolicitacaoDeVaga solicitacao : listaDeSolicitacaoDeVagas) {
			if (solicitacao.getEstado().equals(EstadoSolicitacao.ACEITA)) {
				listaCaronaAceitas.add(solicitacao.getCarona());
			}
		}
		return listaCaronaAceitas;
	}
	
	/**
	 * Metodo que cadastra uma carona municipal
	 * @param origem - origem da carona
	 * @param destino - destino da carona
	 * @param cidade - cidade da carona
	 * @param calendario - objeto do tipo calendar com a data e hora da carona
	 * @param vagas - numero de vagas disponiveis
	 * @param idCarona - o id da carona que sera cadastrada
	 * @return id da carona
	 * @throws CaronaException - problemas na criação da carona
	 * @throws NumeroMaximoException
	 * @throws ArgumentoInexistenteException quando a id da sessão for invalida 
	 */
	public String cadastrarCaronaMunicipal(String origem, String destino,
			String cidade, Calendar calendario, int vagas, String idCarona)
			throws 
			CaronaException{
		Carona carona = new CaronaMunicipal(origem, destino, cidade, calendario, vagas, idCarona, this);
		listaDeCaronas.add(carona);
		
		return idCarona;
	}
	
	/**
	 * Metodo para que um usuario cadastre um interesse sobre uma carona que esteja procurando em um intervalo de tempo
	 * @param origem - origem da carona que esta interessado
	 * @param destino - destino da carona que esta interessado
	 * @param calendarioInicial - dia e e horario do inicial do interesse em pegar uma carona
	 * @param calendarioFinal - dia e horario do final do interesse em pegar uma carona
	 * @param id - id do interesse
	 * @param caronaEhNoDia - boolean pra saber se a carona que busca é no dia que esta interessado
	 * @return - id do interesse cadastrado 
	 * @throws NumeroMaximoException
	 * @throws CaronaException
	 * @throws ArgumentoInexistenteException - data invalida
	 */
	public String cadastrarInteresse(String origem, String destino,
			Calendar calendarioInicial, Calendar calendarioFinal, String id, boolean caronaEhNoDia)
			throws CaronaException {
		Interesse interesseTemp = new Interesse(this, origem, destino,
				calendarioInicial, calendarioFinal, id, caronaEhNoDia);
		listaDeInteresses.add(interesseTemp);
	
		return id;
	}

	public void addMensagen(String novaMensagem) {
		listaDeMensagens.add(novaMensagem);
	}
}
