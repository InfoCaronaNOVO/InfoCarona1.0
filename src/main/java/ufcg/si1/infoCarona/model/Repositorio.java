package ufcg.si1.infoCarona.model;

import java.util.LinkedList;
import java.util.List;

import ufcg.si1.infoCarona.model.carona.Carona;
import ufcg.si1.infoCarona.model.carona.CaronaException;
import ufcg.si1.infoCarona.model.carona.CaronaMunicipal;
import ufcg.si1.infoCarona.model.carona.TiposCarona;
import ufcg.si1.infoCarona.model.negociacao.SolicitacaoDeVaga;
import ufcg.si1.infoCarona.model.negociacao.SugestaoDePontoDeEncontro;
import ufcg.si1.infoCarona.model.usuario.Usuario;
import util.UtilInfo;



public class Repositorio {
	private List<Usuario> listaDeUsuarios;
	private ManipuladorArquivoXML arquivo;
	
	public static Repositorio instance;
	
	protected Repositorio() {
		criaRepositorio();
	}

	private void criaRepositorio() {
		arquivo = new ManipuladorArquivoXML("src/main/java/ufcg/si1/infoCarona/arquivos/usuarios");
		listaDeUsuarios = arquivo.ler();
	}
	
	public static Repositorio getInstance(){
		if (instance == null){
			instance = new Repositorio();
		}
		return instance;
	}

	// ///metodos que estao corretos no repositorio/////

	public void addUsuario(Usuario usuario) {
		listaDeUsuarios.add(usuario);
	}

	public Usuario buscaUsuarioLogin(String login) {
		Usuario retorno = null;
		for (Usuario usuario : listaDeUsuarios) {
			if (usuario.getLogin().equals(login)) {
				retorno = usuario;
			}
		}

		return retorno;
	}

	public void removeUsuarioLogin(Usuario usuario) {
		listaDeUsuarios.remove(usuario);
	}

	public List<Usuario> getTodosOsUsuarios() {
		return listaDeUsuarios;
	}

	public Usuario buscaUsuarioEmail(String email) {
		Usuario retorno = null;
		for (Usuario usuario : listaDeUsuarios) {
			if (usuario.getEmail().equals(email)) {
				retorno = usuario;
			}
		}

		return retorno;
	}

	public List<Usuario> buscaUsuarioNome(String nome) {
		List<Usuario> retorno = new LinkedList<Usuario>();

		for (Usuario usuario : listaDeUsuarios) {
			if (usuario.getNome().equals(nome)) {
				retorno.add(usuario);
			}
		}

		return retorno;
	}

	public List<Carona> getTodasAsCaronas() {
		List<Carona> retorno = new LinkedList<Carona>();

		for (Usuario usuario : listaDeUsuarios) {
			for (Carona caronaTemp : usuario.getCaronas()) {
				retorno.add(caronaTemp);
			}
		}
		return retorno;
	}

	public Carona getCaronaId(String idCarona)
			throws CaronaException {
		for (Usuario usuario : listaDeUsuarios) {
			for(Carona caronaTemp : usuario.getCaronas()){
				if(caronaTemp.getIdCarona().equals(idCarona)){
					return caronaTemp;
				}
			}
		}
		return null;
	}
	
	public SugestaoDePontoDeEncontro getSugestaoId(String idSugestao, String idCarona) throws CaronaException{
		Carona caronaTemp = this.getCaronaId(idCarona);
		for (SugestaoDePontoDeEncontro sugestaoTemp : caronaTemp.getListaDeSugestoes()) {
			if(sugestaoTemp.getIdSugestao().equals(idSugestao)){
				return sugestaoTemp;
			}
		}
		
		return null;
	}

	
	public List<Carona> localizaCaronaPorOrigemDestino(String origem,
			String destino) {

		List<Carona> retorno = new LinkedList<Carona>();

		for (Usuario UsuarioTemp : listaDeUsuarios) {
			for (Carona caronaTemp : UsuarioTemp.getCaronas()) {
				if ((caronaTemp.getOrigem().equals(origem))
						&& (caronaTemp.getDestino().equals(destino))) {
					retorno.add(caronaTemp);
				}
			}
		}
		return retorno;
	}

