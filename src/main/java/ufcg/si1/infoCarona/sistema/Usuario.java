package ufcg.si1.infoCarona.sistema;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


public class Usuario {

	private List<Carona> listaDeCaronas;
	private List<SolicitacaoDeVaga> listaDeSolicitacaoDeVagas;
	private List<String> Reviews;
	private List<Interesse> listaDeInteresses;
	private List<String> listaDeMensagens;

	private int caronasSeguras;
	private int caronaNaoFuncionaram;
	private int faltasEmVagas;
	private int presencaEmVagas;
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

	public void aceitarSolicitacaoPontoEncontro(SolicitacaoDeVaga solicitacao) throws ArgumentoInexistenteException {
		if (solicitacao.isSolicitacaoAceita()) {
			throw new ArgumentoInexistenteException("Solicitação inexistente");
		}
		solicitacao.solicitacaoAceita();
	}

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

	public void rejeitarSolicitacao(SolicitacaoDeVaga solicitacao)
			throws ArgumentoInexistenteException{
		if (solicitacao.isSolicitacaoRejeitada()) {
			throw new ArgumentoInexistenteException("Solicitação inexistente");
		}
		solicitacao.solicitacaoRejeitada();

	}

	public List<Carona> getCaronas() {
		return listaDeCaronas;
	}

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
			if (solicitacao.isSolicitacaoAceita()) {
				listaCaronaAceitas.add(solicitacao.getCarona());
			}
		}
		return listaCaronaAceitas;
	}

	public String cadastrarCaronaMunicipal(String origem, String destino,
			String cidade, Calendar calendario, int vagas, String idCarona)
			throws 
			CaronaException{
		Carona carona = new CaronaMunicipal(origem, destino, cidade, calendario, vagas, idCarona, this);
		listaDeCaronas.add(carona);
		return idCarona;
	}

	private void addInteresseCarona(Interesse interesse) {
		listaDeInteresses.add(interesse);
	}

	public String cadastrarInteresse(String origem, String destino,
			Calendar calendarioInicial, Calendar calendarioFinal, String id, boolean caronaEhNoDia)
			throws CaronaException {
		Interesse interesseTemp = new Interesse(this, origem, destino,
				calendarioInicial, calendarioFinal, id, caronaEhNoDia);
		this.addInteresseCarona(interesseTemp);

		return id;
	}

	public void addMensagen(String novaMensagem) {
		listaDeMensagens.add(novaMensagem);
	}

	public boolean enviarEmail(String destino, String message) {
		//return EnviarEmail.enviarEmail("Info Carona", destino, "Negociação de Caronas", message);
		return true;
	}
}
