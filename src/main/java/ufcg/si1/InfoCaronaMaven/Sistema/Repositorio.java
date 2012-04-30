package ufcg.si1.InfoCaronaMaven.Sistema;

import java.util.LinkedList;
import java.util.List;

import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.CaronaInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.CaronaInvalidaException;

public class Repositorio {
	private List<Usuario> listaDeUsuarios;
	private ManipulaArquivoXML arquivo;
	
	public Repositorio() {
		criaRepositorio();
	}

	private void criaRepositorio() {
		arquivo = new ManipulaArquivoXML("src/main/java/ufcg/si1/InfoCaronaMaven/Arquivos/usuarios.xml");
		listaDeUsuarios = arquivo.ler();
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
			throws CaronaInexistenteException, CaronaInvalidaException {
		for (Usuario usuario : listaDeUsuarios) {
			for(Carona caronaTemp : usuario.getCaronas()){
				if(caronaTemp.getIdCarona().equals(idCarona)){
					return caronaTemp;
				}
			}
		}
		return null;
	}
	
	public SugestaoDePontoDeEncontro getSugestaoId(String idSugestao, String idCarona) throws CaronaInexistenteException, CaronaInvalidaException{
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
}