	public List<Carona> localizaCaronaPorDestino(String destino) {
		List<Carona> retorno = new LinkedList<Carona>();

		for (Usuario UsuarioTemp : listaDeUsuarios) {
			for (Carona caronaTemp : UsuarioTemp.getCaronas()) {
				if (caronaTemp.getDestino().equals(destino)) {
					retorno.add(caronaTemp);
				}
			}
		}
		return retorno;
	}

	public List<Carona> localizaCaronaPorOrigem(String origem) {
		List<Carona> retorno = new LinkedList<Carona>();

		for (Usuario UsuarioTemp : listaDeUsuarios) {
			for (Carona caronaTemp : UsuarioTemp.getCaronas()) {
				if (caronaTemp.getOrigem().equals(origem)) {
					retorno.add(caronaTemp);
				}
			}
		}
		return retorno;
	}

	public List<Carona> getCaronas() {
		List<Carona> retorno = new LinkedList<Carona>();

		for (Usuario UsuarioTemp : listaDeUsuarios) {
			retorno.addAll(UsuarioTemp.getCaronas());
		}
		return retorno;
	}

	public Carona localizaCaronaPorId(String idCarona) {
		for (Usuario usuarioTemp : listaDeUsuarios) {
			for (Carona caronaTemp : usuarioTemp.getCaronas()) {
				if (caronaTemp.getIdCarona().equals(idCarona)) {
					return caronaTemp;
				}
			}
		}
		return null;
	}
	
	public boolean checaExisteLogin(String login){
		for (Usuario UsuarioTemp : listaDeUsuarios) {
			if(UsuarioTemp.getLogin().equals(login)){
				return true;
			}
		}		
		return false;
	}
	
	public boolean checaExisteEmail(String email){
		for (Usuario UsuarioTemp : listaDeUsuarios) {
			if(UsuarioTemp.getEmail().equals(email)){
				return true;
			}
		}		
		return false;
	}
	
	public SolicitacaoDeVaga localizaSolicitacaoPorId(String idSolicitacao) {
		for (Usuario usuarioTemp : listaDeUsuarios) {
			for (SolicitacaoDeVaga solicitacaoTemp : usuarioTemp.getListaDeSolicitacaoDeVagas()) {
				if (solicitacaoTemp.getIdSolicitacao().equals(idSolicitacao)) {
					return solicitacaoTemp;
				}
			}
		}
		return null;
	}

	public void zerarSistema() {
		arquivo.clear();
		criaRepositorio();
	}
	
	private void salvarXML(){
		arquivo.setLista(listaDeUsuarios);
		arquivo.finalizarXML();
	}

	public void encerrarSistema() {
		this.salvarXML();
	}

	public List<Carona> localizarCaronaMunicipal(String cidade) throws CaronaException {
		List<Carona> retorno = new LinkedList<Carona>();
		boolean existeCidade = false;
		
		for (Usuario UsuarioTemp : listaDeUsuarios) {
			for (Carona caronaTemp : UsuarioTemp.getCaronas()) {
				if (caronaTemp.getTipoCarona().equals(TiposCarona.MUNICIPAL)) {
					if(((CaronaMunicipal) caronaTemp).getCidade().equals(cidade)){
						existeCidade = true;
						retorno.add(caronaTemp);
					}
					
				}
			}
		}
		if(!existeCidade){
			throw new CaronaException("Cidade inexistente");
		}
		return retorno;
	}
	
	public List<Carona> localizarCaronaMunicipal(String cidade, String origem, String destino) throws CaronaException {
		List<Carona> retorno = new LinkedList<Carona>();
		boolean existeCidade = false;
		
		for (Usuario usuarioTemp : listaDeUsuarios) {
			for (Carona caronaTemp : usuarioTemp.getCaronas()) {
				if (caronaTemp.getTipoCarona().equals(TiposCarona.MUNICIPAL)) {
					if(((CaronaMunicipal) caronaTemp).getCidade().equals(cidade)){
						existeCidade = true;
						if(caronaTemp.getOrigem().equals(origem) && caronaTemp.getDestino().equals(destino)){
							retorno.add(caronaTemp);
						}
						
					}
					
				}
			}
		}
		if(!existeCidade){
			throw new CaronaException("Cidade inexistente");
		}
		return retorno;
	}
}
